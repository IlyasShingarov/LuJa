package org.example.luja.compiler.symbol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.example.symbol.FunctionSymbol;
import org.example.symbol.VariableSymbol;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public class LuaScope {

    private final Map<String, LuaVariable> variables = new HashMap<>();
    private int scopeDepth;

    public LuaScope(int depth) {
        this.scopeDepth = depth;
    }

    public void declare(LuaVariable variable) {
        variables.put(variable.name(), variable);
    }

    public boolean isDeclared(String name) {
        return variables.containsKey(name);
    }

    public LuaVariable getVariable(String name) {
        return variables.get(name);
    }

}
