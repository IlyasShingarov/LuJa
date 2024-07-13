package org.example.luja.test.assignment;

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

public class GlobalVariableAssignmentTest {
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
    public void testGlobalVariableAssignmentWithIntegerConstant() throws Exception {
        String luaCode = """
                a = 1;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("1", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithFloatConstant() throws Exception {
        String luaCode = """
                a = 2.0;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("2.0", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithBooleanConstant() throws Exception {
        String luaCode = """
                a = true;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("true", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithNilConstant() throws Exception {
        String luaCode = """
                a = nil;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("nil", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithStringConstant() throws Exception {
        String luaCode = """
                a = "Hello";
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("Hello", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithMultipleVariables() throws Exception {
        String luaCode = """
                a = 1;
                b = 2;
                c = 3;
                print(a);
                print(b);
                print(c);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();
        assertEquals("1", lines.get(0));
        assertEquals("2", lines.get(1));
        assertEquals("3", lines.get(2));
    }

    @Test
    public void testGlobalVariableAssignmentWithMultipleTypes() throws Exception {
        String luaCode = """
                a = 1;
                b = 2.0;
                c = true;
                d = "Hello";
                print(a);
                print(b);
                print(c);
                print(d);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();
        assertEquals("1", lines.get(0));
        assertEquals("2.0", lines.get(1));
        assertEquals("true", lines.get(2));
        assertEquals("Hello", lines.get(3));
    }

    @Test
    public void testGlobalVariableAssignmentWithArithmeticExpression() throws Exception {
        String luaCode = """
                a = 1 + 2;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("3", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithRelationExpression() throws Exception {
        String luaCode = """
                c = 1 == 1;
                d = 1 ~= 1;
                print(c);
                print(d);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();
        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
    }

    @Test
    public void testGlobalVariableAssignmentWithLogicalExpression() throws Exception {
        String luaCode = """
                a = true and true;
                b = true and false;
                c = false and false;
                d = false and true;
                e = true or true;
                f = true or false;
                g = false or false;
                h = false or true;
                print(a);
                print(b);
                print(c);
                print(d);
                print(e);
                print(f);
                print(g);
                print(h);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();
        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
        assertFalse(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
        assertTrue(Boolean.parseBoolean(lines.get(4)));
        assertTrue(Boolean.parseBoolean(lines.get(5)));
        assertFalse(Boolean.parseBoolean(lines.get(6)));
        assertTrue(Boolean.parseBoolean(lines.get(7)));
    }

    @Test
    public void testGlobalVariableAssignmentWithVariableExpression() throws Exception {
        String luaCode = """
                a = 1;
                b = a;
                print(b);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("1", output.trim());
    }

    @Test
    public void testGlobalVariableAssignmentWithLocalVariableExpression() throws Exception {
        String luaCode = """
                local a = 1;
                b = a + 1;
                print(b);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("2", output.trim());
    }
}
