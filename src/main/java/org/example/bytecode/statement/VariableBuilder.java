package org.example.bytecode.statement;

import lombok.RequiredArgsConstructor;
import org.example.domain.expression.Expression;
import org.example.domain.expression.constant.BooleanExpression;
import org.example.domain.expression.constant.FloatExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@RequiredArgsConstructor
public class VariableBuilder implements Opcodes {

    private final MethodVisitor mv;
    private String descriptor;
    private int index;
    private boolean isLocalVariable = false;
    private Object value;

    public VariableBuilder isLocal() {
        isLocalVariable = true;
        return this;
    }

    public VariableBuilder withDescriptor(String descriptor) {
        this.descriptor = descriptor;
        return this;
    }

    public VariableBuilder withIndex(int index) {
        this.index = index;
        return this;
    }

    public VariableBuilder withValue(Object value) {
        this.value = value;
        return this;
    }

    public VariableBuilder moveFrom(int fromIndex, String descriptor) {
        loadLocalVariable(fromIndex, descriptor);
        storeLocalVariable(index, descriptor);
        return this;
    }

    public VariableBuilder initArray(int size, Type elementType) {
        mv.visitLdcInsn(size);
        switch (elementType.getSort()) {
            case Type.INT -> mv.visitTypeInsn(ANEWARRAY, "java/lang/Integer");
            case Type.FLOAT -> mv.visitTypeInsn(ANEWARRAY, "java/lang/Float");
            case Type.BOOLEAN -> mv.visitTypeInsn(ANEWARRAY, "java/lang/Boolean");
            case Type.OBJECT -> mv.visitTypeInsn(ANEWARRAY, "java/lang/Object");
            default -> throw new IllegalArgumentException("Unsupported element type: " + elementType);
        }
        mv.visitVarInsn(ASTORE, index);
        return this;
    }

    public VariableBuilder setTableValue(int elementIndex, Expression value) {
        mv.visitVarInsn(Opcodes.ALOAD, index); // Загрузка массива
        mv.visitIntInsn(Opcodes.BIPUSH, elementIndex); // Загрузка индекса элемента
        loadValueOntoStack(value); // Загрузка значения на стек
        mv.visitInsn(Opcodes.AASTORE); // Сохранение значения в массиве
        return this;
    }

    public VariableBuilder loadLocal(int index, String descriptor) {
        loadLocalVariable(index, descriptor);
        return this;
    }

    public VariableBuilder storeLocal(int index, String descriptor) {
        storeLocalVariable(index, descriptor);
        return this;
    }

    public void build() {

        loadConstantValue(descriptor, value);
        if (isLocalVariable) {
            storeLocalVariable(index, descriptor);
        }
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

    private void loadConstantValue(String descriptor, Object value) {
        switch (descriptor) {
            case "Ljava/lang/String;", "I", "F" -> mv.visitLdcInsn(value);
            case "Z" -> mv.visitVarInsn(ILOAD, (boolean) value ? 1 : 0);
            default -> throw new IllegalStateException("Unexpected value: " + descriptor);
        }
    }

    private void loadLocalVariable(int index, String descriptor) {
        switch (descriptor) {
            case "I":
                mv.visitVarInsn(ILOAD, index);
                break;
            case "F":
                mv.visitVarInsn(FLOAD, index);
                break;
            case "Z":
                mv.visitVarInsn(ILOAD, index);
                break;
            case "Ljava/lang/String;", "Ljava/lang/Object;", "[Ljava/lang/Object;":
                mv.visitVarInsn(ALOAD, index);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + descriptor);
        }
    }

    private void storeLocalVariable(int index, String descriptor) {
        switch (descriptor) {
            case "I":
                mv.visitVarInsn(ISTORE, index);
                break;
            case "F":
                mv.visitVarInsn(FSTORE, index);
                break;
            case "Z":
                mv.visitVarInsn(ISTORE, index);
                break;
            case "Ljava/lang/String;":
                mv.visitVarInsn(ASTORE, index);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + descriptor);
        }
    }

    public void moveLocalVariable(int fromIndex, int toIndex, String descriptor) {
        loadLocalVariable(fromIndex, descriptor);
        storeLocalVariable(toIndex, descriptor);
    }

}
