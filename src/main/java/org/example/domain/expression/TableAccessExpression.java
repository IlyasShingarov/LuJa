package org.example.domain.expression;

import org.objectweb.asm.Type;

import java.util.List;

public record TableAccessExpression(
        VariableExpression table,
        List<Expression> key
) implements Expression {
    @Override
    public Type getType() {
        return Type.getType(Object.class);
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}
