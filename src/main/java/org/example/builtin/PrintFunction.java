package org.example.builtin;

import org.example.bytecode.LuaBytecodeGenerator;
import org.example.bytecode.statement.VariableBuilder;
import org.example.domain.expression.Expression;
import org.example.symbol.Symbol;
import org.example.symbol.VariableSymbol;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.List;

public class PrintFunction implements BuiltInFunction{

    @Override
    public void execute(LuaBytecodeGenerator generator, List<Expression> arguments) {
//        MethodVisitor mv = generator.getMethodVisitor();
//        switch ()


//        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//        switch (symbol.metatype()) {
//            case "global" -> mv.visitFieldInsn(Opcodes.GETSTATIC, "GeneratedClass", symbol.name(), symbol.type().getDescriptor());
//            case "local", "counter", "parameter" ->
//                    new VariableBuilder(mv).loadLocal(symbol.index(), symbol.type().getDescriptor());
//        }
//        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
//                "(%s)V".formatted(symbol.type().getDescriptor()), false);
    }
}
