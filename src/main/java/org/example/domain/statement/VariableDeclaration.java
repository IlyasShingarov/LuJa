package org.example.domain.statement;

import org.example.domain.Variable;
import org.example.domain.expression.Expression;

import java.util.List;

public record VariableDeclaration(
        List<Variable> variables,
        List<Expression> expressions
) implements Statement { }
