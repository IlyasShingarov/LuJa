package org.example.domain.expression;

import org.objectweb.asm.Type;

public record FieldExpression(
        Expression object,
        Object fieldName
) implements Expression {
    @Override
    public Type getType() {
        return null;
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}
