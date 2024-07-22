package org.example.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.builtin.FunctionUtil;
import org.example.domain.Condition;
import org.example.domain.expression.BinaryExpression;
import org.example.domain.expression.Expression;
import org.example.domain.expression.FunctionExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.luja.compiler.symbol.ContextManager;
import org.example.luja.compiler.symbol.MainContextManager;
import org.example.luja.compiler.symbol.LuaVariable;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Slf4j
@RequiredArgsConstructor
public class StatementVisitor extends LuaParserBaseVisitor<InsnList> implements Opcodes {

    private final ContextManager contextManager;
    private final CodeGen codeGen;

    @Override
    public InsnList visitStat(LuaParser.StatContext ctx) {
        if (ctx.SEMI() != null) return new InsnList();

        return super.visitStat(ctx);
    }

    @Override
    public InsnList visitIfstat(LuaParser.IfstatContext ctx) {
        log.info("Visiting if statement {}", ctx.getText());

        // Формирование условий
        List<Condition> conditions = new ArrayList<>(ctx.exp().size());
        for (int i = 0; i < ctx.exp().size(); i++) {
            Expression conditionExression = new LuaExpressionVisitor(contextManager).visit(ctx.exp(i));
            LuaParser.BlockContext block = ctx.block(i);
            conditions.add(new Condition(
                    conditionExression,
                    () -> visit(block)
            ));
        }

        // Формирование блока else
        Supplier<InsnList> elseBlock = ctx.block().size() > ctx.exp().size()
                ? () -> visit(ctx.block(ctx.block().size() - 1))
                : null;

        return generateIfStatement(conditions, elseBlock);
    }

    private InsnList generateIfStatement(List<Condition> conditions, Supplier<InsnList> elseBlock) {
        InsnList instructions = new InsnList();

        // Метка для конца всех условий
        LabelNode endLabel = new LabelNode();

        for (Condition condition : conditions) {
            LabelNode nextLabel = new LabelNode();

            if (condition.condition() instanceof BinaryExpression binaryExpression) {
                // Вычисление условия. На стеке остается значение либо true, либо false
                codeGen.getClassBuilder().loadExpressionOntoStack(binaryExpression, instructions);
                instructions.add(new TypeInsnNode(CHECKCAST, "java/lang/Boolean"));
                instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false));
            }

            instructions.add(new JumpInsnNode(Opcodes.IFEQ, nextLabel));
            instructions.add(condition.blockGenerator().get());
            instructions.add(new JumpInsnNode(Opcodes.GOTO, endLabel));
            instructions.add(nextLabel);
        }

        if (elseBlock != null) { instructions.add(elseBlock.get()); }

        instructions.add(endLabel);
        return instructions;
    }

    @Override
    public InsnList visitWhileloop(LuaParser.WhileloopContext ctx) {
        Expression condition = new LuaExpressionVisitor(contextManager).visit(ctx.exp());
        return generateWhileLoop(condition, () -> visit(ctx.block()));
    }

    private InsnList generateWhileLoop(Expression condition, Supplier<InsnList> blockGenerator) {
        InsnList instructions = new InsnList();

        LabelNode startLabel = new LabelNode();
        LabelNode endLabel = new LabelNode();

        instructions.add(startLabel);
        codeGen.getClassBuilder().loadExpressionOntoStack(condition, instructions);
        instructions.add(new TypeInsnNode(CHECKCAST, "java/lang/Boolean"));
        instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false));
        instructions.add(new JumpInsnNode(Opcodes.IFEQ, endLabel));
        instructions.add(blockGenerator.get());
        instructions.add(new JumpInsnNode(Opcodes.GOTO, startLabel));
        instructions.add(endLabel);

        return instructions;
    }

    @Override
    public InsnList visitForloop(LuaParser.ForloopContext ctx) {
        contextManager.enterScope();
        InsnList instructions = new InsnList();
        String counterName = ctx.NAME().getText() + "_counter";
        String limitName = ctx.NAME().getText() + "_limit";
        String stepName = ctx.NAME().getText() + "_step";
        contextManager.addVariable(ctx.NAME().getText());
        contextManager.addVariable(counterName);
        contextManager.addVariable(limitName);
        contextManager.addVariable(stepName);

        Expression startExpression = new LuaExpressionVisitor(contextManager).visit(ctx.exp(0));
        Expression endExpression = new LuaExpressionVisitor(contextManager).visit(ctx.exp(1));
        Expression stepExpression = ctx.exp().size() > 2
                ? new LuaExpressionVisitor(contextManager).visit(ctx.exp(2))
                : new IntegerExpression( 1);


        LuaVariable counterVar = contextManager.getCurrentScope().getVariable(counterName);
        codeGen.getClassBuilder().loadExpressionOntoStack(startExpression, instructions);
        instructions.add(new VarInsnNode(ASTORE, counterVar.index()));

        LuaVariable limitVar = contextManager.getCurrentScope().getVariable(limitName);
        codeGen.getClassBuilder().loadExpressionOntoStack(endExpression, instructions);
        instructions.add(new VarInsnNode(ASTORE, limitVar.index()));

        LuaVariable stepVar = contextManager.getCurrentScope().getVariable(stepName);
        codeGen.getClassBuilder().loadExpressionOntoStack(stepExpression, instructions);
        instructions.add(new VarInsnNode(ASTORE, stepVar.index()));

        instructions.add(generateForLoop(
                counterVar, limitVar, stepVar,
                () -> visit(ctx.block())
        ));

        contextManager.exitScope();
        return instructions;
    }

    private InsnList generateForLoop(LuaVariable counterVar, LuaVariable limitVar, LuaVariable stepVar, Supplier<InsnList> blockGenerator) {
        InsnList instructions = new InsnList();

        LabelNode startLabel = new LabelNode();
        LabelNode endLabel = new LabelNode();

        instructions.add(startLabel);
        instructions.add(new VarInsnNode(ALOAD, counterVar.index()));
        instructions.add(new VarInsnNode(ALOAD, limitVar.index()));
        instructions.add(codeGen.getClassBuilder().makeInvokeDynamic("less_than_or_equals"));
        instructions.add(new TypeInsnNode(CHECKCAST, "java/lang/Boolean"));
        instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/lang/Boolean", "booleanValue", "()Z", false));
        instructions.add(new JumpInsnNode(Opcodes.IFEQ, endLabel));

        LuaVariable counter = contextManager.getCurrentScope().getVariable(counterVar.name().substring(0, counterVar.name().length() - 8));
        instructions.add(new VarInsnNode(ALOAD, counterVar.index()));
        instructions.add(new VarInsnNode(ASTORE, counter.index()));
        instructions.add(blockGenerator.get());

        instructions.add(new VarInsnNode(ALOAD, counterVar.index()));
        instructions.add(new VarInsnNode(ALOAD, stepVar.index()));
        instructions.add(codeGen.getClassBuilder().makeInvokeDynamic("add"));
        instructions.add(new VarInsnNode(ASTORE, counterVar.index()));
        instructions.add(new JumpInsnNode(Opcodes.GOTO, startLabel));
        instructions.add(endLabel);

        return instructions;
    }

//        // Генерация кода инициализации
//        if (init != null) {
//            loadLocalVariable(init.index(), init.type().getDescriptor());
//            mv.visitVarInsn(Opcodes.ISTORE, init.index());
//        }
//
//        // Метка начала цикла (условие)
//        mv.visitLabel(startLoop);
//
//        // Генерация кода условия и проверки
//        if (limit != null) {
//            loadLocalVariable(init.index(), Type.INT_TYPE.getDescriptor());
//            loadLocalVariable(limit.index(), limit.type().getDescriptor());
//            mv.visitJumpInsn(Opcodes.IF_ICMPGT, endLoop); // Переход к метке конца, если условие ложно
//        }
//
//        // Генерация тела цикла
//        bodyGenerator.run();
//
//        // Обновление переменной
//        loadLocalVariable(init.index(), Type.INT_TYPE.getDescriptor());
//        mv.visitLdcInsn(1); // Пока шаг только 1
//        mv.visitInsn(Opcodes.IADD);
//        mv.visitVarInsn(Opcodes.ISTORE, init.index());
//
//        // Переход к началу цикла (условие)
//        mv.visitJumpInsn(Opcodes.GOTO, startLoop);
//
//        // Метка конца цикла
//        mv.visitLabel(endLoop);
//    }

    @Override
    public InsnList visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        log.info("Visiting functioncall {}", ctx.getText());
        InsnList instructions = new InsnList();
        String functionName = ctx.NAME().getFirst().getText();
        LuaParser.ExplistContext explist = ctx.args().explist();
        List<Expression> expressions = new ArrayList<>();
        if (explist != null) {
            expressions = explist.exp().stream()
                    .map(exp -> new LuaExpressionVisitor(contextManager).visit(exp)).toList();
        }

        try {
            InsnList functionCode = FunctionUtil.getFunction(functionName).get();
            expressions.forEach(exp -> codeGen.getClassBuilder().loadExpressionOntoStack(exp, instructions));
            instructions.add(functionCode);
        } catch (Exception e) {
            log.warn("Function is not builtin");
            codeGen.getClassBuilder().loadExpressionOntoStack(
                    new FunctionExpression(functionName, expressions),
                    instructions
            );
        }
        return instructions;
    }

    @Override
    public InsnList visitVardecl(LuaParser.VardeclContext ctx) {
        log.info("Visiting vardecl {}", ctx.getText());
        // TODO Add list handling
        InsnList instructions = new InsnList();

        LuaVariable variable;
        if (ctx.LOCAL() != null) {
            variable = contextManager.getCurrentScope().getVariable(ctx.attnamelist().NAME(0).getText());
        } else {
            variable = contextManager.getCurrentScope().getVariable(ctx.varlist().var(0).NAME().getText());
        }

        Expression expression = new LuaExpressionVisitor(contextManager).visit(ctx.explist().exp(0));
        switch (variable.metaType()) {
            case GLOBAL -> {
                if (expression.hasVariableExpression()) {
                    codeGen.getClassBuilder().loadExpressionOntoStack(expression, instructions);
                    var classname = codeGen.getClassBuilder().getClassNode().name;
                    var fieldname = variable.name();
                    instructions.add(new FieldInsnNode(PUTSTATIC, classname, fieldname, Type.getDescriptor(Object.class)));
                }
            }
            case LOCAL -> {
                codeGen.getClassBuilder().loadExpressionOntoStack(expression, instructions);
                instructions.add(new VarInsnNode(ASTORE, variable.index()));
            }
        }
        return instructions;

//        return super.visitVardecl(ctx);
    }

    @Override
    public InsnList visitBlock(LuaParser.BlockContext ctx) {
        InsnList insnNodes = new InsnList();
        for (LuaParser.StatContext statContext : ctx.stat()) {
            insnNodes.add(visit(statContext));
        }
        if (ctx.retstat() != null) {
            insnNodes.add(visit(ctx.retstat()));
        }
        return insnNodes;
    }

    @Override
    public InsnList visitRetstat(LuaParser.RetstatContext ctx) {
        InsnList instructions = new InsnList();
        if (ctx.explist() != null) {
            Expression expression = new LuaExpressionVisitor(contextManager).visit(ctx.explist().exp(0));
            codeGen.getClassBuilder().loadExpressionOntoStack(expression, instructions);
        } else {
            instructions.add(new InsnNode(ACONST_NULL));
        }
        instructions.add(new InsnNode(ARETURN));
        return instructions;
    }

    @Override
    public InsnList visitFuncdecl(LuaParser.FuncdeclContext ctx) {
        // If method does not exist throw an error
        codeGen.getClassBuilder().getClassNode().methods.stream()
                .filter(m -> m.name.equals(ctx.funcname().getText()))
                .findFirst()
                .ifPresentOrElse(
                    methodNode -> log.info("Method {} already exists", ctx.funcname().getText()),
                    () -> log.info("Method {} does not exist", ctx.funcname().getText())
                );
        return new InsnList();
    }
}