package org.example.bytecode;

import lombok.RequiredArgsConstructor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class MethodBytecodeBuilder implements Opcodes {
    private final ClassWriter cw;
    private final LuaBytecodeGenerator bytecodeGenerator;

    private MethodVisitor mv;
    private String methodName;
    private Type returnType;
    private final List<Runnable> instructions = new ArrayList<>();
    private final List<Type> parameterTypes = new ArrayList<>();

    public MethodBytecodeBuilder withName(String name) {
        this.methodName = name;
        return this;
    }

    public MethodBytecodeBuilder withReturnType(Type returnType) {
        this.returnType = returnType;
        return this;
    }

    public MethodBytecodeBuilder withInstructions(List<Runnable> instructions) {
        this.instructions.addAll(instructions);
        return this;
    }

    public MethodBytecodeBuilder withParameters(List<Type> parameterTypes) {
        this.parameterTypes.addAll(parameterTypes);
        return this;
    }

    public MethodBytecodeBuilder initMethod() {
        mv = cw.visitMethod(
                ACC_PUBLIC + ACC_STATIC,
                methodName,
                createMethodDescriptor(returnType, parameterTypes),
                null, null
        );
        return this;
    }

    public MethodBytecodeBuilder endMethod() {
        bytecodeGenerator.setMethodVisitor(mv);

        instructions.forEach(Runnable::run);

        if (returnType.equals(Type.VOID_TYPE)) {
            mv.visitInsn(RETURN);
        } else if (returnType.equals(Type.INT_TYPE)) {
            mv.visitInsn(IRETURN);
        } else if (returnType.equals(Type.FLOAT_TYPE)) {
            mv.visitInsn(FRETURN);
        } else if (returnType.equals(Type.BOOLEAN_TYPE)) {
            mv.visitInsn(IRETURN);
        } else if (returnType.equals(Type.getType(Object.class))) {
            mv.visitInsn(ARETURN);
        } else if (returnType.equals(Type.getType(String.class))) {
            mv.visitInsn(ARETURN);
        } else {
            throw new IllegalStateException("Unsupported return type: " + returnType);
        }

        mv.visitMaxs(0, 0);
        mv.visitEnd();
        bytecodeGenerator.resetMainMethod();
        return this;
    }

    private String createMethodDescriptor(Type returnType, List<Type> parameterTypes) {
        StringBuilder descriptor = new StringBuilder("(");
        for (Type parameterType : parameterTypes) {
            descriptor.append(parameterType.getDescriptor());
        }
        descriptor.append(")");
        descriptor.append(returnType.getDescriptor());
        return descriptor.toString();
    }

    public MethodVisitor getMethodVisitor() {
        return mv;
    }
}
