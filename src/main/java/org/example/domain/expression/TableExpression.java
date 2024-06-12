package org.example.domain.expression;

import org.objectweb.asm.Type;

import java.util.Map;

// Представление выражения объявления таблицы в Lua
public record TableExpression(
    Map<Object, Object> fields
) implements Expression {
    @Override
    public Type getType() {
        return Type.getType(Map.class);
    }
}
