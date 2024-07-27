package org.example.luja.test.statement;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaLexer;
import org.example.antlr.LuaParser;
import org.example.luja.LuaBytecodeExecutor;
import org.example.temp.LuaChunkVisitor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableConstructorTest {
    private final LuaBytecodeExecutor executor = new LuaBytecodeExecutor();

    private ParseTree createParseTree(String code) {
        CharStream input = CharStreams.fromString(code);
        LuaLexer lexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(new CommonTokenStream(lexer));
        return parser.start_();
    }

    private byte[] executeLuaCode(String luaCode) throws Exception {
        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));
        return chunkVisitor.getBytecodeGenerator().toByteArray();
    }

    @Test
    public void testTableConstructorBasic_1() throws Exception {
        String luaCode = """
                local a = {1, hello="world", "privet"};
                print(a[1]);
                print(a["hello"]);
                print(a[2]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("1", output.get(0));
        assertEquals("world", output.get(1));
        assertEquals("privet", output.get(2));
    }

    @Test
    public void testTableConstructorOnArrayLikeTableWithIntegers() throws Exception {
        String luaCode = """
                local a = {1, 2, 3};
                print(a[1]);
                print(a[2]);
                print(a[3]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("1", output.get(0));
        assertEquals("2", output.get(1));
        assertEquals("3", output.get(2));
    }

    @Test
    public void testTableConstructorOnArrayLikeTableWithStrings() throws Exception {
        String luaCode = """
                local a = {"hello", "world", "privet"};
                print(a[1]);
                print(a[2]);
                print(a[3]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("hello", output.get(0));
        assertEquals("world", output.get(1));
        assertEquals("privet", output.get(2));
    }

    @Test
    public void testTableConstructorOnArrayLikeTableWithMixedTypes() throws Exception {
        String luaCode = """
                local a = {1, "world", 3};
                print(a[1]);
                print(a[2]);
                print(a[3]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("1", output.get(0));
        assertEquals("world", output.get(1));
        assertEquals("3", output.get(2));
    }

    @Test
    public void testTableConstructorOnArrayLikeTableWithNestedTables() throws Exception {
        String luaCode = """
                local a = {1, {2, 3}, 4};
                print(a[1]);
                print(a[2][1]);
                print(a[2][2]);
                print(a[3]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("1", output.get(0));
        assertEquals("2", output.get(1));
        assertEquals("3", output.get(2));
        assertEquals("4", output.get(3));
    }

    @Test
    public void testTableConstructorOnArrayLikeTableWithNestedTablesAndStrings() throws Exception {
        String luaCode = """
                local a = {1, {2, "hello"}, 4};
                print(a[1]);
                print(a[2][1]);
                print(a[2][2]);
                print(a[3]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("1", output.get(0));
        assertEquals("2", output.get(1));
        assertEquals("hello", output.get(2));
        assertEquals("4", output.get(3));
    }
}

