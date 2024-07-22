package org.example.luja.compiler.symbol;

import lombok.extern.slf4j.Slf4j;

import java.util.Deque;
import java.util.Stack;

@Slf4j
public class FunctionContextManager implements ContextManager{
    private final LuaSymbolTable luaSymbolTable;
    private final Deque<LuaScope> functionScope;
    private int currentLocalIndex;

    private int currentScopeDepth = 1;
    private final Stack<LuaScope> currentScopeStack = new Stack<>();

    public FunctionContextManager(LuaSymbolTable luaSymbolTable, Deque<LuaScope> functionScope) {
        this.luaSymbolTable = luaSymbolTable;
        this.functionScope = functionScope;
        this.currentLocalIndex = functionScope.stream()
                    .flatMap(luaScope -> luaScope.getVariables().values().stream())
                .map(LuaVariable::index)
                .max(Integer::compareTo)
                .orElse(0);
    }

    @Override
    public void enterScope() {
        LuaScope scope = functionScope.removeFirst();
        currentScopeStack.push(scope);
        currentScopeDepth++;
    }

    @Override
    public ScopeManager getCurrentScope() {
        return new ScopeManager(luaSymbolTable.getGlobalScope(), currentScopeStack);
    }

    @Override
    public void addVariable(String name) {
        log.info("Adding variable {}", name);
        log.info("Current local index is {}", currentLocalIndex);
        currentScopeStack.peek().declare(new LuaVariable(name, currentLocalIndex++, LuaSymbolMetatype.LOCAL, false));
    }

    @Override
    public void exitScope() {
        currentScopeStack.pop();
        currentScopeDepth--;
    }
}
