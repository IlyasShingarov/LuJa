package org.example.luja.compiler.symbol;

import lombok.Getter;
import lombok.ToString;

import java.util.Stack;

@ToString
@Getter
public class MainContextManager implements ContextManager {
    private LuaSymbolTable luaSymbolTable;
    private final Stack<LuaScope> currentScopeStack = new Stack<>();
    private int currentScopeDepth = 1;
    private int currentLocalIndex;

    public MainContextManager(LuaSymbolTable luaSymbolTable) {
        this.luaSymbolTable = luaSymbolTable;
        this.currentLocalIndex = luaSymbolTable.getCurrentLocalIndex();
    }

    public void setLuaSymbolTable(LuaSymbolTable luaSymbolTable) {
        this.luaSymbolTable = luaSymbolTable;
    }

    public void enterScope() {
        LuaScope scope = luaSymbolTable.getAllScopes().removeFirst();
        currentScopeStack.push(scope);
        currentScopeDepth++;
    }

    public ScopeManager getCurrentScope() {
        return new ScopeManager(luaSymbolTable.getGlobalScope(), currentScopeStack);
    }

    public void addVariable(String name) {
        currentScopeStack.peek().declare(new LuaVariable(name, currentLocalIndex++, LuaSymbolMetatype.LOCAL, false));
    }

    public void exitScope() {
        currentScopeStack.pop();
        currentScopeDepth--;
    }
}
