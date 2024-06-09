package org.example.domain.expression;

import org.example.symbol.Symbol;

public record VariableExpression(Symbol symbol) implements Expression { }
