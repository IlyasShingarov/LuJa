package org.example.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.statement.StaticField;
import org.example.luja.compiler.parser.FunctionScopeVisitor;
import org.example.luja.compiler.parser.FunctionVisitor;
import org.example.luja.compiler.parser.SymbolVisitor;
import org.example.luja.compiler.symbol.ContextManagerFactory;
import org.example.luja.compiler.symbol.LuaScope;
import org.example.luja.compiler.symbol.LuaSymbolTable;
import org.example.luja.compiler.symbol.MainContextManager;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.Deque;
import java.util.List;
import java.util.Map;


@Slf4j
public class LuaChunkVisitor extends LuaParserBaseVisitor<Void> {

    private final CodeGen gen = new CodeGen();
    private MainContextManager contextManager;

    @SneakyThrows
    @Override
    public Void visitChunk(LuaParser.ChunkContext ctx) {
        log.info("Visiting chunk");

        log.info("Collecting context ================================================");
        LuaSymbolTable symbolTable = new SymbolVisitor().visit(ctx);
        contextManager = new MainContextManager(symbolTable);

        ObjectMapper om = new ObjectMapper();
//        log.info(om.writerWithDefaultPrettyPrinter().writeValueAsString(symbolTable));

        log.info("Context collected =================================================");


        log.info("Initializing class ================================================");
        gen.init();

        log.info("Collecting functions ==============================================");
        Map<String, Deque<LuaScope>> functionScopes = new FunctionScopeVisitor(symbolTable).visit(ctx);
        log.info(om.writerWithDefaultPrettyPrinter().writeValueAsString(functionScopes));
        ContextManagerFactory contextManagerFactory = new ContextManagerFactory(symbolTable, functionScopes);
        List<MethodNode> functions = new FunctionVisitor(contextManagerFactory, gen).visit(ctx);
        gen.getClassBuilder().getClassNode().methods.addAll(functions);

        log.info("Adding fields =====================================================");
        List<StaticField> fields = new GlobalVariableVisitor(contextManager).visit(ctx);
        gen.getClassBuilder().addStaticFields(fields);

        gen.startMainMethod();

        visitChildren(ctx);

        log.info("Exiting chunk");
        log.info("Exiting class");
        gen.endMainMethod();
        return null;
    }

    @Override
    public Void visitBlock(LuaParser.BlockContext ctx) {
        log.info("Visiting block");
        log.info("Entering new scope");
        contextManager.enterScope();

        log.info("Current scope {}", contextManager.getCurrentScope());

        visitChildren(ctx);

        contextManager.exitScope();
        log.info("Exiting scope");
        log.info("Exiting block");
        return null;
    }

    @Override
    public Void visitStat(LuaParser.StatContext ctx) {
        log.info("Visiting statement {}", ctx.getText());

        StatementVisitor visitor = new StatementVisitor(contextManager, gen);
        InsnList instructions = visitor.visit(ctx);

        gen.getCurrentMethod().instructions().add(instructions);

        return null;
//        return super.visitStat(ctx);
    }

    public CodeGen getBytecodeGenerator() {
        return gen;
    }
}
