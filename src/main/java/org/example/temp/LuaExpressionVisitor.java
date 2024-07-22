package org.example.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.*;
import org.example.luja.compiler.symbol.ContextManager;
import org.example.luja.compiler.symbol.MainContextManager;
import org.example.luja.compiler.symbol.LuaVariable;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LuaExpressionVisitor extends LuaParserBaseVisitor<Expression> {

    private final ContextManager contextManager;

    @Override
    public Expression visitNumber(LuaParser.NumberContext ctx) {
        if (ctx.INT() != null) {
            return new IntegerExpression(Integer.parseInt(ctx.INT().getText()));
        } else if (ctx.FLOAT() != null) {
            return new FloatExpression(Double.parseDouble(ctx.FLOAT().getText()));
        }
        return null;
    }

    @Override
    public Expression visitString(LuaParser.StringContext ctx) {
        if (ctx.NORMALSTRING() != null) {
            var str = ctx.NORMALSTRING().getText().substring(1, ctx.NORMALSTRING().getText().length() - 1);
            return new StringExpression(str);
        } else if (ctx.CHARSTRING() != null) {
            return new StringExpression(ctx.CHARSTRING().getText());
        } else if (ctx.LONGSTRING() != null) {
            return new StringExpression(ctx.LONGSTRING().getText());
        }
        return null;
    }

    @Override
    public Expression visitExp(LuaParser.ExpContext ctx) {
        if (ctx.TRUE() != null) return new BooleanExpression(true);
        if (ctx.FALSE() != null) return new BooleanExpression(false);
        if (ctx.NIL() != null) return new NilExpression();
        if (ctx.binop() != null) {
            log.info("Binary expression encountered {}", ctx.getText());
            var left = visit(ctx.exp(0));
            var right = visit(ctx.exp(1));
            var operation = BinaryOperation.fromSymbol(ctx.binop().getText());
            log.info("Left: {}, Right: {}, Operation: {}", left, right, operation);
            return new BinaryExpression(left, right, operation);
        }
        return super.visitExp(ctx);
    }

    @Override
    public Expression visitPrefixexp(LuaParser.PrefixexpContext ctx) {
        if (ctx.NAME() != null && ctx.NAME().size() == 1) { // Variable expression
            log.info("Variable expression encountered {}", ctx.NAME(0).getText());
            log.info("Current scope: {}", contextManager.getCurrentScope());
            LuaVariable variable = contextManager.getCurrentScope().getVariable(ctx.NAME(0).getText());
            return new VariableExpression(variable);
        } else if (ctx.NAME() != null && ctx.NAME().size() == 2) { // Function call
            log.info("Function call encountered {}", ctx.NAME(0).getText());
            return visit(ctx.functioncall());
        }
        return super.visitPrefixexp(ctx);
    }

    @Override
    public Expression visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        List<Expression> arguments = ctx.args().explist().exp().stream()
                .map(this::visit)
                .toList();

        String functionName = ctx.NAME(0).getText();
        return new FunctionExpression(functionName, arguments);
    }
}
