package org.example.domain.statement;

import org.example.domain.expression.Expression;
import org.objectweb.asm.tree.FieldNode;

public record StaticField(
        FieldNode field,
        Expression valueExpression
) implements Statement {
}
