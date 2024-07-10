package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.ExpressionBuilder;
import org.example.bytecode.FunctionCallBuilder;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.bytecode.statement.VariableBuilder;
import org.example.domain.Condition;
import org.example.domain.expression.Expression;
import org.example.domain.expression.VariableExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.example.domain.statement.Statement;
import org.example.parser.expression.ExpressionEvalVisitor;
import org.example.parser.expression.ExpressionTypeVisitor;
import org.example.parser.expression.ExpressionVisitor;
import org.example.symbol.VariableSymbol;
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
    private final ExpressionEvalVisitor expressionEvalVisitor;
    private final ExpressionTypeVisitor expressionTypeVisitor;

    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

    @Override
    public Statement visitStat(LuaParser.StatContext ctx) {
        log.info("Visiting statement {} ", ctx.getText());
        return super.visitChildren(ctx);
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
        VariableSymbol counterSymbol = null;
        if (init instanceof IntegerExpression || init instanceof VariableExpression) {
            symbolTable.addLocalVariable(counter, Type.INT_TYPE, "counter");
            counterSymbol = symbolTable.getLocalVariable(counter);
            new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                    .withValue(() -> new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                            .loadExpression(init)
                    ).storeLocal(counterSymbol.index(), Type.INT_TYPE.getDescriptor());
//            bytecodeGenerator.declareLocalVariable(
//                    counterSymbol.name(), Type.INT_TYPE.getDescriptor(), intInit.value(),
//                    counterSymbol.index()
//            );
//        } else if (init instanceof VariableExpression varInit) {
//            symbolTable.addLocalVariable(counter, Type.INT_TYPE, "counter");
//            counterSymbol = symbolTable.getLocalVariable(counter);
//            new VariableBuilder(bytecodeGenerator.getMethodVisitor())
//                    .withValue(() -> new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                            .loadExpression(varInit)
//                    ).storeLocal(counterSymbol.index(), Type.INT_TYPE.getDescriptor());
        }

        // Создать переменную для предела цикла
        VariableSymbol limitSymbol = null;
        if (limit instanceof IntegerExpression || limit instanceof VariableExpression) {
            symbolTable.addLocalVariable(counter + "_limit", Type.INT_TYPE, "limit");
            limitSymbol = symbolTable.getLocalVariable(counter + "_limit");
            new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                    .withValue(() -> new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                            .loadExpression(limit)
                    ).storeLocal(limitSymbol.index(), Type.INT_TYPE.getDescriptor());
//            bytecodeGenerator.declareLocalVariable(
//                    limitSymbol.name(), Type.INT_TYPE.getDescriptor(), intLimit.value(),
//                    limitSymbol.index()
//            );
        } else {
            symbolTable.addLocalVariable(counter + "_limit", Type.INT_TYPE, "limit");
            limitSymbol = symbolTable.getLocalVariable(counter + "_limit");
            new VariableBuilder(bytecodeGenerator.getMethodVisitor())
                    .withValue(() -> expressionEvalVisitor.visit(ctx.exp(1)))
                    .storeLocal(limitSymbol.index(), Type.INT_TYPE.getDescriptor());
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
            var arg  = ctx.args().explist().exp(0);
            Expression text = arg.accept(expressionVisitor);
            if (text instanceof VariableExpression var) {
//                log.info("Printing variable: {}", var.symbol());
//                bytecodeGenerator.generatePrint(var.symbol());
//                bytecodeGenerator.generatePrint(text);
                return null;
            }

            Type type = expressionTypeVisitor.visit(ctx.args().explist().exp(0));
            expressionEvalVisitor.visit(ctx.args().explist());
            bytecodeGenerator.printStackValue(type);
//            if (text instanceof VariableExpression var) {
//                log.info("Printing variable: {}", var.symbol());
//                bytecodeGenerator.generatePrint(var.symbol());
//            } else {
//                bytecodeGenerator.generatePrint(text);
//            }
        } else if (ctx.NAME(0) != null && ctx.NAME(1) != null) {
            if (ctx.NAME(0).getText().equals("io") && ctx.NAME(1).getText().equals("read")) {
                Expression arg = ctx.args().explist().exp(0).accept(expressionVisitor);
                StringExpression type = (StringExpression) arg;
                bytecodeGenerator.generateIoRead(type.value());
            }
        } else {
            String functionName = ctx.NAME(0).getText();
            log.info("Function name: {}", functionName);
            List<Expression> arguments = new ArrayList<>();
            if (ctx.args().explist() != null) {
                arguments = ctx.args().explist().exp().stream()
                        .map(exp -> exp.accept(expressionVisitor))
                        .toList();
            }
            log.info("Arguments: {}", arguments);
            new FunctionCallBuilder(bytecodeGenerator.getMethodVisitor())
                    .putArguments(arguments)
                    .forgetArguments()
                    .callFunction(symbolTable.getFunction(functionName));
        }

        return null;
    }

    @Override
    public Statement visitFuncdecl(LuaParser.FuncdeclContext ctx) {
        return null;
    }

    @Override
    public Statement visitRetstat(LuaParser.RetstatContext ctx) {
        if (ctx.explist() != null) {
//            List<Expression> returnValues = ctx.explist().exp().stream()
//                    .map(exp -> exp.accept(expressionVisitor))
//                    .toList();
//
//            Expression returnValue = returnValues.getFirst();
            Type returnType = expressionTypeVisitor.visit(ctx.explist().exp(0));
            expressionEvalVisitor.visit(ctx.explist().exp(0));
            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
//                    .loadExpression(returnValue)
                    .addReturn(returnType);
        } else {
            new ExpressionBuilder(bytecodeGenerator.getMethodVisitor())
                    .addReturn(Type.VOID_TYPE);
        }
        return null;
    }

    @Override
    public Statement visitBlock(LuaParser.BlockContext ctx) {
        ctx.stat().forEach(this::visit);
        if (ctx.retstat() != null) {
            visit(ctx.retstat());
        }
        return null;
    }
}
