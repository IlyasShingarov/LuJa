package org.example.symbol;

import org.objectweb.asm.Type;

public record VariableSymbol(
        String name,
        int index,
        String metatype,
        Type type
) implements Symbol { }
