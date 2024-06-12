package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.Condition;
import org.example.domain.expression.Expression;
import org.example.domain.expression.VariableExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.statement.Statement;
import org.example.parser.ExpressionVisitor;
import org.example.symbol.Symbol;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class StatementVisitor extends LuaParserBaseVisitor<Statement> {

    private final VariableDeclarationVisitor variableDeclarationVisitor;
    private final ExpressionVisitor expressionVisitor;

    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

    @Override
    public Statement visitStat(LuaParser.StatContext ctx) {
        log.info("Visiting statement {} ", ctx.getText());
        return super.visitChildren(ctx); // ????
    }

    @Override
    public Statement visitVardecl(LuaParser.VardeclContext ctx) {
        return variableDeclarationVisitor.visit(ctx);
    }

    @Override
    public Statement visitForloop(LuaParser.ForloopContext ctx) {
        log.info("Visiting for loop {}", ctx.getText());

        String counter = ctx.NAME().getText();
        Expression init = ctx.exp(0).accept(expressionVisitor);
        Expression limit = ctx.exp(1).accept(expressionVisitor);
        Expression step = ctx.exp(2) != null ? ctx.exp(2).accept(expressionVisitor) : new IntegerExpression(1);
        log.info("Counter: {}", counter);
        log.info("Init: {}", init);
        log.info("Limit: {}", limit);

        // Создать переменную для счетчика цикла
        Symbol counterSymbol = null;
        if (init instanceof IntegerExpression intInit) {
            symbolTable.addLocalVariable(counter, Type.INT_TYPE, "counter");
            counterSymbol = symbolTable.getLocalVariable(counter);
            bytecodeGenerator.declareLocalVariable(
                    counterSymbol.name(), Type.INT_TYPE.getDescriptor(), intInit.value(),
                    counterSymbol.index()
            );
        }

        // Создать переменную для предела цикла
        Symbol limitSymbol = null;
        if (limit instanceof IntegerExpression intLimit) {
            symbolTable.addLocalVariable(counter + "_limit", Type.INT_TYPE, "limit");
            limitSymbol = symbolTable.getLocalVariable(counter + "_limit");
            bytecodeGenerator.declareLocalVariable(
                    limitSymbol.name(), Type.INT_TYPE.getDescriptor(), intLimit.value(),
                    limitSymbol.index()
            );
        }

        bytecodeGenerator.generateForLoop(counterSymbol, limitSymbol, () -> {
            log.info("Generating for loop block");
            visit(ctx.block());
        });

        return null;
    }

    @Override
    public Statement visitWhileloop(LuaParser.WhileloopContext ctx) {
        log.info("Visiting while loop {}", ctx.getText());
        Expression condition = expressionVisitor.visit(ctx.exp());

        bytecodeGenerator.generateWhileLoop(condition, () -> visit(ctx.block()));

        return null;
    }

    @Override
    public Statement visitIfstat(LuaParser.IfstatContext ctx) {
        log.info("Visiting if statement {}", ctx.getText());

        // Формируем условия
        List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < ctx.exp().size(); i++) {
            log.info("Visiting condition: {}", ctx.exp(i).getText());
            LuaParser.ExpContext expContext = ctx.exp(i);
            LuaParser.BlockContext blockContext = ctx.block(i);
            conditions.add(new Condition(
                    expressionVisitor.visit(expContext),
                    () -> visit(blockContext)
            ));
        }

        // Формируем блок else
        Runnable elseBlock = ctx.block().size() > ctx.exp().size()
                ? () -> visit(ctx.block(ctx.block().size() - 1))
                : null;

        bytecodeGenerator.generateIfElseStatement(conditions, elseBlock);

        return null;
    }

    @Override
    public Statement visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        log.info("Visiting function call");
        if (ctx.NAME() != null && ctx.NAME(0).getText().equals("print")) {
            log.info("Encountered print statement");
            var arg = ctx.args().explist().exp(0);
            Expression text = arg.accept(expressionVisitor);
            if (text instanceof VariableExpression var) {
                bytecodeGenerator.generatePrint(var.symbol());
            } else {
                bytecodeGenerator.generatePrint(text);
            }
        }
        return null;
    }

    @Override
    public Statement visitBlock(LuaParser.BlockContext ctx) {
        ctx.stat().forEach(this::visit);
        return null;
    }
}
