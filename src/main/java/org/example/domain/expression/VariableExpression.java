package org.example.domain.expression;

import org.example.luja.compiler.symbol.LuaVariable;
import org.objectweb.asm.Type;

public record VariableExpression(LuaVariable symbol) implements Expression {
    @Override
    public Type getType() {
        return null;
    }

    @Override
    public boolean hasVariableExpression() {
        return true;
    }
}
