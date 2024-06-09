package org.example.symbol;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    private final Map<String, Symbol> localVariables = new HashMap<>();
    private int nextLocalIndex = 0;

    public void addLocalVariable(String name, Symbol symbol) {
        localVariables.put(name, symbol);
    }

    public Symbol getLocalVariable(String name) {
        return localVariables.get(name);
    }

    public int getNextLocalIndex() {
        return nextLocalIndex++;
    }

}
