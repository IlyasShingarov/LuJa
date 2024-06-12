package org.example.domain.expression.constant;

import org.example.domain.expression.Expression;

public interface ConstantExpression extends Expression {
    Object value();
}
