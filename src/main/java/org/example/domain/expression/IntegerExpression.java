package org.example.domain.expression;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public record IntegerExpression(int value) implements Expression { }

