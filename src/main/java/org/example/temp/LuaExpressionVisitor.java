package org.example.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.*;
import org.example.luja.compiler.symbol.ContextManager;
import org.example.luja.compiler.symbol.LuaVariable;

import java.util.ArrayList;
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
        if (ctx.NAME() != null && ctx.OB() != null && !ctx.OB().isEmpty()) { // Table constructor
            log.info("Table access encountered {}", ctx.NAME(0).getText());
            LuaVariable tableVariable = contextManager.getCurrentScope().getVariable(ctx.NAME(0).getText());
            List<Expression> keys = ctx.exp().stream()
                    .map(this::visit)
                    .toList();
            return new TableAccessExpression(new VariableExpression(tableVariable), keys);
        } else if (ctx.NAME() != null && ctx.NAME().size() == 1) { // Variable expression
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
        List<Expression> arguments = new ArrayList<>();
        if (ctx.args().explist() != null) {
            arguments.addAll(ctx.args().explist().exp().stream()
                    .map(this::visit)
                    .toList());
        }

        String functionName = ctx.NAME(0).getText();
        return new FunctionExpression(functionName, arguments);
    }

    @Override
    public Expression visitTableconstructor(LuaParser.TableconstructorContext ctx) {
        FieldVisitor fieldVisitor = new FieldVisitor();
        List<FieldExpression> fields = ctx.fieldlist().field().stream()
                .map(fieldVisitor::visitField).toList();

        log.info("Table constructor fields: {}", fields);

        return new TableExpression(fields, 0);
    }

    @Override
    public Expression visitUnop(LuaParser.UnopContext ctx) {
        log.error("UNOPUNOPUNOPUNOPUNOPUNOPUNOPUNOPUNOPUNOP");
        return super.visitUnop(ctx);
    }

    private class FieldVisitor extends LuaParserBaseVisitor<FieldExpression> {
        private int index = 1;

        @Override
        public FieldExpression visitField(LuaParser.FieldContext ctx) {
            if (ctx.NAME() != null) {
                String name = ctx.NAME().getText();
                return new FieldExpression(
                        new LuaExpressionVisitor(contextManager).visit(ctx.exp(0)),
                        name
                );
            }
            return new FieldExpression(
                    new LuaExpressionVisitor(contextManager).visit(ctx.exp(0)),
                    index++
            );
        }
    }
}
