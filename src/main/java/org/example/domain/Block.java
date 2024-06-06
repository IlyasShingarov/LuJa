package org.example.domain;

import org.example.domain.statement.Statement;

import java.util.List;

public record Block(List<Statement> statements) { }
