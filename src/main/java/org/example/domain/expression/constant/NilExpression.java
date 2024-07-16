package org.example.domain.expression.constant;

import org.objectweb.asm.Type;

public record NilExpression() implements ConstantExpression {
    @Override
    public Object value() {
        return null;
    }

    @Override
    public Type getType() {
        return Type.getType(Object.class);
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}
