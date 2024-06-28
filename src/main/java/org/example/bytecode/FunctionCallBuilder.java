package org.example.bytecode;

import lombok.RequiredArgsConstructor;
import org.example.domain.expression.Expression;
import org.example.symbol.FunctionSymbol;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public class FunctionCallBuilder implements Opcodes {

    private final MethodVisitor mv;
    private final List<Expression> arguments = new ArrayList<>();

    public FunctionCallBuilder putArguments(List<Expression> arguments) {
        var argumentBuilder = new ExpressionBuilder(mv);
        for (Expression argument : arguments) {
            argumentBuilder.loadExpression(argument);
        }
        this.arguments.addAll(arguments);
        return this;
    }

    public FunctionCallBuilder forgetArguments() {
        arguments.clear();
        return this;
    }

    public FunctionCallBuilder callFunction(FunctionSymbol func) {
        if (!arguments.isEmpty()) {
            StringBuilder descriptor = new StringBuilder("(");
            for (Expression argument : arguments) {
                descriptor.append(argument.getType().getDescriptor());
            }
            descriptor.append(")").append(func.type().getDescriptor());
            mv.visitMethodInsn(INVOKESTATIC, "GeneratedClass", func.name(), descriptor.toString(), false);
        } else {
            mv.visitMethodInsn(INVOKESTATIC, "GeneratedClass", func.name(), func.descriptor(), false);
        }
        return this;
    }

}
