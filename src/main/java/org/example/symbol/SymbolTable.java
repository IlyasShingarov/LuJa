package org.example.symbol;

import jakarta.annotation.PostConstruct;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

@Component
public class SymbolTable {

    private final Deque<Scope> scopes = new ArrayDeque<>();

    public SymbolTable() {
    }

    @PostConstruct
    public void init() {
        enterScope();
    }

    public void enterScope() {
        scopes.push(new Scope());
    }

    public void exitScope() {
        scopes.pop();
    }

    public void addLocalVariable(String name, Type type, String metatype) {
        Scope currentScope = scopes.peek();
        int index = currentScope.getNextLocalIndex();
        Symbol symbol = new Symbol(name, index, metatype, type);
        currentScope.addLocalVariable(name, symbol);
    }

    public Symbol getLocalVariable(String name) {
        for (Scope scope : scopes) {
            Symbol symbol = scope.getLocalVariable(name);
            if (symbol != null) {
                return symbol;
            }
        }
        return null;
    }

    public boolean containsLocalVariable(String name) {
        for (Scope scope : scopes) {
            if (scope.getLocalVariable(name) != null) {
                return true;
            }
        }
        return false;
    }

    public int getLocalVariableIndex(String name, Type type) {
        // Добавляет переменную в таблицу символов
        // Получает индекс переменной
        if (!containsLocalVariable(name)) {
            addLocalVariable(name, type,"local");
        }
        Symbol symbol = getLocalVariable(name);
        if (symbol == null) {
            throw new RuntimeException("Undefined variable: " + name);
        }
        return symbol.index();
    }
}
