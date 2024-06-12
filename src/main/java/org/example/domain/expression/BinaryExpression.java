package org.example.domain.expression;

import org.objectweb.asm.Type;

public record BinaryExpression(Expression left, Expression right, BinaryOperation operation) implements Expression {
    @Override
    public Type getType() {
        return left.getType();
    }
}
