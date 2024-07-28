package org.example.luja.compiler.symbol;

import lombok.Getter;
import lombok.ToString;

import java.util.*;

@Getter
@ToString
public class LuaSymbolTable {

    private final LuaScope globalScope = new LuaScope(0);
    private final Stack<LuaScope> localScopes = new Stack<>();
    private final Map<String, Stack<LuaScope>> functionScopes = new HashMap<>();
    private final Deque<LuaScope> allScopes = new ArrayDeque<>();
    private int currentLocalIndex = 0;

    public LuaSymbolTable() {
//        allScopes.add(globalScope);
    }

    public void enterScope() {
        int depth = localScopes.isEmpty() ? 1 : localScopes.peek().getScopeDepth() + 1;
        LuaScope scope = new LuaScope(depth);
        localScopes.push(scope);
        allScopes.add(scope);
    }

    public void exitScope() {
        localScopes.pop();
    }

    public void declareGlobal(String name) {
        globalScope.declare(new LuaVariable(name, -1, LuaSymbolMetatype.GLOBAL, false));
    }

    public void declareLocal(String name) {
        if (!localScopes.isEmpty()) {
            localScopes.peek().declare(new LuaVariable(name, currentLocalIndex++, LuaSymbolMetatype.LOCAL, false));
        }
    }

    public void declareLocal(String name, boolean isTable) {
        if (!localScopes.isEmpty()) {
            localScopes.peek().declare(new LuaVariable(name, currentLocalIndex++, LuaSymbolMetatype.LOCAL, isTable));
        }
    }

//    public void declareFunctionScope(String name) {
//        var scope = localScopes.peek();
//        functionScopes.put(name, scope);
//    }

//    public void exitFunctionScope(String name) {
//        allScopes.remove(functionScopes.get(name));
//    }

    public Stack<LuaScope> getFunctionScope(String name) {
        return functionScopes.get(name);
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

    public void resetLocalIndexes() {
        currentLocalIndex = 0;
    }

}
