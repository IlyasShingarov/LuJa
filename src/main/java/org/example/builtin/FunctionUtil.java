package org.example.builtin;

import lombok.extern.slf4j.Slf4j;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.util.Map;
import java.util.function.Supplier;

@Slf4j
public class FunctionUtil implements Opcodes {

    private static final Map<String, Supplier<InsnList>> functions = Map.of(
            "print", FunctionUtil::print
    );

    public static Supplier<InsnList> getFunction(String functionName) {
        log.info("Getting function: {}", functionName);
        return functions.get(functionName);
    }

    public static InsnList print() {
        return new InsnList() {{
            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "toString", "()Ljava/lang/String;", false));
            add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;"));
            add(new InsnNode(Opcodes.SWAP));
            add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false));
        }};
    }
}
