package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.*;
import org.example.bytecode.statement.VariableBuilder;
import org.example.domain.VariableAccess;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.*;
import org.example.domain.statement.VariableDeclaration;
import org.example.parser.expression.ExpressionEvalVisitor;
import org.example.parser.expression.ExpressionVisitor;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VariableDeclarationVisitor extends LuaParserBaseVisitor<VariableDeclaration> {

    private final ExpressionVisitor expressionVisitor;
    private final VariableVisitor variableVisitor;
    private final ExpressionEvalVisitor expressionEvalVisitor;

    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

    private Runnable jump;

    @Override
    public VariableDeclaration visitVardecl(LuaParser.VardeclContext ctx) {
        log.info("Visiting variable declaration: {}", ctx.getText());
        if (ctx.LOCAL() != null) {
            log.info("Encountered local variable declaration");
            List<String> varnames = ctx.attnamelist().NAME().stream()
                    .map(ParseTree::getText)
                    .toList();
            log.info("Local variable names: {}", varnames);

            List<Expression> values = ctx.explist().exp().stream()
                    .map(exp -> exp.accept(expressionVisitor))
                    .toList();
            log.info("Values: {}", values);

            for (int i = 0; i < varnames.size(); i++) {
                createLocalVariable(varnames.get(i), values.get(i));
            }

            log.info("Returning from variable declaration");
            return new VariableDeclaration(varnames, values);
        } else {
            log.info("Encountered variable context: {}", ctx.getText());

            List<VariableAccess> variableAccess = ctx.varlist().var().stream()
                    .map(var -> var.accept(variableVisitor))
                    .toList();
            log.info("Variable access: {}", variableAccess);

            List<Expression> values = ctx.explist().exp().stream()
                    .map(exp -> exp.accept(expressionVisitor))
                    .toList();
            log.info("Values: {}", values);

            for (int i = 0; i < variableAccess.size(); i++) {
                updateVariable(variableAccess.get(i), values.get(i));
            }
        }
        return null;
    }

    private void updateVariable(VariableAccess variableAccess, Expression value) {
        log.info("Updating variable: {}", variableAccess);
        if (variableAccess.index() == null) {
            log.info("Updating variable: {}", variableAccess.name());
            createLocalVariable(variableAccess.name(), value);
        } else if (variableAccess.index() instanceof StringExpression keyExpression) {
            int index = symbolTable.getLocalVariable(variableAccess.name()).index();
            log.info("Updating table element at index: {}", index);
            new TableBuilder(bytecodeGenerator.getMethodVisitor())
                    .loadTable(index)
                    .pointAt(keyExpression.value())
                    .setElement(value);
        } else {
                log.info("BLYAAAAT");
                log.info(" {} {} ", variableAccess, value);
                log.info("BLYAAAAT");
                int index = symbolTable.getLocalVariable(variableAccess.name()).index();
                log.info("Updating array element at index: {}", index);
                switch (variableAccess.index()) {
                    case IntegerExpression integerExpression -> {
                        new ArrayBuilder(bytecodeGenerator.getMethodVisitor())
                                .loadArray(index)
                                .pointAt(integerExpression.value())
                                .setElement(value);
                    }
                    case VariableExpression variableExpression -> {
                        if (value instanceof ArrayAccessExpression arrExp) {
                            var arr = new ArrayBuilder(bytecodeGenerator.getMethodVisitor())
                                    .loadArray(index)
                                    .pointAt(variableExpression)
                                    .loadArray(arrExp.array().symbol().index());
                            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                                    .loadExpression(arrExp.index())
                                    .getIntArrayElement(false);
                            arr.store();
                        } else {
                            new ArrayBuilder(bytecodeGenerator.getMethodVisitor())
                                    .loadArray(index)
                                    .pointAt(variableExpression)
                                    .setElement(variableExpression);
                        }
                    }
                    case BinaryExpression binaryExpression -> {
                        var arr = new ArrayBuilder(bytecodeGenerator.getMethodVisitor())
                                .loadArray(index);
                        new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                                .loadExpression(binaryExpression);
                        arr.setElement(value);
//                                .loadExpression(variableAccess.index());
//                        arr.store();


//                        bytecodeGenerator.convertStackToInt();
//                        arr.setElement(value);
                    }
                    default ->
                            throw new IllegalStateException("Unexpected value: " + variableAccess.index());
            }
        }
    }

    private void loadBinaryExpression(BinaryExpression binaryExpression) {
        log.info("Processing binary expression: {}", binaryExpression);
        BinaryOperation binaryOperation = binaryExpression.operation();
        switch (binaryOperation) {
            case ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, DIVIDE_FLOOR -> {
                Type type = binaryExpression.left().getType();
                new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                        .generateBinaryOperation(
                                binaryExpression.left(), binaryExpression.right(),
                                binaryOperation.getOpcode(type)
                        );
            }
            case EQUALS, NOT_EQUALS, LESS_THAN, LESS_THAN_OR_EQUALS, GREATER_THAN, GREATER_THAN_OR_EQUALS -> {
                new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                        .generateComparison(
                                binaryExpression.left(), binaryExpression.right(),
                                binaryOperation
                        );
            }
        }
    }

    private void createLocalVariable(String name, Expression value) {
        switch (value) {
            case ConstantExpression constantExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, constantExpression.getType());
                new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                        .isLocal()
                        .withIndex(index)
                        .withDescriptor(constantExpression.getType().getDescriptor())
                        .withValue(constantExpression.value())
                        .build();
            }
            case VariableExpression variableExpression -> {
//                int index = symbolTable.getLocalVariableIndex(name, variableExpression.symbol().type());
//                new VariableBuilder(bytecodeGenerator.getMethodVisitor())
//                        .isLocal()
//                        .withIndex(index)
//                        .moveFrom(variableExpression.symbol().index(), variableExpression.symbol().type().getDescriptor());
            }
            case ArrayTableExpression arrayTableExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, arrayTableExpression.getType(), true);
                var table = new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                        .isLocal()
                        .withIndex(index)
                        .initArray(arrayTableExpression.size(), arrayTableExpression.getElementType());

                for (int elementIndex = 0; elementIndex < arrayTableExpression.size(); elementIndex++) {
                    table.setTableValue(elementIndex, arrayTableExpression.elements().get(elementIndex));
                }
            }
            case ArrayAccessExpression arrayAccessExpression -> {
                VariableExpression arrayVar = arrayAccessExpression.array();
                Expression indexExpression = arrayAccessExpression.index();
                switch (indexExpression) {
                    case IntegerExpression integerExpression -> {
                        int index = symbolTable.getLocalVariableIndex(name, Type.INT_TYPE);
                        new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                                .getIntArrayElement(arrayVar.symbol().index(), integerExpression);
                        new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                                .storeLocal(index, Type.INT_TYPE.getDescriptor());
                    }
                    case VariableExpression variableExpression -> {
//                        int index = symbolTable.getLocalVariableIndex(name, variableExpression.symbol().type());
//                        new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                                .getIntArrayElement(arrayVar.symbol().index(), variableExpression);
//                        new VariableBuilder(bytecodeGenerator.getMethodVisitor())
//                                .storeLocal(index, variableExpression.symbol().type().getDescriptor());
                    }
                    case BinaryExpression binaryExpression -> {
                        bytecodeGenerator.getMethodVisitor().visitInsn(Opcodes.NOP);
                        int index = symbolTable.getLocalVariableIndex(name, binaryExpression.getType());
                        var arr = new ArrayBuilder(bytecodeGenerator.getMethodVisitor())
                                .loadArray(arrayVar.symbol().index());
                        loadBinaryExpression(binaryExpression);
//                        bytecodeGenerator.convertStackToInt();
                        new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                                .getIntArrayElement(true);
                        new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                                .storeLocal(index, binaryExpression.getType().getDescriptor());
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + indexExpression);
                }
            }
            case BinaryExpression binaryExpression -> {
                log.info("Processing binary expression: {}", binaryExpression);
                BinaryOperation binaryOperation = binaryExpression.operation();
                switch (binaryOperation) {
                    case ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO, DIVIDE_FLOOR -> {
                        Type type = binaryExpression.left().getType();
                        int index = symbolTable.getLocalVariableIndex(name, type);

                        new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                                .generateBinaryOperation(
                                        binaryExpression.left(), binaryExpression.right(),
                                        binaryOperation.getOpcode(type)
                                );
                        new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                                .storeLocal(index, type.getDescriptor());
                    }
                    case EQUALS, NOT_EQUALS, LESS_THAN, LESS_THAN_OR_EQUALS, GREATER_THAN, GREATER_THAN_OR_EQUALS -> {
                        int index = symbolTable.getLocalVariableIndex(name, Type.BOOLEAN_TYPE);
                        new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                                .generateComparison(
                                        binaryExpression.left(), binaryExpression.right(),
                                        binaryOperation
                                );
                        new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                                .storeLocal(index, Type.BOOLEAN_TYPE.getDescriptor());
                    }
                }
            }
            case FunctionExpression functionExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, functionExpression.getType());
                log.info("Creating function variable: {}", functionExpression);
                new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                        .isLocal()
                        .withIndex(index)
                        .withDescriptor(functionExpression.getType().getDescriptor())
                        .withValue(() ->
                            new FunctionCallBuilder(bytecodeGenerator.getMethodVisitor())
                                    .putArguments(functionExpression.arguments())
                                    .callFunction(functionExpression.functionSymbol())
                        )
                        .build();
            }
            case BuiltInFunctionExpression builtInFunctionExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, builtInFunctionExpression.getType());
                log.info("Creating built-in function variable: {}", builtInFunctionExpression);
                new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                        .isLocal()
                        .withIndex(index)
                        .withDescriptor(builtInFunctionExpression.getType().getDescriptor())
                        .withValue(builtInFunctionExpression.runnable())
                        .build();
            }
            case TableExpression tableExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, tableExpression.getType());
                log.info("Creating table variable: {}", tableExpression);
                new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                        .isLocal()
                        .withIndex(index)
                        .withDescriptor(tableExpression.getType().getDescriptor())
                        .withValue(() -> {
                            var t = new TableBuilder(bytecodeGenerator.getMethodVisitor())
                                .createTable(index);
                            for (var element : tableExpression.elements()) {
                                t.putHashMapElement(index, element.getKey(), element.getValue());
                            }
                        });
            }
            case TableAccessExpression tableAccessExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, tableAccessExpression.getType());
                log.info("Creating table access variable: {}", tableAccessExpression);
                new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                        .isLocal()
                        .withIndex(index)
                        .withDescriptor(tableAccessExpression.getType().getDescriptor())
                        .withValue(() -> {
                            new TableBuilder(bytecodeGenerator.getMethodVisitor())
                                    .getHashMapElement(tableAccessExpression.table().symbol().index(), tableAccessExpression.key());
                        })
                        .build();
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }


}
