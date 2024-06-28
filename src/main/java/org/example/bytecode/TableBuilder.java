package org.example.bytecode;

import lombok.RequiredArgsConstructor;
import org.example.domain.expression.Expression;
import org.example.domain.expression.TableExpression;
import org.example.domain.expression.constant.BooleanExpression;
import org.example.domain.expression.constant.FloatExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

@RequiredArgsConstructor
public class TableBuilder implements Opcodes {

    private final MethodVisitor mv;

    public TableBuilder createTable(int index) {
        mv.visitTypeInsn(NEW, "java/util/HashMap");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false);
        mv.visitVarInsn(ASTORE, index);
        return this;
    }

    public TableBuilder loadTable(int index) {
        mv.visitVarInsn(ALOAD, index);
        return this;
    }

    public TableBuilder pointAt(String key) {
        mv.visitLdcInsn(key);
        return this;
    }

    public TableBuilder setElement(Expression value) {
        loadValueOntoStack(value);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
        mv.visitInsn(POP); // Удаляем результат put с вершины стека
        return this;
    }

    public TableBuilder putHashMapElement(int mapIndex, Expression key, Expression value) {
        mv.visitVarInsn(ALOAD, mapIndex);
        new ExpressionBuilder(mv).loadExpression(key);
        loadValueOntoStack(value);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", false);
        mv.visitInsn(POP); // Удаляем результат put с вершины стека
        return this;
    }

    public void getHashMapElement(int mapIndex, Expression key) {
        mv.visitVarInsn(ALOAD, mapIndex);
        new ExpressionBuilder(mv).loadExpression(key);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
    }

//    public void getHashMapElement(int mapIndex, String key) {
//        mv.visitVarInsn(ALOAD, mapIndex);
//        new ExpressionBuilder(mv).loadExpression(new StringExpression(key));
//        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
//    }

    public void getHashMapElement(int mapIndex, String key) {
        mv.visitVarInsn(ALOAD, mapIndex);
        new ExpressionBuilder(mv).loadExpression(new StringExpression(key));
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false);
    }

    private void loadValueOntoStack(Expression value) {
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
            case null, default ->
                    throw new IllegalStateException("Unsupported expression type: " + value.getClass().getName());
        }
    }

}
