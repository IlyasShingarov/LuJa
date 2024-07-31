package org.example.luja.compiler.symbol;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Getter
@ToString
@EqualsAndHashCode
public class LuaScope {

    private final Map<String, LuaVariable> variables = new HashMap<>();
    private final int scopeDepth;

    public LuaScope(int depth) {
        this.scopeDepth = depth;
    }

    public void declare(LuaVariable variable) {
        log.info("Declaring variable {}", variable);
        variables.put(variable.name(), variable);
    }

    public boolean isDeclared(String name) {
        return variables.containsKey(name);
    }

    public LuaVariable getVariable(String name) {
        return variables.get(name);
    }

}
