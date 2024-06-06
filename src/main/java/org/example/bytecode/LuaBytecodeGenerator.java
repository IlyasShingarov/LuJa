package org.example.bytecode;

import jakarta.annotation.PostConstruct;
import org.example.domain.Variable;
import org.objectweb.asm.*;
import org.springframework.stereotype.Component;

@Component
public class LuaBytecodeGenerator {

    private ClassWriter cw;
    private MethodVisitor mv;

    @PostConstruct
    public void init() {
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "GeneratedClass", null, "java/lang/Object", null);
    }

    public void generateMainMethod() {
        mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        mv = cw.visitMethod(Opcodes.ACC_STATIC, "<clinit>", "()V", null, null);
        mv.visitCode();
    }

    public void generateExitMain() {
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(2, 2);
        mv.visitEnd();
        cw.visitEnd();
    }

    public void declareGlobalVariable(Variable v) {
        String descriptor;
        switch (v.type()) {
            case NUMBER -> {
                descriptor = "I";
                mv.visitFieldInsn(Opcodes.PUTSTATIC, "GeneratedClass", v.name(), descriptor);
            }
            default -> throw new IllegalStateException("Unexpected value: " + v.type());
        }
        FieldVisitor fw = cw.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, v.name(), descriptor, null, null);

    }

    public void declareGlobalVariable(String name, String descriptor, Object value) {
        // Объявление статического поля класса
        int access = Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC;
        FieldVisitor fw = cw.visitField(access, name, descriptor, null, value);
        fw.visitEnd();

        // Инициализация статического поля класса
        mv.visitLdcInsn(value);
        mv.visitFieldInsn(Opcodes.PUTSTATIC, "GeneratedClass", name, descriptor);
    }

    public void generateAssignment(String variable, int value) {
        mv.visitVarInsn(Opcodes.BIPUSH, value);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
    }

    public void generatePrint(String variable) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }

    public byte[] getBytecode() {
        return cw.toByteArray();
    }
}
