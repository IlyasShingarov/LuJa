package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.Variable;
import org.example.domain.expression.Expression;
import org.example.domain.expression.FloatExpression;
import org.example.domain.expression.IntegerExpression;
import org.example.domain.expression.Value;
import org.example.domain.statement.VariableDeclaration;
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
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }
}
