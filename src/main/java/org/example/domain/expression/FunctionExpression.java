package org.example.domain.expression;

import org.example.symbol.FunctionSymbol;
import org.objectweb.asm.Type;

import java.util.List;

public record FunctionExpression(
        String name,
        FunctionSymbol functionSymbol,
        List<Expression> arguments
) implements Expression {
    @Override
    public Type getType() {
        return functionSymbol.type();
    }

    @Override
    public boolean hasVariableExpression() {
        return false;
    }
}
