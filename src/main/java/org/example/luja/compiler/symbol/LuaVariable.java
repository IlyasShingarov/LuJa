package org.example.luja.compiler.symbol;

public record LuaVariable(String name, int index, LuaSymbolMetatype metaType, boolean isTable) { }
