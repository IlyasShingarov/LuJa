package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.Variable;
import org.example.domain.expression.*;
import org.example.domain.statement.VariableDeclaration;
import org.example.symbol.Symbol;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        } else {
            log.info("Encountered global variable declaration");
            List<String> varnames = ctx.varlist().var().stream()
                    .map(var -> var.NAME().getText())
                    .toList();
            log.info("Global variable names: {}", varnames);

            List<Expression> values = ctx.explist().exp().stream()
                    .map(exp -> exp.accept(expressionVisitor))
                    .toList();

            log.info("Values: {}", values);

            for (int i = 0; i < varnames.size(); i++) {
                createGlobalVariable(varnames.get(i), values.get(i));
            }
        }

        log.info("Returning from variable declaration");
        return null;
    }

    private void createGlobalVariable(String name, Expression value) {
        switch (value) {
            case IntegerExpression integerExpression -> {
                bytecodeGenerator.declareGlobalVariable(name, "I", integerExpression.value());
            }
            case FloatExpression floatExpression -> {
                bytecodeGenerator.declareGlobalVariable(name, "F", floatExpression.value());
            }
            case BooleanExpression booleanExpression -> {
                bytecodeGenerator.declareGlobalVariable(name, "Z", booleanExpression.value());
            }
            case StringExpression stringExpression -> {
                bytecodeGenerator.declareGlobalVariable(name, "Ljava/lang/String;", stringExpression.value());
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    private void createLocalVariable(String name, Expression value) {
        switch (value) {
            case IntegerExpression integerExpression -> {
                int index = getLocalVariableIndex(name, Type.INT_TYPE);
                bytecodeGenerator.declareLocalVariable(name, "I", integerExpression.value(), index);
            }
            case FloatExpression floatExpression -> {
                int index = getLocalVariableIndex(name, Type.FLOAT_TYPE);
                bytecodeGenerator.declareLocalVariable(name, "F", floatExpression.value(), index);
            }
            case BooleanExpression booleanExpression -> {
                int index = getLocalVariableIndex(name, Type.BOOLEAN_TYPE);
                bytecodeGenerator.declareLocalVariable(name, "Z", booleanExpression.value(), index);
            }
            case StringExpression stringExpression -> {
                int index = getLocalVariableIndex(name, Type.getType(String.class));
                bytecodeGenerator.declareLocalVariable(name, "Ljava/lang/String;", stringExpression.value(), index);
            }
            case VariableExpression variableExpression -> {
                int index = getLocalVariableIndex(name, variableExpression.symbol().type());
                Symbol variableSymbol = variableExpression.symbol();
                bytecodeGenerator.moveLocalVariable(variableSymbol.index(), index, variableExpression.symbol().type().getDescriptor());
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    private int getLocalVariableIndex(String name, Type type) {
        symbolTable.addLocalVariable(name, type,"LOCALVARIABLE");
        Symbol symbol = symbolTable.getLocalVariable(name);
        if (symbol == null) {
            throw new RuntimeException("Undefined variable: " + name);
        }
        return symbol.index();
    }
}
