package org.example.domain.expression;

import org.objectweb.asm.Type;

public record ArrayAccessExpression(
        VariableExpression array,
        Type elementType,
        Expression index
) implements Expression {
    @Override
    public Type getType() {
        return elementType;
    }
}
