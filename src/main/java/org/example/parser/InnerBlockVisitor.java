package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.expression.Expression;
import org.example.domain.expression.VariableExpression;
import org.example.parser.statement.StatementVisitor;
import org.example.parser.statement.VariableDeclarationVisitor;
import org.example.symbol.SymbolTable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class InnerBlockVisitor extends LuaParserBaseVisitor<Void> {

    private final SymbolTable symbolTable;
    private final LuaBytecodeGenerator bytecodeGenerator;
//    private final StatementVisitor statementVisitor;

    private final VariableDeclarationVisitor variableDeclarationVisitor;
    private final ExpressionVisitor expressionVisitor;

    @Override
    public Void visitBlock(LuaParser.BlockContext ctx) {
        log.info("Visiting block: {}", ctx.getText());
        return super.visitBlock(ctx);
    }

//    @Override
//    public Void visitStat(LuaParser.StatContext ctx) {
//        statementVisitor.visit(ctx);
//        return null;
//    }

    //    @Override
//    public Void visitVardecl(LuaParser.VardeclContext ctx) {
//        variableDeclarationVisitor.visit(ctx);
//        return null;
//    }

//    @Override
//    public Void visitFunctioncall(LuaParser.FunctioncallContext ctx) {
//        log.info("Visiting function call");
//        if (ctx.NAME() != null && ctx.NAME(0).getText().equals("print")) {
//            log.info("Encountered print statement");
//            var arg = ctx.args().explist().exp(0);
//            Expression text = arg.accept(expressionVisitor);
//            if (text instanceof VariableExpression) {
//                bytecodeGenerator.generatePrint(((VariableExpression) text).symbol());
//            }
//        }
//        return null;
//    }
}
