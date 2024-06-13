package org.example.bytecode;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Condition;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.*;
import org.example.symbol.VariableSymbol;
import org.objectweb.asm.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LuaBytecodeGenerator implements Opcodes {

    private ClassWriter cw;
    private MethodVisitor mainMethod;
    private MethodVisitor mv;

    @PostConstruct
    public void init() {
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        cw.visit(V1_8, ACC_PUBLIC, "GeneratedClass", null, "java/lang/Object", null);
    }

    public void generateMainMethod() {
        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mainMethod = mv;
        mv.visitCode();
    }

    public void generateExitMain() {
        mv.visitInsn(RETURN);
        mv.visitMaxs(-1, -1);

        mv.visitEnd();
        cw.visitEnd();
    }

    public void declareLocalVariable(String name, String descriptor, Object value, int index) {
        if (value == null) {
            mv.visitInsn(ACONST_NULL);
            mv.visitVarInsn(ASTORE, index);
        } else {
            switch (descriptor) {
                case "I" -> {
                    mv.visitLdcInsn(value);
                    mv.visitVarInsn(ISTORE, index);
                }
                case "F" -> {
                    mv.visitLdcInsn(value);
                    mv.visitVarInsn(FSTORE, index);
                }
                case "Z" -> {
                    mv.visitVarInsn(ILOAD, (boolean) value ? 1 : 0);
                    mv.visitVarInsn(ISTORE, index);
                }
                case "Ljava/lang/String;" -> {
                    mv.visitLdcInsn(value);
                    mv.visitVarInsn(ASTORE, index);
                }
            }
        }
    }


    public void loadLocalVariable(int index, String descriptor) {
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


    // Функция формирования байткода условной конструкции
    public void generateIfElseStatement(List<Condition> conditions, Runnable elseBlock) {
        // Метка для конца всех условий
        Label endLabel = new Label();

        // Перебор всех условий
        for (Condition condition : conditions) {
            Label nextLabel = new Label();

            if (condition.condition() instanceof BinaryExpression binaryExpression) {
                // Вычисление условия. На стеке остается значение либо 0, либо 1
                generateComparison(binaryExpression.left(), binaryExpression.right(), binaryExpression.operation());
            }

            // Переход к следующему условию, если текущее ложно
            mv.visitJumpInsn(Opcodes.IFEQ, nextLabel);

            // Выполнение тела блока, если условие истинно
            condition.blockGenerator().run();

            // Переход к концу всех условий
            mv.visitJumpInsn(Opcodes.GOTO, endLabel);

            // Метка для следующего условия
            mv.visitLabel(nextLabel);
        }

        // Выполнение блока else, если все условия ложны
        if (elseBlock != null) {
            elseBlock.run();
        }

        // Метка конца всех условий
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

    public void generateForLoop(VariableSymbol init, VariableSymbol limit, Runnable bodyGenerator) {
        // Метка для начала цикла (условие)
        Label startLoop = new Label();
        // Метка для конца цикла
        Label endLoop = new Label();

        // Генерация кода инициализации
        if (init != null) {
            loadLocalVariable(init.index(), init.type().getDescriptor());
            mv.visitVarInsn(Opcodes.ISTORE, init.index());
        }

        // Метка начала цикла (условие)
        mv.visitLabel(startLoop);

        // Генерация кода условия и проверки
        if (limit != null) {
            loadLocalVariable(init.index(), Type.INT_TYPE.getDescriptor());
            loadLocalVariable(limit.index(), limit.type().getDescriptor());
            mv.visitJumpInsn(Opcodes.IF_ICMPGT, endLoop); // Переход к метке конца, если условие ложно
        }

        // Генерация тела цикла
        bodyGenerator.run();

        // Обновление переменной
        loadLocalVariable(init.index(), Type.INT_TYPE.getDescriptor());
        mv.visitLdcInsn(1); // Пока шаг только 1
        mv.visitInsn(Opcodes.IADD);
        mv.visitVarInsn(Opcodes.ISTORE, init.index());

        // Переход к началу цикла (условие)
        mv.visitJumpInsn(Opcodes.GOTO, startLoop);

        // Метка конца цикла
        mv.visitLabel(endLoop);
    }

    public void generateWhileLoop(Expression condition, Runnable bodyGenerator) {
        // Метка для начала цикла (условие)
        Label startLoop = new Label();
        // Метка для конца цикла
        Label endLoop = new Label();

        // Метка начала цикла (условие)
        mv.visitLabel(startLoop);

        // Генерация кода условия и проверки
        if (condition instanceof BooleanExpression booleanExpr) {
            mv.visitLdcInsn(booleanExpr.value());
            mv.visitJumpInsn(Opcodes.IFEQ, endLoop);
        } else if (condition instanceof VariableExpression varExpr) {
            VariableSymbol symbol = varExpr.symbol();
            mv.visitVarInsn(Opcodes.ILOAD, symbol.index());
            mv.visitJumpInsn(Opcodes.IFEQ, endLoop);
        } else if (condition instanceof BinaryExpression binaryExpr) {
            generateComparison(binaryExpr.left(), binaryExpr.right(), binaryExpr.operation());
            mv.visitJumpInsn(Opcodes.IFEQ, endLoop);
        }

        // Генерация тела цикла
        bodyGenerator.run();

        // Переход к началу цикла (условие)
        mv.visitJumpInsn(Opcodes.GOTO, startLoop);

        // Метка конца цикла
        mv.visitLabel(endLoop);
    }


    public void generatePrint(VariableSymbol symbol) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        switch (symbol.metatype()) {
            case "global" -> mv.visitFieldInsn(Opcodes.GETSTATIC, "GeneratedClass", symbol.name(), symbol.type().getDescriptor());
            case "local", "counter", "parameter" -> loadLocalVariable(symbol.index(), symbol.type().getDescriptor());
        }
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                "(%s)V".formatted(symbol.type().getDescriptor()), false);
    }

    public void generatePrint(Expression expression) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        loadOperand(expression);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }


    public void setMethodVisitor(MethodVisitor mv) {
        this.mv = mv;
    }

    public MethodVisitor getMethodVisitor() {
        return mv;
    }

    public void resetMainMethod() {
        mv = mainMethod;
    }

    public ClassWriter getClassWriter() {
        return cw;
    }

    public byte[] getBytecode() {
        return cw.toByteArray();
    }
}
