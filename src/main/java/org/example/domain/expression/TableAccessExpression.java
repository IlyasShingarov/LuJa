package org.example.domain.expression;

import org.objectweb.asm.Type;

public record TableAccessExpression(
        VariableExpression table,
        String key
) implements Expression {
    @Override
    public Type getType() {
        return Type.getType(Object.class);
    }
}
