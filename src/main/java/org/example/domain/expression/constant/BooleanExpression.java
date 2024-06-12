package org.example.domain.expression.constant;

import org.objectweb.asm.Type;

public record BooleanExpression(Boolean value) implements ConstantExpression {
    @Override
    public Type getType() {
        return Type.BOOLEAN_TYPE;
    }
}
