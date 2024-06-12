package org.example.domain.expression;

import org.objectweb.asm.Type;

import java.util.List;

public record ArrayTableExpression(
        List<Expression> elements,
        int size
) implements Expression {
    @Override
    public Type getType() {
        if (elements.isEmpty()) {
            return Type.getType(Object[].class);
        }

        Type firstElementType = elements.getFirst().getType();
        for (Expression element : elements) {
            if (!element.getType().equals(firstElementType)) {
                return Type.getType(Object[].class);
            }
        }

        // Возвращаем тип массива на основе типа первого элемента
        return Type.getType("[" + firstElementType.getDescriptor());
    }

    public Type getElementType() {
        return elements.isEmpty() ? Type.getType(Object.class) : elements.getFirst().getType();
    }
}
