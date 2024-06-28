package org.example.bytecode;

import lombok.RequiredArgsConstructor;
import org.example.bytecode.statement.VariableBuilder;
import org.example.domain.expression.ArrayAccessExpression;
import org.example.domain.expression.Expression;
import org.example.domain.expression.VariableExpression;
import org.example.domain.expression.constant.BooleanExpression;
import org.example.domain.expression.constant.FloatExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@RequiredArgsConstructor
public class ArrayBuilder implements Opcodes {

    private final MethodVisitor mv;

    public ArrayBuilder loadArray(int index) {
        mv.visitVarInsn(ALOAD, index);
        return this;
    }

    public ArrayBuilder pointAt(int index) {
        mv.visitIntInsn(BIPUSH, index);
        return this;
    }

    public ArrayBuilder pointAt(VariableExpression expression) {
        mv.visitVarInsn(Opcodes.ILOAD, expression.symbol().index());
        return this;
    }

    public ArrayBuilder setElement(Expression expression) {
        loadValueOntoStack(expression);
        mv.visitInsn(AASTORE);
        return this;
    }

    public ArrayBuilder setElement(Runnable loader) {
        loader.run();
        mv.visitInsn(AASTORE);
        return this;
    }

    public ArrayBuilder store() {
        mv.visitInsn(AASTORE);
        return this;
    }

    public void loadValueOntoStack(Expression value) {
        switch (value) {
            case IntegerExpression integerExpression -> {
                mv.visitTypeInsn(Opcodes.NEW, "java/lang/Integer");
                mv.visitInsn(Opcodes.DUP);
                mv.visitLdcInsn(integerExpression.value());
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V", false);
            }
            case FloatExpression floatExpression -> {
                mv.visitTypeInsn(Opcodes.NEW, "java/lang/Float");
                mv.visitInsn(Opcodes.DUP);
                mv.visitLdcInsn(floatExpression.value());
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Float", "<init>", "(F)V", false);
            }
            case BooleanExpression booleanExpression -> {
                mv.visitTypeInsn(Opcodes.NEW, "java/lang/Boolean");
                mv.visitInsn(Opcodes.DUP);
                mv.visitLdcInsn(booleanExpression.value() ? Boolean.TRUE : Boolean.FALSE);
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Boolean", "<init>", "(Z)V", false);
            }
            case StringExpression stringExpression -> mv.visitLdcInsn(stringExpression.value());
            case VariableExpression variableExpression -> {
                if (variableExpression.getType().getSort() == Type.INT) {
                    mv.visitTypeInsn(Opcodes.NEW, "java/lang/Integer");
                    mv.visitInsn(Opcodes.DUP);
                    mv.visitVarInsn(Opcodes.ILOAD, variableExpression.symbol().index());
                    mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V", false);
                } else if (variableExpression.getType() == Type.getType(Object.class)) {
                    mv.visitVarInsn(Opcodes.ALOAD, variableExpression.symbol().index());
                }
            }
            case ArrayAccessExpression arrayAccessExpression -> {
                new ExpressionBuilder(mv)
                        .getIntArrayElement(arrayAccessExpression.array().symbol().index(), arrayAccessExpression.index());
            }
            case null, default ->
                    throw new IllegalStateException("Unsupported expression type: " + value.getClass().getName());
        }
    }

}
