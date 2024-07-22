//package org.example.parser.expression;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.antlr.LuaParser;
//import org.example.antlr.LuaParserBaseVisitor;
//import org.example.bytecode.*;
//import org.example.bytecode.statement.VariableBuilder;
//import org.example.domain.expression.*;
//import org.example.domain.expression.constant.BooleanExpression;
//import org.example.domain.expression.constant.FloatExpression;
//import org.example.domain.expression.constant.IntegerExpression;
//import org.example.domain.expression.constant.StringExpression;
//import org.example.parser.OperationVisitor;
//import org.example.symbol.FunctionSymbol;
//import org.example.symbol.SymbolTable;
//import org.example.symbol.VariableSymbol;
//import org.objectweb.asm.Opcodes;
//import org.objectweb.asm.Type;
//import org.springframework.stereotype.Component;
//
//import java.util.AbstractMap;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import static org.example.parser.expression.ExpressionUtil.*;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class ExpressionEvalVisitor extends LuaParserBaseVisitor<Void> {
//    private final OperationVisitor operationVisitor;
//    private final SymbolTable symbolTable;
//    private final LuaBytecodeGenerator bytecodeGenerator;
//
//    @Override
//    public Void visitNumber(LuaParser.NumberContext ctx) {
//        if (ctx.INT() != null) {
//            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(
//                            new IntegerExpression(Integer.parseInt(ctx.INT().getText()))
//                    );
//        } else if (ctx.FLOAT() != null) {
//            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(
//                            new FloatExpression(Double.parseDouble(ctx.FLOAT().getText()))
//                    );
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitString(LuaParser.StringContext ctx) {
//        if (ctx.NORMALSTRING() != null) {
//            var str = ctx.NORMALSTRING().getText().substring(1, ctx.NORMALSTRING().getText().length() - 1);
//            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(
//                            new StringExpression(str)
//                    );
//        } else if (ctx.CHARSTRING() != null) {
//            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(
//                            new StringExpression(ctx.CHARSTRING().getText())
//                    );
//        } else if (ctx.LONGSTRING() != null) {
//            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(
//                            new StringExpression(ctx.LONGSTRING().getText())
//                    );
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitPrefixexp(LuaParser.PrefixexpContext ctx) {
//        /*
//        prefixexp
//            : NAME ('[' exp ']' | '.' NAME)*
//            | functioncall ('[' exp ']' | '.' NAME)*
//            | '(' exp ')' ('[' exp ']' | '.' NAME)*
//         */
//        if (ctx.NAME() != null && !ctx.NAME().isEmpty()) {
//            log.info("Name detected: {}", ctx.NAME());
//            String varName = ctx.NAME(0).getText();
//            VariableSymbol symbol = symbolTable.getLocalVariable(varName);
//            if (symbol == null) {
//                throw new RuntimeException("Undefined variable: " + varName);
//            }
//            if (!ctx.OB().isEmpty() && !ctx.CB().isEmpty()) {
//                // Обработка массива
//                log.info("Array access detected: {}", ctx.getText());
//                int arrayVarIndex = symbolTable.getLocalVariable(varName).index();
////                new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
////                        .loadExpression(new VariableExpression(symbol));
//                visit(ctx.exp(0));
//                bytecodeGenerator.getMethodVisitor().visitInsn(Opcodes.AALOAD);
//
////                return new ArrayAccessExpression(new VariableExpression(symbol), index);
//            } else if (!ctx.DOT().isEmpty() && ctx.NAME().size() > 1) {
//                // Обработка поля
//                log.info("Field access detected: {}", ctx.getText());
//                int tableIndex = symbolTable.getLocalVariable(varName).index();
//                String fieldName = ctx.NAME(1).getText();
//                new TableBuilder(bytecodeGenerator.getMethodVisitor())
//                        .getHashMapElement(tableIndex, fieldName);
////                return new TableAccessExpression(new VariableExpression(symbol), fieldName);
//            } else {
////                new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
////                        .loadExpression(new VariableExpression(symbol));
////                return new VariableExpression(symbol);
//            }
//        } else if (ctx.CP() != null) {
//            // Обработка выражения в скобках
//            log.info("Bracket expression detected: {}", ctx.getText());
//            return visit(ctx.exp(0));
//        } else if (ctx.functioncall() != null) {
//            log.info("Function call detected: {}", ctx.functioncall().getText());
//            return ctx.functioncall().accept(this);
//        }
//        return null;
////        return super.visitPrefixexp(ctx);
//    }
//
//    @Override
//    public Void visitFunctioncall(LuaParser.FunctioncallContext ctx) {
//        log.info("Visiting function call: {}", ctx.getText());
//        if (ctx.NAME(0).getText().equals("io") && ctx.NAME(1).getText().equals("read")) {
////            log.info("IO read detected");
////            Expression arg = visit(ctx.args().explist().exp(0));
////            StringExpression type = (StringExpression) arg;
////            Type returnType = switch (type.value()) {
////                case "int" -> Type.INT_TYPE;
////                case "string" -> Type.getType(String.class);
////                default -> throw new IllegalArgumentException("Unknown type: " + type.value());
////            };
////            return new BuiltInFunctionExpression(
////                    "io.read", returnType,
////                    () -> bytecodeGenerator.generateIoRead(type.value())
////            );
//        } else {
//            String functionName = ctx.NAME(0).getText();
//            FunctionSymbol functionSymbol = symbolTable.getFunction(functionName);
//            ctx.args().explist().accept(this);
//            new FunctionCallBuilder(bytecodeGenerator.getMethodVisitor())
//                    .callFunction(functionSymbol);
//            return null;
////            log.info("Function name: {}", functionName);
////            List<Expression> arguments = new ArrayList<>();
////            if (ctx.args().explist() != null) {
////                for (LuaParser.ExpContext expContext : ctx.args().explist().exp()) {
////                    arguments.add(visit(expContext));
////                }
////            }
////            return new FunctionExpression(functionName, symbolTable.getFunction(functionName), arguments);
////        }
////        return super.visitFunctioncall(ctx);
//        }
//        return null;
//    }
//
//    @Override
//    public Void visitExp(LuaParser.ExpContext ctx) {
//        log.info("Visiting expression: {}", ctx.getText());
//
//        if (ctx.TRUE() != null || ctx.FALSE() != null) {
//            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(
//                            new BooleanExpression(ctx.TRUE() != null)
//                    );
//        } else if (ctx.NIL() != null) {
//            log.info("Nil detected");
//        } else if (ctx.binop() != null) {
//            log.info("Binary operation detected: {}", ctx.binop().getText());
//            // Кладем левый операнд на стек
//            visit(ctx.exp(0));
//            // Кладем правый операнд на стек
//            visit(ctx.exp(1));
//
//            BinaryOperation operation = ctx.binop().accept(operationVisitor);
//            log.info("Operation: {}", operation);
//
//            bytecodeGenerator.getMethodVisitor().visitInsn(operation.getOpcode(Type.INT_TYPE));
//            return null;
//        } else if (ctx.prefixexp() != null) {
////            log.info("Prefix expression detected: {}", ctx.prefixexp().getText());
////            Expression expression = visit(ctx.prefixexp());
////            log.info("Expression: {}", expression);
//        }
//
//        return super.visitExp(ctx);
//    }
//
////    @Override
////    public Expression visitTableconstructor(LuaParser.TableconstructorContext ctx) {
////        log.info("Visiting table constructor: {}", ctx.getText());
////        int tableSize = ctx.fieldlist() != null ? ctx.fieldlist().field().size() : 0;
////        List<Map.Entry<Expression, Expression>> associativeElements = new ArrayList<>();
////        List<Expression> arrayElements = new ArrayList<>();
////
////        if (ctx.fieldlist() != null) {
////            for (LuaParser.FieldContext field : ctx.fieldlist().field()) {
////                if (field.exp(0) != null && field.exp(1) != null) { // Ассоциативный элемент
////                    Expression key = field.exp(0).accept(this);
////                    Expression value = field.exp(1).accept(this);
////                    associativeElements.add(new AbstractMap.SimpleEntry<>(key, value));
////                } else if (field.NAME() != null && field.exp() != null) { // Ассоциативный элемент с именем
////                    Expression key = new StringExpression(field.NAME().getText());
////                    Expression value = field.exp(0).accept(this);
////                    associativeElements.add(new AbstractMap.SimpleEntry<>(key, value));
////                } else if (field.exp() != null) { // Индексированный элемент
////                    arrayElements.add(field.exp(0).accept(this));
////                }
////            }
////        }
////
////        if (!associativeElements.isEmpty()) {
////            return new TableExpression(associativeElements, tableSize);
////        } else {
////            return new ArrayTableExpression(arrayElements, tableSize);
////        }
//
////        int tableSize = ctx.fieldlist() != null ? ctx.fieldlist().field().size() : 0;
////        List<Expression> elements = new ArrayList<>();
////        if (ctx.fieldlist() != null) {
////            for (LuaParser.FieldContext field : ctx.fieldlist().field()) {
////                elements.add(field.accept(this));
////            }
////        }
////
////        return new ArrayTableExpression(elements, tableSize);
//}
