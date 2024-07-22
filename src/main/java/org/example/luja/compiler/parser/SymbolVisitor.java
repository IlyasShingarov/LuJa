package org.example.luja.compiler.parser;

import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.luja.compiler.symbol.LuaSymbolTable;

import java.util.Stack;

@Slf4j
public class SymbolVisitor extends LuaParserBaseVisitor<LuaSymbolTable> {

    private final LuaSymbolTable symbolTable = new LuaSymbolTable();

    @Override
    public LuaSymbolTable visitBlock(LuaParser.BlockContext ctx) {
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
        } else {
            log.info("Block is a regular block. Entering scope");
            symbolTable.enterScope();
            super.visitBlock(ctx);
            symbolTable.exitScope();
            return symbolTable;
        }
    }

    @Override
    public LuaSymbolTable visitFuncdecl(LuaParser.FuncdeclContext ctx) {
        return null;
    }

    @Override
    public LuaSymbolTable visitForloop(LuaParser.ForloopContext ctx) {
        symbolTable.enterScope();
        if (ctx.NAME() != null) {
            symbolTable.declareLocal(ctx.NAME().getText());
        } else if (ctx.namelist() != null) {
            ctx.namelist().NAME().stream()
                    .map(ParseTree::getText)
                    .forEach(symbolTable::declareLocal);
        }
        visitBlock(ctx.block());
        symbolTable.exitScope();
        return null;
    }

    @Override
    public LuaSymbolTable visitWhileloop(LuaParser.WhileloopContext ctx) {
        symbolTable.enterScope();
        visitBlock(ctx.block());
        symbolTable.exitScope();
        return null;
    }

//    @Override
//    public LuaSymbolTable visitFuncbody(LuaParser.FuncbodyContext ctx) {
//        ctx.parlist().namelist().NAME().stream()
//                .map(ParseTree::getText)
//                .forEach(symbolTable::declareLocal);
//        visitChildren(ctx);
//        return null;
//    }

    @Override
    public LuaSymbolTable visitVardecl(LuaParser.VardeclContext ctx) {
        if (ctx.LOCAL() != null) {
            log.info("Local variable declaration encountered {}", ctx.getText());
            ctx.attnamelist().NAME().stream()
                    .map(ParseTree::getText)
                    .forEach(symbolTable::declareLocal);
        } else {
            log.info("Global variable declaration encountered {}", ctx.getText());
            ctx.varlist().var().stream()
                    .map(var -> var.NAME().getText())
                    .forEach(symbol -> {
                        if (symbolTable.isLocal(symbol)) {
                            log.error("Variable {} is already declared in the current scope", symbol);
                        } else {
                            symbolTable.declareGlobal(symbol);
                        }

                    });
        }
        return super.visitVardecl(ctx);
    }
}
