package org.example.domain.expression;

import org.objectweb.asm.Type;

public record BuiltInFunctionExpression(
        String name,
        Type returnType,
        Runnable runnable
) implements Expression {
    @Override
    public Type getType() {
        return returnType;
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}
