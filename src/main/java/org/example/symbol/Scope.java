package org.example.symbol;

import java.util.HashMap;
import java.util.Map;

public class Scope {

    private final Map<String, VariableSymbol> localVariables = new HashMap<>();
    private final Map<String, FunctionSymbol> functions = new HashMap<>();
    private int nextLocalIndex = 0;

    public void addLocalVariable(String name, VariableSymbol symbol) {
        localVariables.put(name, symbol);
    }

    public VariableSymbol getLocalVariable(String name) {
        return localVariables.get(name);
    }

    public int getNextLocalIndex() {
        return nextLocalIndex++;
    }

    public void addFunction(String name, FunctionSymbol symbol) {
        functions.put(name, symbol);
    }

    public FunctionSymbol getFunction(String name) {
        return functions.get(name);
    }
}
