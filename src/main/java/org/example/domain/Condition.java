package org.example.domain;

import org.example.domain.expression.Expression;

public record Condition(
        Expression condition,
        Runnable blockGenerator
) {
}
