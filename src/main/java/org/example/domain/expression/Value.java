package org.example.domain.expression;

import org.example.domain.variable.SymbolType;

public record Value(Object value, SymbolType type) implements Expression { }
