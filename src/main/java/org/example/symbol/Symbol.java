package org.example.symbol;

import org.objectweb.asm.Type;

public record Symbol(
        String name,
        int index,
        String metatype,
        Type type
) { }
