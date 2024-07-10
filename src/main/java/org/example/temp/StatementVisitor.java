package org.example.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.builtin.FunctionUtil;
import org.example.domain.expression.Expression;
import org.example.luja.compiler.symbol.ContextManager;
import org.example.luja.compiler.symbol.LuaSymbolMetatype;
import org.example.luja.compiler.symbol.LuaVariable;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.VarInsnNode;

import java.util.List;

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
    public InsnList visitFunctioncall(LuaParser.FunctioncallContext ctx) {
        log.info("Visiting functioncall {}", ctx.getText());
        InsnList instructions = new InsnList();
        String functionName = ctx.NAME().getFirst().getText();
        List<Expression> expressions = ctx.args().explist().exp().stream()
                .map(exp -> new LuaExpressionVisitor(contextManager).visit(exp)).toList();
        expressions.forEach(exp -> codeGen.getClassBuilder().loadExpressionOntoStack(exp, instructions));

        instructions.add((FunctionUtil.getFunction(functionName).get()));
        return instructions;
//        return super.visitFunctioncall(ctx);
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
}