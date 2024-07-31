package org.example.luja.compiler.symbol;

import java.util.List;

public record ScopeManager(
        LuaScope globalScope,
        List<LuaScope> localScopes
) {

    public LuaVariable getVariable(String name) {
        for (LuaScope scope : localScopes) {
            if (scope.isDeclared(name)) {
                return scope.getVariable(name);
            }
        }
        return globalScope.getVariable(name);
    }


    public boolean isGlobal(String name) {
        return globalScope.isDeclared(name);
    }

    public boolean isLocal(String name) {
        for (LuaScope scope : localScopes) {
            if (scope.isDeclared(name)) {
                return true;
            }
        }
        return false;
    }

    public boolean isVariableDeclared(String name) {
        return isLocal(name) || isGlobal(name);
    }

}
