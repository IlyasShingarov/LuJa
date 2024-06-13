package org.example.bytecode;

import lombok.RequiredArgsConstructor;
import org.example.bytecode.statement.VariableBuilder;
import org.example.domain.expression.BinaryExpression;
import org.example.domain.expression.BinaryOperation;
import org.example.domain.expression.Expression;
import org.example.domain.expression.VariableExpression;
import org.example.domain.expression.constant.ConstantExpression;
import org.example.symbol.VariableSymbol;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

@RequiredArgsConstructor
public class ExpressionBuilder implements Opcodes {

    private final MethodVisitor mv;

    public void generateBinaryOperation(Expression left, Expression right, int opcode) {
        // Загрузка левого операнда
        // Если левый операнд - переменная, то загружаем ее значение
        // Если левый операнд - константа, то загружаем ее значение
        // Загрузка правого операнда
        // Если правый операнд - переменная, то загружаем ее значение
        // Если правый операнд - константа, то загружаем ее значение
        // Выполняем операцию (Результат остается на стеке)

        if (left instanceof BinaryExpression leftBinary) {
            generateBinaryOperation(
                    leftBinary.left(), leftBinary.right(),
                    leftBinary.operation().getOpcode(leftBinary.left().getType()));
        } else {
            loadOperand(left);
        }

        if (right instanceof BinaryExpression rightBinary) {
            generateBinaryOperation(
                    rightBinary.left(), rightBinary.right(),
                    rightBinary.operation().getOpcode(rightBinary.left().getType()));
        } else {
            loadOperand(right);
        }

        mv.visitInsn(opcode);
    }

    public void generateComparison(Expression left, Expression right, BinaryOperation operation) {
        // Загружаем две переменных на стек
        // Определяем тип сравнения
        //  - INT & INT
        //  - INT & FLOAT || FLOAT & INT
        //  - FLOAT & FLOAT
        //  - STRING
        // Выполняем сравнение
        loadOperand(left);
        loadOperand(right);

        Label trueLabel = new Label();
        Label endLabel = new Label();

        Type leftType = left.getType();
        Type rightType = right.getType();

        if (leftType.getSort() == Type.INT && rightType.getSort() == Type.INT) {
            // Сравнение двух целочисленных значений
            switch (operation) {
                case EQUALS -> mv.visitJumpInsn(Opcodes.IF_ICMPEQ, trueLabel);
                case NOT_EQUALS -> mv.visitJumpInsn(Opcodes.IF_ICMPNE, trueLabel);
                case LESS_THAN -> mv.visitJumpInsn(Opcodes.IF_ICMPLT, trueLabel);
                case LESS_THAN_OR_EQUALS -> mv.visitJumpInsn(Opcodes.IF_ICMPLE, trueLabel);
                case GREATER_THAN -> mv.visitJumpInsn(Opcodes.IF_ICMPGT, trueLabel);
                case GREATER_THAN_OR_EQUALS -> mv.visitJumpInsn(Opcodes.IF_ICMPGE, trueLabel);
                default -> throw new IllegalArgumentException("Unknown comparison operation: " + operation);
            }
        } else if (leftType.getSort() == Type.FLOAT || rightType.getSort() == Type.FLOAT) {
            // Приведение к типу FLOAT и сравнение
            if (leftType.getSort() == Type.INT || rightType.getSort() == Type.INT) {
                mv.visitInsn(Opcodes.I2F);
            }
            mv.visitInsn(Opcodes.FCMPG);
            switch (operation) {
                case EQUALS -> mv.visitJumpInsn(Opcodes.IFEQ, trueLabel);
                case NOT_EQUALS -> mv.visitJumpInsn(Opcodes.IFNE, trueLabel);
                case LESS_THAN -> mv.visitJumpInsn(Opcodes.IFLT, trueLabel);
                case LESS_THAN_OR_EQUALS -> mv.visitJumpInsn(Opcodes.IFLE, trueLabel);
                case GREATER_THAN -> mv.visitJumpInsn(Opcodes.IFGT, trueLabel);
                case GREATER_THAN_OR_EQUALS -> mv.visitJumpInsn(Opcodes.IFGE, trueLabel);
                default -> throw new IllegalArgumentException("Unknown comparison operation: " + operation);
            }
        } else if (leftType.getSort() == Type.OBJECT && rightType.getSort() == Type.OBJECT) {
            // Сравнение строк
            if (leftType.equals(Type.getType(String.class)) && rightType.equals(Type.getType(String.class))) {
                // Приведение объектов к строкам
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/String");
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/String");

                // Сравнение строк
                switch (operation) {
                    case EQUALS -> mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
                    case NOT_EQUALS -> {
                        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/String", "equals", "(Ljava/lang/Object;)Z", false);
                        mv.visitInsn(Opcodes.ICONST_1);
                        mv.visitInsn(Opcodes.IXOR);
                    }
                    default -> throw new IllegalArgumentException("Unknown comparison operation: " + operation);
                }
            } else {
                throw new IllegalArgumentException("Unsupported object types for comparison: " + leftType + " and " + rightType);
            }
        } else {
            throw new IllegalArgumentException("Unsupported types for comparison: " + leftType + " and " + rightType);
        }

        // Если сравнение ложно, загружаем 0 (false) и переходим к метке конца
        mv.visitInsn(Opcodes.ICONST_0);
        mv.visitJumpInsn(Opcodes.GOTO, endLabel);

        // Метка для истинного результата сравнения
        mv.visitLabel(trueLabel);
        mv.visitInsn(Opcodes.ICONST_1);

        // Метка конца
        mv.visitLabel(endLabel);
    }

    private void loadOperand(Expression expression) {
        if (expression instanceof VariableExpression var) {
            if (var.symbol().metatype().equals("global")) {
                var symbol = var.symbol();
                mv.visitFieldInsn(Opcodes.GETSTATIC, "GeneratedClass", symbol.name(), symbol.type().getDescriptor());
            } else {
                mv.visitVarInsn(Opcodes.ILOAD, var.symbol().index());
            }
        } else if (expression instanceof ConstantExpression constant) {
            mv.visitLdcInsn(constant.value());
        }
    }

    public void loadExpression(Expression expression) {

        switch (expression) {
            case ConstantExpression constExpr -> mv.visitLdcInsn(constExpr.value());
            case VariableExpression varExpr -> {
                VariableSymbol symbol = varExpr.symbol();
                if (symbol.metatype().equals("global")) {
                    mv.visitFieldInsn(GETSTATIC, "GeneratedClass", symbol.name(), symbol.type().getDescriptor());
                } else {
                    new VariableBuilder(mv)
                            .loadLocal(symbol.index(), symbol.type().getDescriptor());
                }
            }
            case BinaryExpression binExpr -> generateBinaryOperation(binExpr.left(), binExpr.right(), binExpr.operation().getOpcode(binExpr.left().getType()));
            default -> throw new IllegalArgumentException("Unsupported expression type: " + expression.getClass().getName());
        }
    }

    public void getIntArrayElement(int arrayIndex, Expression elementIndex) {
        mv.visitVarInsn(Opcodes.ALOAD, arrayIndex); // Загрузка массива на стек
        loadOperand(elementIndex); // Загрузка индекса элемента на стек
        mv.visitInsn(Opcodes.AALOAD); // Получение значения (объекта Integer) из массива

        // Анбоксинг объекта Integer в примитивный int
        mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Integer");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Integer", "intValue", "()I", false);
    }
}
