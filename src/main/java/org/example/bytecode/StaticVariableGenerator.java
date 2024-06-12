package org.example.bytecode;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StaticVariableGenerator implements Opcodes {

    private final LuaBytecodeGenerator bytecodeGenerator;

    private MethodVisitor clinit;

    @PostConstruct
    public void init() {
        clinit = bytecodeGenerator.getClassWriter()
                .visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
        clinit.visitCode();
    }

    public void declareGlobalVariable(String name, String descriptor, Object value) {
        // Объявление статического поля класса
        int access = ACC_PUBLIC + ACC_STATIC;
        FieldVisitor fw = bytecodeGenerator.getClassWriter()
                .visitField(access, name, descriptor, null, value);
        fw.visitEnd();

        // Инициализация статического поля класса
        clinit.visitLdcInsn(value);
        clinit.visitFieldInsn(PUTSTATIC, "GeneratedClass", name, descriptor);
    }

    public void generateEnd() {
        clinit.visitInsn(RETURN);
        clinit.visitMaxs(-1, -1);
        clinit.visitEnd();
    }

}
