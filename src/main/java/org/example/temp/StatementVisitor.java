package org.example.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.builtin.FunctionUtil;
import org.example.domain.Condition;
import org.example.domain.expression.BinaryExpression;
import org.example.domain.expression.Expression;
import org.example.luja.compiler.symbol.ContextManager;
import org.example.luja.compiler.symbol.LuaSymbolMetatype;
import org.example.luja.compiler.symbol.LuaVariable;
import org.objectweb.asm.Label;
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

        InsnList instructions = generateIfStatement(conditions, elseBlock);

        return instructions;
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
    public InsnList visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        log.info("Visiting functioncall {}", ctx.getText());
        InsnList instructions = new InsnList();
        String functionName = ctx.NAME().getFirst().getText();
        List<Expression> expressions = ctx.args().explist().exp().stream()
                .map(exp -> new LuaExpressionVisitor(contextManager).visit(exp)).toList();
        expressions.forEach(exp -> codeGen.getClassBuilder().loadExpressionOntoStack(exp, instructions));

        instructions.add((FunctionUtil.getFunction(functionName).get()));
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
}