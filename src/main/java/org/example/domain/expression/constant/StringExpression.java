package org.example.domain.expression.constant;

import org.objectweb.asm.Type;

public record StringExpression(String value) implements ConstantExpression {
    @Override
    public Type getType() {
        return Type.getType(String.class);
    }
}
