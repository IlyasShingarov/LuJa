package org.example.symbol;

import org.objectweb.asm.Type;

public record FunctionSymbol(
        String name,
        Type type,
        String metatype
) implements Symbol { }
