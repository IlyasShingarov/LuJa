package org.example.domain.expression;

import org.objectweb.asm.Type;

import java.util.HashMap;
import java.util.List;

public record TableExpression(
        List<FieldExpression> fields,
        int size
) implements Expression {
    @Override
    public Type getType() {
        return Type.getType(HashMap.class);
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}
