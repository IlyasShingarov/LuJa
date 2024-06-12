package org.example.domain.expression;

import org.example.symbol.Symbol;
import org.objectweb.asm.Type;

public record VariableExpression(Symbol symbol) implements Expression {
    @Override
    public Type getType() {
        return symbol.type();
    }
}
