package org.example.parser.expression;

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
import org.example.parser.OperationVisitor;
import org.example.symbol.VariableSymbol;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Expression visitPrefixexp(LuaParser.PrefixexpContext ctx) {
        /*
        prefixexp
            : NAME ('[' exp ']' | '.' NAME)*
            | functioncall ('[' exp ']' | '.' NAME)*
            | '(' exp ')' ('[' exp ']' | '.' NAME)*
         */
        if (ctx.NAME() != null && !ctx.NAME().isEmpty()) {
            log.info("Name detected: {}", ctx.NAME());
            String varName = ctx.NAME(0).getText();
            VariableSymbol symbol = symbolTable.getLocalVariable(varName);
            if (symbol == null) {
                throw new RuntimeException("Undefined variable: " + varName);
            }
            if (!ctx.OB().isEmpty() && !ctx.CB().isEmpty()) {
                // Обработка массива
                log.info("Array access detected: {}", ctx.getText());
                Expression index = visit(ctx.exp(0));
                String arrDescriptor = symbol.type().getDescriptor();
                Type elementType;
                if (arrDescriptor.length() == 1) {
                    elementType = Type.getType(arrDescriptor);
                } else {
                    elementType= Type.getType(arrDescriptor.substring(1));
                }
//                return new ArrayAccessExpression(new VariableExpression(symbol), elementType, index);
            } else if (!ctx.DOT().isEmpty() && ctx.NAME().size() > 1) {
                // Обработка поля
                log.info("Field access detected: {}", ctx.getText());
                String fieldName = ctx.NAME(1).getText();
//                return new TableAccessExpression(new VariableExpression(symbol), fieldName);
            } else {
//                return new VariableExpression(symbol);
            }
        } else if (ctx.CP() != null) {
            // Обработка выражения в скобках
            log.info("Bracket expression detected: {}", ctx.getText());
            return visit(ctx.exp(0));
        } else if (ctx.functioncall() != null) {
            log.info("Function call detected: {}", ctx.functioncall().getText());
            return ctx.functioncall().accept(this);
        }
        return super.visitPrefixexp(ctx);
    }

    @Override
    public Expression visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        log.info("Visiting function call: {}", ctx.getText());
        if (ctx.NAME(0).getText().equals("io") && ctx.NAME(1).getText().equals("read")) {
            log.info("IO read detected");
            Expression arg = visit(ctx.args().explist().exp(0));
            StringExpression type = (StringExpression) arg;
            Type returnType = switch (type.value()) {
                case "int" -> Type.INT_TYPE;
                case "string" -> Type.getType(String.class);
                default -> throw new IllegalArgumentException("Unknown type: " + type.value());
            };
            return new BuiltInFunctionExpression(
                    "io.read", returnType,
                    () -> luaBytecodeGenerator.generateIoRead(type.value())
            );
        } else {
            String functionName = ctx.NAME(0).getText();
            log.info("Function name: {}", functionName);
            List<Expression> arguments = new ArrayList<>();
            if (ctx.args().explist() != null) {
                for (LuaParser.ExpContext expContext : ctx.args().explist().exp()) {
                    arguments.add(visit(expContext));
                }
            }
            return new FunctionExpression(functionName, symbolTable.getFunction(functionName), arguments);
        }
//        return super.visitFunctioncall(ctx);
    }

    @Override
    public Expression visitExp(LuaParser.ExpContext ctx) {
        log.info("Visiting expression: {}", ctx.getText());

        if (ctx.TRUE() != null || ctx.FALSE() != null) {
            return new BooleanExpression(ctx.TRUE() != null);
        } else if (ctx.NIL() != null) {
            log.info("Nil detected");
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
        int tableSize = ctx.fieldlist() != null ? ctx.fieldlist().field().size() : 0;
        List<Map.Entry<Expression, Expression>> associativeElements = new ArrayList<>();
        List<Expression> arrayElements = new ArrayList<>();

        if (ctx.fieldlist() != null) {
            for (LuaParser.FieldContext field : ctx.fieldlist().field()) {
                if (field.exp(0) != null && field.exp(1) != null) { // Ассоциативный элемент
                    Expression key = field.exp(0).accept(this);
                    Expression value = field.exp(1).accept(this);
                    associativeElements.add(new AbstractMap.SimpleEntry<>(key, value));
                } else if (field.NAME() != null && field.exp() != null) { // Ассоциативный элемент с именем
                    Expression key = new StringExpression(field.NAME().getText());
                    Expression value = field.exp(0).accept(this);
                    associativeElements.add(new AbstractMap.SimpleEntry<>(key, value));
                } else if (field.exp() != null) { // Индексированный элемент
                    arrayElements.add(field.exp(0).accept(this));
                }
            }
        }

        if (!associativeElements.isEmpty()) {
            return new TableExpression(associativeElements, tableSize);
        } else {
            return new ArrayTableExpression(arrayElements, tableSize);
        }

//        int tableSize = ctx.fieldlist() != null ? ctx.fieldlist().field().size() : 0;
//        List<Expression> elements = new ArrayList<>();
//        if (ctx.fieldlist() != null) {
//            for (LuaParser.FieldContext field : ctx.fieldlist().field()) {
//                elements.add(field.accept(this));
//            }
//        }
//
//        return new ArrayTableExpression(elements, tableSize);
    }

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
