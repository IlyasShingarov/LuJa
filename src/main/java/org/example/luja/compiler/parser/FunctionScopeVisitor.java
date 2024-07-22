package org.example.luja.compiler.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.luja.compiler.symbol.LuaScope;
import org.example.luja.compiler.symbol.LuaSymbolMetatype;
import org.example.luja.compiler.symbol.LuaSymbolTable;
import org.example.luja.compiler.symbol.LuaVariable;

import java.util.*;

@Slf4j
@RequiredArgsConstructor
public class FunctionScopeVisitor extends LuaParserBaseVisitor<Map<String, Deque<LuaScope>>> {

    private final LuaSymbolTable symbolTable;
    private final Map<String, Deque<LuaScope>> functions = new HashMap<>();

    @Override
    public Map<String, Deque<LuaScope>> visitChunk(LuaParser.ChunkContext ctx) {
        visitChildren(ctx);
        return functions;
    }

    @Override
    public Map<String, Deque<LuaScope>> visitFuncdecl(LuaParser.FuncdeclContext ctx) {
        String functionName = ctx.funcname().getText();
        Deque<LuaScope> functionScopes = new FScopeVisitor(symbolTable).visit(ctx);
        functions.put(functionName, functionScopes);
        return null;
    }

    @RequiredArgsConstructor
    static class FScopeVisitor extends LuaParserBaseVisitor<Deque<LuaScope>> {
        private final LuaSymbolTable symbolTable;
        private int scopeDepth = 0;
        private int localVarIndex = 0;

        private Deque<LuaScope> scopes = new ArrayDeque<>();

        @Override
        public Deque<LuaScope> visitFuncdecl(LuaParser.FuncdeclContext ctx) {

            LuaScope scope = new LuaScope(scopeDepth++);
            LuaParser.NamelistContext namelist = ctx.funcbody().parlist().namelist();
            if (namelist != null && namelist.NAME() != null && !namelist.NAME().isEmpty()) {
                namelist.NAME().stream()
                        .map(ParseTree::getText)
                        .forEach(name -> scope.declare(new LuaVariable(
                                name,
                                localVarIndex++,
                                LuaSymbolMetatype.LOCAL,
                                false
                        )));
            }
            scopes.add(scope);

            visit(ctx.funcbody());

            return scopes;
        }

        @Override
        public Deque<LuaScope> visitIfstat(LuaParser.IfstatContext ctx) {
            for (LuaParser.BlockContext block : ctx.block()) {
                LuaScope newScope = new LuaScope(scopeDepth++);
                scopes.add(newScope);
                visitBlock(block);
            }
            return null;
        }

        @Override
        public Deque<LuaScope> visitWhileloop(LuaParser.WhileloopContext ctx) {
            LuaScope newScope = new LuaScope(scopeDepth++);
            scopes.add(newScope);
            visitBlock(ctx.block());
            return null;
        }

        @Override
        public Deque<LuaScope> visitForloop(LuaParser.ForloopContext ctx) {
            LuaScope newScope = new LuaScope(scopeDepth++);
            scopes.add(newScope);

            if (ctx.NAME() != null) {
                declareLocal(ctx.NAME().getText());
            } else if (ctx.namelist() != null) {
                ctx.namelist().NAME().stream()
                        .map(ParseTree::getText)
                        .forEach(this::declareLocal);
            }

            visitBlock(ctx.block());

            return null;
        }

        @Override
        public Deque<LuaScope> visitVardecl(LuaParser.VardeclContext ctx) {
            if (ctx.LOCAL() != null) {
                log.info("Local variable declaration encountered {}", ctx.getText());
                ctx.attnamelist().NAME().stream()
                        .map(ParseTree::getText)
                        .forEach(this::declareLocal);
            } else {
                log.info("Global variable declaration encountered {}", ctx.getText());
                ctx.varlist().var().stream()
                        .map(var -> var.NAME().getText())
                        .forEach(symbol -> {
                            if (isLocal(symbol)) {
                                log.error("Variable {} is already declared in the current scope", symbol);
                            } else {
                                symbolTable.declareGlobal(symbol);
                            }
                        });
            }
            return null;
        }

        @Override
        public Deque<LuaScope> visitBlock(LuaParser.BlockContext ctx) {
            log.info("Visiting block");
            if (ctx.getParent() instanceof LuaParser.FuncbodyContext) {
                log.info("Block is a function body. Scope is already entered");
                return visitChildren(ctx);
            } else if (ctx.getParent() instanceof LuaParser.ForloopContext) {
                log.info("Block is a for loop. Scope is already entered");
                return visitChildren(ctx);
            } else if (ctx.getParent() instanceof LuaParser.WhileloopContext) {
                log.info("Block is a while loop. Scope is already entered");
                return visitChildren(ctx);
            } else if (ctx.getParent() instanceof LuaParser.IfstatContext) {
                log.info("Block is an if statement. Scope is already entered");
                return visitChildren(ctx);
            } else {
                log.error("Why da fuck the block is top level");
                return null;
//                symbolTable.enterScope();
//                super.visitBlock(ctx);
//                symbolTable.exitScope();
//                return symbolTable;
            }
        }

        private boolean isLocal(String name) {
            for (LuaScope scope : scopes) {
                if (scope.isDeclared(name)) {
                    return true;
                }
            }
            return false;
        }

        private void declareLocal(String name) {
            scopes.peekLast().declare(
                            new LuaVariable(
                                    name, localVarIndex++, LuaSymbolMetatype.LOCAL, false
                            ));
        }
    }
}
