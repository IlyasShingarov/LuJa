package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.*;
import org.example.domain.statement.VariableDeclaration;
import org.example.parser.ExpressionVisitor;
import org.example.symbol.Symbol;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VariableDeclarationVisitor extends LuaParserBaseVisitor<VariableDeclaration> {

    private final ExpressionVisitor expressionVisitor;
    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

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
            List<String> varnames = ctx.varlist().var().stream()
                    .map(var -> var.NAME().getText())
                    .toList();
            log.info("Global variable names: {}", varnames);

            List<Expression> values = ctx.explist().exp().stream()
                    .map(exp -> exp.accept(expressionVisitor))
                    .toList();
            log.info("Values: {}", values);

            for (int i = 0; i < varnames.size(); i++) {
                createLocalVariable(varnames.get(i), values.get(i));
            }
        }
        return null;
    }

    private void createLocalVariable(String name, Expression value) {
        switch (value) {
            case ConstantExpression constantExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, constantExpression.getType());
                bytecodeGenerator.declareLocalVariable(name, constantExpression.getType().getDescriptor(),
                        constantExpression.value(), index);
            }
            case VariableExpression variableExpression -> {
                int index = symbolTable.getLocalVariableIndex(name, variableExpression.symbol().type());
                Symbol variableSymbol = variableExpression.symbol();
                bytecodeGenerator.moveLocalVariable(variableSymbol.index(), index, variableExpression.symbol().type().getDescriptor());
            }
            case ArrayTableExpression arrayTableExpression -> {
                log.info("Processing array table expression: {}", arrayTableExpression);
                log.info("Type of array table expression: {}", arrayTableExpression.getType());

                int index = symbolTable.getLocalVariableIndex(name, arrayTableExpression.getType());
                bytecodeGenerator.createTable(arrayTableExpression.size(), index, arrayTableExpression.getElementType());
                for (int elementIndex = 0; elementIndex < arrayTableExpression.size(); elementIndex++) {
                    bytecodeGenerator.setTableValue(index, elementIndex, arrayTableExpression.elements().get(elementIndex));
                }
            }
            case ArrayAccessExpression arrayAccessExpression -> {
                VariableExpression arrayVar = arrayAccessExpression.array();
                Expression indexExpression = arrayAccessExpression.index();
                switch (indexExpression) {
                    case IntegerExpression integerExpression -> {
                        int index = symbolTable.getLocalVariableIndex(name, Type.INT_TYPE);
                        bytecodeGenerator.getIntArrayElement(arrayVar.symbol().index(), integerExpression.value());
                        bytecodeGenerator.storeLocalVariable(index, Type.INT_TYPE.getDescriptor());
                    }
                    case VariableExpression variableExpression -> {
                        int index = symbolTable.getLocalVariableIndex(name, variableExpression.symbol().type());

                        bytecodeGenerator.getIntArrayElement(arrayVar.symbol().index(), variableExpression.symbol().index());
                        bytecodeGenerator.storeLocalVariable(index, variableExpression.symbol().type().getDescriptor());
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
                        bytecodeGenerator.generateBinaryOperation(
                                binaryExpression.left(), binaryExpression.right(),
                                binaryOperation.getOpcode(type)
                        );

                        bytecodeGenerator.storeLocalVariable(index, type.getDescriptor());
                    }
                    case EQUALS, NOT_EQUALS, LESS_THAN, LESS_THAN_OR_EQUALS, GREATER_THAN, GREATER_THAN_OR_EQUALS -> {
                        Type type = binaryExpression.left().getType();
                        int index = symbolTable.getLocalVariableIndex(name, Type.BOOLEAN_TYPE);
                        bytecodeGenerator.generateComparison(
                                binaryExpression.left(), binaryExpression.right(),
                                binaryOperation
                        );

                        bytecodeGenerator.storeLocalVariable(index, Type.BOOLEAN_TYPE.getDescriptor());
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }
}
