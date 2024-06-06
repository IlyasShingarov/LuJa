package org.example.symbol;

import org.example.domain.Variable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalSymbolTable {

    private final Map<String, Variable> variables = new HashMap<>();

    public void add(String name, Variable v) {
        variables.put(name, v);
    }

    public Variable get(String name) {
        return variables.get(name);
    }
}
