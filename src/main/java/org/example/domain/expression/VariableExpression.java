package org.example.domain.expression;

import org.example.symbol.VariableSymbol;
import org.objectweb.asm.Type;

public record VariableExpression(VariableSymbol symbol) implements Expression {
    @Override
    public Type getType() {
        return symbol.type();
    }
}
