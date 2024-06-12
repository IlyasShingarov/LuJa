package org.example.domain.expression;

import org.objectweb.asm.Type;

public record ArrayAccessExpression(
        VariableExpression array,
        Expression index
) implements Expression {
    @Override
    public Type getType() {
        return null;
    }
}
