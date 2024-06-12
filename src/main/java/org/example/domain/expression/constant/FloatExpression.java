package org.example.domain.expression.constant;

import org.objectweb.asm.Type;

public record FloatExpression(Float value) implements ConstantExpression {
    @Override
    public Type getType() {
        return Type.FLOAT_TYPE;
    }
}

