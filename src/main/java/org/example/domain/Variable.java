package org.example.domain;

import org.example.domain.variable.SymbolType;

public record Variable(String name, Object value, SymbolType type) { }


