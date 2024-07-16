package org.example.domain;

import org.example.domain.expression.Expression;
import org.objectweb.asm.tree.InsnList;

import java.util.function.Supplier;

public record Condition(
        Expression condition,
        Supplier<InsnList> blockGenerator
) {
}
