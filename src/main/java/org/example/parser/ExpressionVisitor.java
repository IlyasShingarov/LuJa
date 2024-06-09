package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.expression.*;
import org.example.domain.variable.SymbolType;
import org.example.symbol.Symbol;
import org.example.symbol.SymbolTable;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionVisitor extends LuaParserBaseVisitor<Expression> {

    private final OperationVisitor operationVisitor;
    private final SymbolTable symbolTable;

    @Override
    public Expression visitNumber(LuaParser.NumberContext ctx) {
        if (ctx.INT() != null) {
            return new IntegerExpression(Integer.parseInt(ctx.INT().getText()));
        } else if (ctx.FLOAT() != null) {
            return new FloatExpression(Float.parseFloat(ctx.FLOAT().getText()));
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
    public Expression visitPrefixexp(LuaParser.PrefixexpContext ctx) {
        if (ctx.NAME() != null) {
            // Обработка переменной с возможным доступом к элементам массива или полям объекта
            String varName = ctx.NAME(0).getText();
            Symbol symbol = symbolTable.getLocalVariable(varName);
            if (symbol == null) {
                throw new RuntimeException("Undefined variable: " + varName);
            }
            VariableExpression variableExpression = new VariableExpression(symbol);

//            // Обработка доступа к элементам массива или полям объекта
//            for (int i = 0; i < ctx.getChildCount(); i++) {
//                ParseTree child = ctx.getChild(i);
//                if (child instanceof TerminalNode) {
//                    String text = child.getText();
//                    if (text.equals("[")) {
//                        // Обработка доступа к элементу массива
//                        Expression indexExpression = visit(ctx.getChild(i + 1));
//                        variableExpression.addArrayAccess(indexExpression);
//                        i++; // Пропустить выражение индекса и ']'
//                    } else if (text.equals(".")) {
//                        // Обработка доступа к полю объекта
//                        String fieldName = ctx.getChild(i + 1).getText();
//                        variableExpression.addFieldAccess(fieldName);
//                        i++; // Пропустить имя поля
//                    }
//                }
//            }
            return variableExpression;
        }
        return super.visitPrefixexp(ctx);
    }

    @Override
    public Expression visitExp(LuaParser.ExpContext ctx) {
        log.info("Visiting expression: {}", ctx.getText());

        if (ctx.TRUE() != null || ctx.FALSE() != null) {
            return new BooleanExpression(ctx.TRUE() != null);
        } else if (ctx.binop() != null) {
            log.info("Binary operation detected: {}", ctx.binop().getText());
            var left = visit(ctx.exp(0));
            var right = visit(ctx.exp(1));
            log.info("Left: {}, Right: {}", left, right);

            BinaryOperation operation = ctx.binop().accept(operationVisitor);
            log.info("Operation: {}", operation);

            return switch (operation) {
                case ADD -> handleAddition(left, right);
                case SUBTRACT ->  handleSubtraction(left, right);
                case DIVIDE -> handleDivision(left, right);
                case MULTIPLY -> handleMultiplication(left, right);
                default -> throw new IllegalArgumentException("Unknown operation: " + operation);
            };
        }

        return super.visitExp(ctx);
    }

    private Expression handleAddition(Expression left, Expression right) {
        if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() + ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() + ((FloatExpression) right).value());
        } else if (left instanceof StringExpression && right instanceof StringExpression) {
            return new StringExpression(((StringExpression) left).value() + ((StringExpression) right).value());
        }
        return null;
    }

    private Expression handleSubtraction(Expression left, Expression right) {
        if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() - ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() - ((FloatExpression) right).value());
        }
        return null;
    }

    private Expression handleDivision(Expression left, Expression right) {
        if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() / ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() / ((FloatExpression) right).value());
        }
        return null;
    }

    private Expression handleMultiplication(Expression left, Expression right) {
        if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() * ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() * ((FloatExpression) right).value());
        }
        return null;
    }
}
