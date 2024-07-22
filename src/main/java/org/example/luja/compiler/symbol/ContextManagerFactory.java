package org.example.luja.compiler.symbol;

import java.util.Deque;
import java.util.Map;

public class ContextManagerFactory {
    private final LuaSymbolTable luaSymbolTable;
    private final Map<String, Deque<LuaScope>> functionScopes;

    public ContextManagerFactory(LuaSymbolTable luaSymbolTable, Map<String, Deque<LuaScope>> functionScopes) {
        this.luaSymbolTable = luaSymbolTable;
        this.functionScopes = functionScopes;
    }

    public ContextManager createFunctionContextManager(String functionName) {
        Deque<LuaScope> functionScope = functionScopes.get(functionName);
        return new FunctionContextManager(luaSymbolTable, functionScope);
    }
}
