package org.example.domain.statement;

import org.example.domain.expression.Expression;

import java.util.List;

public record VariableDeclaration(
        List<String> variables,
        List<Expression> expressions
) implements Statement { }
