package org.example.bytecode;

import org.example.domain.expression.BinaryExpression;
import org.example.domain.expression.Expression;
import org.example.domain.expression.constant.BooleanExpression;
import org.example.domain.expression.constant.FloatExpression;
import org.example.domain.expression.constant.IntegerExpression;
import org.example.domain.expression.constant.StringExpression;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.invoke.MethodType;

public class BytecodeUtil implements Opcodes {

    public static void loadExpressionOntoStack(Expression expression, InsnList instructions) {
        switch (expression) {
            case IntegerExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/lang/Integer"));
                instructions.add(new InsnNode(DUP));
                instructions.add(new LdcInsnNode(expr.value()));
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V", false));
            }
            case FloatExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/lang/Double"));
                instructions.add(new InsnNode(DUP));
                instructions.add(new LdcInsnNode(expr.value()));
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Double", "<init>", "(D)V", false));
            }
            case BooleanExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/lang/Boolean"));
                instructions.add(new InsnNode(DUP));
                if (!expr.value()) {
                    instructions.add(new LdcInsnNode(0));
                } else {
                    instructions.add(new LdcInsnNode(1));
                }
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Boolean", "<init>", "(Z)V", false));
            }
            case StringExpression expr -> {
                instructions.add(new LdcInsnNode(expr.value()));
            }
            case BinaryExpression expr -> {
//                loadExpressionOntoStack(expr.left(), instructions);
//                loadExpressionOntoStack(expr.right(), instructions);
//                String name = expr.operation().name().toLowerCase();
//                instructions.add(new InvokeDynamicInsnNode(
//                                name,
//                                MethodType.genericMethodType(2).toMethodDescriptorString(),
//                                OPERATOR_HANDLE, (Integer) 2
//                        )
//                );
            }
            default -> throw new IllegalArgumentException("Unsupported expression type: " + expression);
        }
    }
}
