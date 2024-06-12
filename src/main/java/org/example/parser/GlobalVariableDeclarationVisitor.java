package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.StaticVariableGenerator;
import org.example.domain.expression.constant.BooleanExpression;
import org.example.domain.expression.Expression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalVariableDeclarationVisitor extends LuaParserBaseVisitor<Void> {

    private final ExpressionVisitor expressionVisitor;
    private final StaticVariableGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;



    @Override
    public Void visitVardecl(LuaParser.VardeclContext ctx) {
        if (ctx.LOCAL() == null) {
            log.info("Encountered global variable declaration with names: {}", ctx.varlist().getText());
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

        return super.visitVardecl(ctx);
    }

    private void createGlobalVariable(String name, Expression value) {
        switch (value) {
            case IntegerExpression integerExpression -> {
                symbolTable.addLocalVariable(name, Type.INT_TYPE, "global");
                bytecodeGenerator.declareGlobalVariable(name, "I", integerExpression.value());
            }
            case BooleanExpression booleanExpression -> {
                symbolTable.addLocalVariable(name, Type.BOOLEAN_TYPE,"global");
                bytecodeGenerator.declareGlobalVariable(name, "Z", booleanExpression.value());
            }
            case StringExpression stringExpression -> {
                symbolTable.addLocalVariable(name, Type.getType(String.class),"global");
                bytecodeGenerator.declareGlobalVariable(name, "Ljava/lang/String;", stringExpression.value());
            }
            default -> throw new IllegalStateException("Unexpected value: " + value);
        }
    }

    @Override
    public Void visitBlock(LuaParser.BlockContext ctx) {
        super.visitBlock(ctx);
        bytecodeGenerator.generateEnd();
        return null;
    }

    @Override
    public Void visitStat(LuaParser.StatContext ctx) {
        if (ctx.vardecl() != null) {
            visitVardecl(ctx.vardecl());
        }
        return null;
    }
}
