package org.example.luja.compiler.symbol;

public interface ContextManager {
    void enterScope();
    ScopeManager getCurrentScope();
    void addVariable(String name);
    void exitScope();
}
