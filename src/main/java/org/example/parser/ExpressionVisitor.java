package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.BooleanExpression;
import org.example.domain.expression.constant.FloatExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.example.symbol.Symbol;
import org.example.symbol.SymbolTable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionVisitor extends LuaParserBaseVisitor<Expression> {

    private final OperationVisitor operationVisitor;
    private final SymbolTable symbolTable;
    private final LuaBytecodeGenerator luaBytecodeGenerator;

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
        if (ctx.NAME() != null && !ctx.NAME().isEmpty()) {
            // Обработка переменной с возможным доступом к элементам массива или полям объекта
            log.info("Name detected: {}", ctx.NAME());
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
        } else if (ctx.CP() != null) {
            // Обработка выражения в скобках
            log.info("Bracket expression detected: {}", ctx.getText());
            return visit(ctx.exp(0));
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
                case EQUALS -> handleEquals(left, right);
                case GREATER_THAN -> handleGreaterThan(left, right);
                case GREATER_THAN_OR_EQUALS -> handleGreaterThanEquals(left, right);
                case LESS_THAN -> handleLessThan(left, right);
                case LESS_THAN_OR_EQUALS -> handleLessThanEquals(left, right);
                case NOT_EQUALS -> handleNotEquals(left, right);
                default -> throw new IllegalArgumentException("Unknown operation: " + operation);
            };
        } else if (ctx.prefixexp() != null) {
            log.info("Prefix expression detected: {}", ctx.prefixexp().getText());
            Expression expression = visit(ctx.prefixexp());
            log.info("Expression: {}", expression);
        }

        return super.visitExp(ctx);
    }

    @Override
    public Expression visitTableconstructor(LuaParser.TableconstructorContext ctx) {
        log.info("Visiting table constructor: {}", ctx.getText());


        return super.visitTableconstructor(ctx);
    }

//    @Override
//    public Expression visitFieldlist(LuaParser.FieldlistContext ctx) {
//        log.info("Visiting field list: {}", ctx.getText());
//        for (LuaParser.FieldContext fieldContext : ctx.field()) {
//            log.info("Field: {}", fieldContext.getText());
//            if (fieldContext.exp(0) != null && fieldContext.exp(1) != null) {
//                // Обработка поля вида [exp] = exp
//                Object key = visit(fieldContext.exp(0));
//                Object value = visit(fieldContext.exp(1));
//                log.info("Key: {}, Value: {}", key, value);
////                bytecodeGenerator.initializeTable(tableIndex, key, value);
//            } else if (fieldCtx.NAME() != null) {
//                // Обработка поля вида NAME = exp
//                String key = fieldCtx.NAME().getText();
//                Object value = fieldCtx.exp(0).accept(expressionVisitor);
//                bytecodeGenerator.initializeTable(tableIndex, key, value);
//            } else if (fieldCtx.exp(0) != null) {
//                // Обработка поля вида exp
//                // В этом случае ключом будет текущий индекс массива
//                Object key = currentArrayIndex++;
//                Object value = fieldCtx.exp(0).accept(expressionVisitor);
//                bytecodeGenerator.initializeTable(tableIndex, key, value);
//            }
//        }
//        return super.visitFieldlist(ctx);
//    }

    private Expression handleAddition(Expression left, Expression right) {
        if (left instanceof BinaryExpression binaryLeft) {
            return new BinaryExpression(
                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
                    right,
                    BinaryOperation.ADD
            );
        }

        if (left instanceof VariableExpression || right instanceof VariableExpression) {
            return new BinaryExpression(left, right, BinaryOperation.ADD);
        }

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
        if (left instanceof BinaryExpression binaryLeft) {
            return new BinaryExpression(
                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
                    right,
                    BinaryOperation.SUBTRACT
            );
        }
        if (left instanceof VariableExpression || right instanceof VariableExpression) {
            return new BinaryExpression(left, right, BinaryOperation.SUBTRACT);
        } else if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() - ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() - ((FloatExpression) right).value());
        }
        return null;
    }

    private Expression handleDivision(Expression left, Expression right) {
        if (left instanceof BinaryExpression binaryLeft) {
            return new BinaryExpression(
                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
                    right,
                    BinaryOperation.DIVIDE
            );
        }
        if (left instanceof VariableExpression || right instanceof VariableExpression) {
            return new BinaryExpression(left, right, BinaryOperation.DIVIDE);
        } else if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() / ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() / ((FloatExpression) right).value());
        }
        return null;
    }

    private Expression handleMultiplication(Expression left, Expression right) {
        if (left instanceof BinaryExpression binaryLeft) {
            return new BinaryExpression(
                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
                    right,
                    BinaryOperation.MULTIPLY
            );
        }
        if (left instanceof VariableExpression || right instanceof VariableExpression) {
            return new BinaryExpression(left, right, BinaryOperation.MULTIPLY);
        } else if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
            return new IntegerExpression(((IntegerExpression) left).value() * ((IntegerExpression) right).value());
        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
            return new FloatExpression(((FloatExpression) left).value() * ((FloatExpression) right).value());
        }
        return null;
    }

    private Expression handleEquals(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperation.EQUALS);
    }

    private Expression handleGreaterThan(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperation.GREATER_THAN);
    }

    private Expression handleGreaterThanEquals(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperation.GREATER_THAN_OR_EQUALS);
    }

    private Expression handleLessThan(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperation.LESS_THAN);
    }

    private Expression handleLessThanEquals(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperation.LESS_THAN_OR_EQUALS);
    }

    private Expression handleNotEquals(Expression left, Expression right) {
        return new BinaryExpression(left, right, BinaryOperation.NOT_EQUALS);
    }
}
