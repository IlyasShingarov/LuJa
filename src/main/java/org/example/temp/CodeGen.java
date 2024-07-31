package org.example.temp;

import lombok.Getter;
import org.objectweb.asm.Opcodes;

@Getter
public class CodeGen implements Opcodes {
    private ClassBuilder classBuilder;
    private ClassBuilder.MethodBuilder mainMethod;
    private ClassBuilder.MethodBuilder currentMethod;

    public void init() {
        // TODO Add parameterized class name
        classBuilder = new ClassBuilder("GeneratedClass", "java/lang/Object");
        addDefaultConstructor();
    }

    private void addDefaultConstructor() {
        // Create a new method for the default constructor
        ClassBuilder.MethodBuilder constructor = classBuilder.newMethod(ACC_PUBLIC, "<init>", "()V");

        // Add the constructor code
        constructor.addVarInsn(ALOAD, 0) // Load 'this' onto the stack
                .addMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false) // Call the super constructor
                .addInsn(RETURN) // Return from the constructor
                .setMaxs(1, 1) // Set max stack and max locals
                .endMethod();
    }

    public void startMainMethod() {
        mainMethod = classBuilder.newMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V");
//                .addVarInsn(ALOAD, 0);
//        mainMethod.instructions().add(new InsnList() {{
//
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "GeneratedClass", "a", "Ljava/lang/Object;"));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//            add(new InsnNode(Opcodes.SWAP));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//
//
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "GeneratedClass", "b", "Ljava/lang/Object;"));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//            add(new InsnNode(Opcodes.SWAP));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//
//
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "GeneratedClass", "c", "Ljava/lang/Object;"));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//            add(new InsnNode(Opcodes.SWAP));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "GeneratedClass", "d", "Ljava/lang/Object;"));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
//            add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//            add(new InsnNode(Opcodes.SWAP));
//            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//
//            add(new InsnNode(Opcodes.RETURN));
//        }});
        currentMethod = mainMethod;
    }

    public void endMainMethod() {
        // Печать локальной переменной с индексом 1
//        mainMethod.instructions().add(
//                new InsnList() {
//                    {
//                        add(new VarInsnNode(ALOAD, 1));
//                        add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
//                        add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//                        add(new InsnNode(Opcodes.SWAP));
//                        add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//                    }
//
//                }
//                new InsnList() {{
//                    add(new FieldInsnNode(Opcodes.GETSTATIC, "GeneratedClass", "d", "Ljava/lang/Object;"));
//                    add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
//                    add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
//                    add(new InsnNode(Opcodes.SWAP));
//                    add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
//                }}
//        );
        mainMethod.addInsn(RETURN)
                .setMaxs(1, 1)
                .endMethod();
    }


    public byte[] toByteArray() {
        return classBuilder.build();
    }
}
