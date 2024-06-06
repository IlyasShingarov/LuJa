package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.expression.Expression;
import org.example.domain.statement.Statement;
import org.example.domain.statement.VariableDeclaration;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatementVisitor extends LuaParserBaseVisitor<Statement> {

    private final VariableDeclarationVisitor variableDeclarationVisitor;
    private final ExpressionVisitor expressionVisitor;
    private final LuaBytecodeGenerator bytecodeGenerator;

    @Override
    public Statement visitStat(LuaParser.StatContext ctx) {
        log.info("Visiting statement");
        if (ctx.vardecl() != null) {
            return ctx.vardecl().accept(variableDeclarationVisitor);
        }
        log.info("Returning from statement");
        return super.visitChildren(ctx); // ????
    }

    @Override
    public Statement visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        return null;
//        log.info("Visiting function call");
//        if (ctx.NAME() != null && ctx.NAME(0).getText().equals("print")) {
//            log.info("Encountered print statement");
//            var arg = ctx.args().explist().exp(0);
//            bytecodeGenerator.generatePrint(arg.getText());
//        }
//        return null;
    }
}
