package org.example.domain.statement;

import org.example.domain.Block;
import org.example.domain.expression.Expression;

public record ForLoop(
        String counter,
        Expression init,
        Expression limit,
        Block block
) implements Statement {
}
