package org.example.luja.expression;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaLexer;
import org.example.antlr.LuaParser;
import org.example.luja.LuaBytecodeExecutor;
import org.example.temp.LuaChunkVisitor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelationExpressionTest {
    private ParseTree createParseTree(String code) {
        CharStream input = CharStreams.fromString(code);
        LuaLexer lexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(new CommonTokenStream(lexer));
        return parser.start_();
    }

    @Test
    public void testIntegerEqualityExpression() throws Exception {
        String luaCode = """
                print(1 == 1)
                print(1 == 2)
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testFloatEqualityExpression() throws Exception {
        String luaCode = """
                print(1.0 == 1.0)
                print(1.0 == 2.0)
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testStringEqualityExpression() throws Exception {
        String luaCode = """
                print("hello" == "hello")
                print("hello" == "world")
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testBooleanEqualityExpression() throws Exception {
        String luaCode = """
                print(true == true)
                print(true == false)
                print(false == false)
                print(false == true)
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
        assertTrue(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
    }

    @Test
    public void testConstantEqualityExpression() throws Exception {
        String luaCode = """
                print(1 == 1)
                print(1 == 2)
                print(1.0 == 1.0)
                print(1.0 == 2.0)
                print(true == true)
                print(true == false)
                print(false == false)
                print(false == true)
                print("hello" == "hello")
                print("hello" == "world")
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
        assertTrue(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
        assertTrue(Boolean.parseBoolean(lines.get(4)));
        assertFalse(Boolean.parseBoolean(lines.get(5)));
        assertTrue(Boolean.parseBoolean(lines.get(6)));
        assertFalse(Boolean.parseBoolean(lines.get(7)));
        assertTrue(Boolean.parseBoolean(lines.get(8)));
        assertFalse(Boolean.parseBoolean(lines.get(9)));
    }

    @Test
    public void testIntegerInequalityExpression() throws Exception {
        String luaCode = """
                print(1 ~= 1)
                print(1 ~= 2)
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertFalse(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testFloatInequalityExpression() throws Exception {
        String luaCode = """
                print(1.0 ~= 1.0)
                print(1.0 ~= 2.0)
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertFalse(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testStringInequalityExpression() throws Exception {
        String luaCode = """
                print("hello" ~= "hello")
                print("hello" ~= "world")
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertFalse(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testBooleanInequalityExpression() throws Exception {
        String luaCode = """
                print(true ~= true)
                print(true ~= false)
                print(false ~= false)
                print(false ~= true)
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertFalse(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
        assertFalse(Boolean.parseBoolean(lines.get(2)));
        assertTrue(Boolean.parseBoolean(lines.get(3)));
    }

    @Test
    public void testConstantInequalityExpression() throws Exception {
        String luaCode = """
                print(1 ~= 1)
                print(1 ~= 2)
                print(1.0 ~= 1.0)
                print(1.0 ~= 2.0)
                print(true ~= true)
                print(true ~= false)
                print(false ~= false)
                print(false ~= true)
                print("hello" ~= "hello")
                print("hello" ~= "world")
                """;

        ParseTree parseTree = createParseTree(luaCode);
        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(parseTree);

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        List<String> lines = output.lines().toList();

        assertFalse(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
        assertFalse(Boolean.parseBoolean(lines.get(2)));
        assertTrue(Boolean.parseBoolean(lines.get(3)));
        assertFalse(Boolean.parseBoolean(lines.get(4)));
        assertTrue(Boolean.parseBoolean(lines.get(5)));
        assertFalse(Boolean.parseBoolean(lines.get(6)));
        assertTrue(Boolean.parseBoolean(lines.get(7)));
        assertFalse(Boolean.parseBoolean(lines.get(8)));
        assertTrue(Boolean.parseBoolean(lines.get(9)));
    }
}
