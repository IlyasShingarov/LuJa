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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalVariableAssignmentTest {

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
    public void testLocalVariableAssignmentWithIntegerConstant() throws Exception {
        String luaCode = """
                local a = 1;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("1", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithFloatConstant() throws Exception {
        String luaCode = """
                local a = 2.0;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("2.0", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithBooleanConstant() throws Exception {
        String luaCode = """
                local a = true;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("true", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithStringConstant() throws Exception {
        String luaCode = """
                local a = "Hello";
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("Hello", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithNilConstant() throws Exception {
        String luaCode = """
                local a = nil;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("nil", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithConstants() throws Exception {
        String luaCode = """
                local a = 1;
                local b = 2.0;
                local c = true;
                local d = false;
                local e = "Hello";
                
                print(a);
                print(b);
                print(c);
                print(d);
                print(e);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        var lines = output.lines().toList();

        assertEquals("1", lines.get(0));
        assertEquals("2.0", lines.get(1));
        assertEquals("true", lines.get(2));
        assertEquals("false", lines.get(3));
        assertEquals("Hello", lines.get(4));
    }

    @Test
    public void testLocalVariableAssignmentWithLocalVariable() throws Exception {
        String luaCode = """
                local a = 1;
                local b = a;
                print(b);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("1", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithLocalVariableExpression() throws Exception {
        String luaCode = """
                local a = 1;
                local b = a + 1;
                print(b);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("2", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithConstantExpression() throws Exception {
        String luaCode = """
                local a = 1 + 2;
                print(a);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("3", output.trim());
    }

    @Test
    public void testLocalVariableAssignmentWithGlobalVariable() throws Exception {
        String luaCode = """
                a = 1;
                local b = a;
                print(b);
                """;

        byte[] bytecode = executeLuaCode(luaCode);
        String output = executor.execute(bytecode);
        assertEquals("1", output.trim());
    }

}
