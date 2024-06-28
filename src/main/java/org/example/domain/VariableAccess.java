package org.example.domain;

import org.example.domain.expression.Expression;

public record VariableAccess(
        String name,
        Expression index
) {
}
