package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.expression.Expression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.statement.ForLoop;
import org.example.parser.ExpressionVisitor;
import org.example.parser.InnerBlockVisitor;
import org.example.symbol.Symbol;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ForLoopVisitor extends LuaParserBaseVisitor<ForLoop> {

    private final ExpressionVisitor expressionVisitor;
    private final InnerBlockVisitor innerBlockVisitor;

    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

    @Override
    public ForLoop visitForloop(LuaParser.ForloopContext ctx) {
        log.info("Visiting for loop {}", ctx.getText());
        log.info("Init expression: {}", ctx.exp(0).getText());
        log.info("Limit expression: {}", ctx.exp(1).getText());
        log.info("Block: {}", ctx.block().getText());

        String counter = ctx.NAME().getText();
        Expression init = ctx.exp(0).accept(expressionVisitor);
        Expression limit = ctx.exp(1).accept(expressionVisitor);
        Expression step = ctx.exp(2) != null ? ctx.exp(2).accept(expressionVisitor) : new IntegerExpression(1);
        log.info("Counter: {}", counter);
        log.info("Init: {}", init);
        log.info("Limit: {}", limit);

//        symbolTable.enterScope();
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
            innerBlockVisitor.visitBlock(ctx.block());
        });

        return super.visitForloop(ctx);
    }

    @Override
    public ForLoop visitExp(LuaParser.ExpContext ctx) {
        return super.visitExp(ctx);
    }

    //    @Override
//    public Statement visitForloop(LuaParser.ForloopContext ctx) {
//        log.info("Visiting for loop");
//        symbolTable.enterScope();
//        log.info("For loop context: {}", ctx.getText());
//        String counter = ctx.NAME().getText();
//        log.info("Variable name: {}", varName);
//        Expression initExpr = ctx.exp(0).accept(expressionVisitor);
//
//        log.info("Init expression: {}", initExpr);
//        Expression limitExpr = ctx.exp(1).accept(expressionVisitor);
//        log.info("Limit expression: {}", limitExpr);
//        //        Expression stepExpr = ctx.exp(2) != null ? visitExpression(ctx.exp(2)) : new ConstantExpression(1);
//
//        // Создание и инициализация переменной цикла
////        int varIndex = getLocalVariableIndex(varName);
////        generateExpression(initExpr);
////        mv.visitVarInsn(Opcodes.ISTORE, varIndex);
//
//
//        symbolTable.exitScope();
//        return super.visitForloop(ctx);
//    }
}
