package org.example.bytecode;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.Variable;
import org.objectweb.asm.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LuaBytecodeGenerator implements Opcodes {

    private ClassWriter cw;
    private MethodVisitor mv;
    private MethodVisitor clinit;

    @PostConstruct
    public void init() {
        cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_8, ACC_PUBLIC, "GeneratedClass", null, "java/lang/Object", null);
    }

    public void generateMainMethod() {
        mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();

        clinit = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        clinit.visitCode();
    }

    public void generateExitMain() {
        mv.visitInsn(RETURN);
        mv.visitMaxs(100, 100);
        mv.visitEnd();
        cw.visitEnd();
    }

    public void declareGlobalVariable(Variable v) {
        String descriptor;
        switch (v.type()) {
            case NUMBER -> {
                descriptor = "I";
                mv.visitFieldInsn(PUTSTATIC, "GeneratedClass", v.name(), descriptor);
            }
            default -> throw new IllegalStateException("Unexpected value: " + v.type());
        }
        FieldVisitor fw = cw.visitField(ACC_PUBLIC + ACC_STATIC, v.name(), descriptor, null, null);

    }

    public void declareGlobalVariable(String name, String descriptor, Object value) {
        // Объявление статического поля класса
        int access = ACC_PUBLIC + ACC_STATIC;
        FieldVisitor fw = cw.visitField(access, name, descriptor, null, value);
        fw.visitEnd();

        // Инициализация статического поля класса
        clinit.visitLdcInsn(value);
        clinit.visitFieldInsn(PUTSTATIC, "GeneratedClass", name, descriptor);
    }

    public void declareLocalVariable(String name, String descriptor, Object value, int index) {
        log.warn("Declaring local variable: {} with descriptor: {} and value: {}", name, descriptor, value);
        switch (descriptor) {
            case "I" -> {
                mv.visitLdcInsn(value);
                mv.visitVarInsn(ISTORE, index);
//                mv.visitLocalVariable(name, descriptor, null, null, null, index);
            }
            case "F" -> {
                mv.visitLdcInsn(value);
                mv.visitVarInsn(FSTORE, index);
//                mv.visitLocalVariable(name, descriptor, null, null, null, index);
            }
            case "Z" -> {
                mv.visitVarInsn(ILOAD, (boolean) value ? 1 : 0);
                mv.visitVarInsn(ISTORE, index);
//                mv.visitLocalVariable(name, descriptor, null, null, null, index);
            }
            case "Ljava/lang/String;" -> {
                mv.visitLdcInsn(value);
                mv.visitVarInsn(ASTORE, index);
//                mv.visitLocalVariable(name, descriptor, null, null, null, index);
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
            case "Ljava/lang/String;":
                mv.visitVarInsn(ALOAD, index);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + descriptor);
        }
    }

    public void storeLocalVariable(int index, String descriptor) {
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

    public void generateAssignment(String variable, int value) {
        mv.visitVarInsn(Opcodes.BIPUSH, value);
        mv.visitVarInsn(Opcodes.ISTORE, 1);
    }

    public void generatePrint(int index) {
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ILOAD, index);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(I)V", false);
    }

    public byte[] getBytecode() {
        return cw.toByteArray();
    }
}
