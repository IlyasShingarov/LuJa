package org.example.domain.expression.constant;

import org.objectweb.asm.Type;

public record FloatExpression(Double value) implements ConstantExpression {
    @Override
    public Type getType() {
        return Type.FLOAT_TYPE;
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}

