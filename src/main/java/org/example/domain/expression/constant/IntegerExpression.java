package org.example.domain.expression.constant;

import org.objectweb.asm.Type;

public record IntegerExpression(Integer value) implements ConstantExpression {
    @Override
    public Type getType() {
        return Type.INT_TYPE;
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}

