package org.example.luja.compiler.symbol;

import lombok.Getter;
import lombok.ToString;

import java.util.Stack;

@ToString
@Getter
public class ContextManager {
    private LuaSymbolTable luaSymbolTable;
    private Stack<LuaScope> currentScopeStack = new Stack<>();
    private int currentScopeDepth = 1;

    public ContextManager(LuaSymbolTable luaSymbolTable) {
        this.luaSymbolTable = luaSymbolTable;
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

    public void exitScope() {
        currentScopeStack.pop();
        currentScopeDepth--;
    }
}
