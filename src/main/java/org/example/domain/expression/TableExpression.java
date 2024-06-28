package org.example.domain.expression;

import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public record TableExpression(
        List<Map.Entry<Expression, Expression>> elements,
        int size
) implements Expression {
    @Override
    public Type getType() {
        return Type.getType(HashMap.class);
    }
}
