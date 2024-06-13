package org.example.domain;

import org.example.domain.expression.Expression;

import java.util.List;

public record VariableAccess(
        String name,
        Expression index
) {
}
