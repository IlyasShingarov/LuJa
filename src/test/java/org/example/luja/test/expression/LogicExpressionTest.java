package org.example.luja.test.expression;

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

import static org.junit.jupiter.api.Assertions.*;

public class LogicExpressionTest {

    private ParseTree createParseTree(String code) {
        CharStream input = CharStreams.fromString(code);
        LuaLexer lexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(new CommonTokenStream(lexer));
        return parser.start_();
    }

    @Test
    public void testConjunctionOnBooleanExpression() throws Exception {
        String luaCode = """
                print(true and true)
                print(true and false)
                print(false and true)
                print(false and false)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
        assertFalse(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
    }

    @Test
    public void testConjunctionOnIntegerExpression() throws Exception {
        String luaCode = """
                print(1 and 2)
                print(1 and 0)
                print(0 and 1)
                print(0 and 0)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals(2, Integer.parseInt(lines.get(0)));
        assertEquals(0, Integer.parseInt(lines.get(1)));
        assertEquals(1, Integer.parseInt(lines.get(2)));
        assertEquals(0, Integer.parseInt(lines.get(3)));
    }

    @Test
    public void testConjunctionOnFloatExpression() throws Exception {
        String luaCode = """
                print(1.0 and 2.0)
                print(1.0 and 0.0)
                print(0.0 and 1.0)
                print(0.0 and 0.0)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals(2.0, Double.parseDouble(lines.get(0)));
        assertEquals(0.0, Double.parseDouble(lines.get(1)));
        assertEquals(1.0, Double.parseDouble(lines.get(2)));
        assertEquals(0.0, Double.parseDouble(lines.get(3)));
    }

    @Test
    public void testConjunctionOnStringExpression() throws Exception {
        String luaCode = """
                print("a" and "b")
                print("a" and "")
                print("" and "b")
                print("" and "")
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals("b", lines.get(0));
        assertEquals("", lines.get(1));
        assertEquals("b", lines.get(2));
        assertEquals("", lines.get(3));
    }

    @Test
    public void testConjunctionOnMixedExression() throws Exception {
        String luaCode = """
                print(1 and "b")
                print("a" and 0)
                print(0 and 6.0)
                print(0.0 and "b")
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();
        System.out.println(lines);

        assertEquals("b", lines.get(0));
        assertEquals(0, Integer.parseInt(lines.get(1)));
        assertEquals(6.0, Double.parseDouble(lines.get(2)));
        assertEquals("b", lines.get(3));
    }

    @Test
    public void testConjunctionOnChainExpression() throws Exception {
        String luaCode = """
                print(1 and 2 and 3)
                print(1 and 0 and 3)
                print(0 and 1 and 3)
                print(0 and 0 and 3)
                print(0 and 0 and 0)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals(3, Integer.parseInt(lines.get(0)));
        assertEquals(3, Integer.parseInt(lines.get(1)));
        assertEquals(3, Integer.parseInt(lines.get(2)));
        assertEquals(3, Integer.parseInt(lines.get(3)));
        assertEquals(0, Integer.parseInt(lines.get(4)));
    }

    @Test
    public void testConjunctionOnChainBooleanExpression() throws Exception {
        String luaCode = """
                print(true and true and true)
                print(true and false and true)
                print(false and true and true)
                print(false and false and true)
                print(false and false and false)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
        assertFalse(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
        assertFalse(Boolean.parseBoolean(lines.get(4)));
    }

    @Test
    public void testDisjunctionOnBooleanExpression() throws Exception {
        String luaCode = """
                print(true or true)
                print(true or false)
                print(false or true)
                print(false or false)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
        assertTrue(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
    }

    @Test
    public void testDisjunctionOnIntegerExpression() throws Exception {
        String luaCode = """
                print(1 or 2)
                print(1 or 0)
                print(0 or 1)
                print(0 or 0)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals(1, Integer.parseInt(lines.get(0)));
        assertEquals(1, Integer.parseInt(lines.get(1)));
        assertEquals(0, Integer.parseInt(lines.get(2)));
        assertEquals(0, Integer.parseInt(lines.get(3)));
    }

    @Test
    public void testDisjunctionOnFloatExpression() throws Exception {
        String luaCode = """
                print(1.0 or 2.0)
                print(1.0 or 0.0)
                print(0.0 or 1.0)
                print(0.0 or 0.0)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals(1.0, Double.parseDouble(lines.get(0)));
        assertEquals(1.0, Double.parseDouble(lines.get(1)));
        assertEquals(0.0, Double.parseDouble(lines.get(2)));
        assertEquals(0.0, Double.parseDouble(lines.get(3)));
    }

    @Test
    public void testDisjunctionOnStringExpression() throws Exception {
        String luaCode = """
                print("a" or "b")
                print("a" or "")
                print("" or "b")
                print("" or "")
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals("a", lines.get(0));
        assertEquals("a", lines.get(1));
        assertEquals("", lines.get(2));
        assertEquals("", lines.get(3));
    }

    @Test
    public void testDisjunctionOnMixedExression() throws Exception {
        String luaCode = """
                print(1 or "b")
                print("a" or 0)
                print(0 or 6.0)
                print(0.0 or "b")
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();
        System.out.println(lines);

        assertEquals(1, Integer.parseInt(lines.get(0)));
        assertEquals("a", lines.get(1));
        assertEquals(0, Double.parseDouble(lines.get(2)));
        assertEquals(0.0, Double.parseDouble(lines.get(3)));
    }

    @Test
    public void testDisjunctionOnChainExpression() throws Exception {
        String luaCode = """
                print(1 or 2 or 3)
                print(1 or 0 or 3)
                print(0 or 1 or 3)
                print(0 or 0 or 3)
                print(0 or 0 or 0)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertEquals(1, Integer.parseInt(lines.get(0)));
        assertEquals(1, Integer.parseInt(lines.get(1)));
        assertEquals(0, Integer.parseInt(lines.get(2)));
        assertEquals(0, Integer.parseInt(lines.get(3)));
        assertEquals(0, Integer.parseInt(lines.get(4)));
    }

    @Test
    public void testDisjunctionOnChainBooleanExpression() throws Exception {
        String luaCode = """
                print(true or true or true)
                print(true or false or true)
                print(false or true or true)
                print(false or false or true)
                print(false or false or false)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertTrue(Boolean.parseBoolean(lines.get(1)));
        assertTrue(Boolean.parseBoolean(lines.get(2)));
        assertTrue(Boolean.parseBoolean(lines.get(3)));
        assertFalse(Boolean.parseBoolean(lines.get(4)));
    }

}
