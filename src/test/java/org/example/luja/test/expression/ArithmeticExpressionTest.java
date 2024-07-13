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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArithmeticExpressionTest {
    private ParseTree createParseTree(String code) {
        CharStream input = CharStreams.fromString(code);
        LuaLexer lexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(new CommonTokenStream(lexer));
        return parser.start_();
    }

    @Test
    public void testIntegerAdditionOnLocalVariable() throws Exception {
        String luaCode = """
                local a = 1 + 2
                print(a)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        assertEquals(3, Integer.parseInt(output.trim()));
    }

    @Test
    public void testIntegerSubtractionOnLocalVariable() throws Exception {
        String luaCode = """
                local a = 5 - 2
                print(a)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        assertEquals(3, Integer.parseInt(output.trim()));
    }

    @Test
    public void testIntegerMultiplicationOnLocalVariable() throws Exception {
        String luaCode = """
                local a = 2 * 3
                print(a)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        assertEquals(6, Integer.parseInt(output.trim()));
    }

    @Test
    public void testIntegerFloatDivisionOnLocalVariable() throws Exception {
        String luaCode = """
                local a = 6 / 2
                print(a)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        assertEquals(3.0, Double.parseDouble(output.trim()));
    }

    @Test
    public void testIntegerFloorDivisionOnLocalVariable() throws Exception {
        String luaCode = """
                local a = 7 // 2
                print(a)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        assertEquals(3, Integer.parseInt(output.trim()));
    }

    @Test
    public void testIntegerModuloOnLocalVariable() throws Exception {
        String luaCode = """
                local a = 7 % 2
                print(a)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        assertEquals(1, Integer.parseInt(output.trim()));
    }

    @Test
    public void testIntegerArithmeticExpression() throws Exception {
        String luaCode = """
                print(1 + 2)
                print(5 - 2)
                print(2 * 3)
                print(6 / 2)
                print(7 // 2)
                print(7 % 2)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        String[] lines = output.split("\n");

        assertEquals(3, Integer.parseInt(lines[0]));
        assertEquals(3, Integer.parseInt(lines[1]));
        assertEquals(6, Integer.parseInt(lines[2]));
        assertEquals(3.0, Double.parseDouble(lines[3]));
        assertEquals(3, Integer.parseInt(lines[4]));
        assertEquals(1, Integer.parseInt(lines[5]));
    }

    @Test
    public void testFloatArithmeticExpression() throws Exception {
        String luaCode = """
                print(1.0 + 2.0)
                print(5.0 - 2.0)
                print(2.0 * 3.0)
                print(6.0 / 2.0)
                print(7.0 // 2.0)
                print(7.0 % 2.0)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        String[] lines = output.split("\n");

        assertEquals(3.0, Double.parseDouble(lines[0]));
        assertEquals(3.0, Double.parseDouble(lines[1]));
        assertEquals(6.0, Double.parseDouble(lines[2]));
        assertEquals(3.0, Double.parseDouble(lines[3]));
        assertEquals(3.0, Double.parseDouble(lines[4]));
        assertEquals(1.0, Double.parseDouble(lines[5]));
    }

    @Test
    public void testFloatIntegerArithmeticExpression() throws Exception {
        String luaCode = """
                print(1.0 + 2)
                print(5 - 2.0)
                print(2.0 * 3)
                print(6 / 2.0)
                print(7 // 2.0)
                print(7.0 % 2)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        String[] lines = output.split("\n");

        assertEquals(3.0, Double.parseDouble(lines[0]));
        assertEquals(3.0, Double.parseDouble(lines[1]));
        assertEquals(6.0, Double.parseDouble(lines[2]));
        assertEquals(3.0, Double.parseDouble(lines[3]));
        assertEquals(3.0, Double.parseDouble(lines[4]));
        assertEquals(1.0, Double.parseDouble(lines[5]));
    }

    @Test
    public void testIntegerFloatArithmeticExpression() throws Exception {
        String luaCode = """
                print(1 + 2.0)
                print(5.0 - 2)
                print(2 * 3.0)
                print(6.0 / 2)
                print(7 // 2.0)
                print(7.0 % 2)
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        String[] lines = output.split("\n");

        assertEquals(3.0, Double.parseDouble(lines[0]));
        assertEquals(3.0, Double.parseDouble(lines[1]));
        assertEquals(6.0, Double.parseDouble(lines[2]));
        assertEquals(3.0, Double.parseDouble(lines[3]));
        assertEquals(3.0, Double.parseDouble(lines[4]));
        assertEquals(1.0, Double.parseDouble(lines[5]));
    }

    @Test
    public void testFloatArithmeticExpressionWithParentheses() throws Exception {
        String luaCode = """
                print((1.0 + 2.0) * 3.0)
                print(5.0 - (2.0 * 3.0))
                print(2.0 * (3.0 / 2.0))
                print((6.0 / 2.0) // 2.0)
                print(7.0 // (2.0 % 2.0))
                print(7.0 % (2.0 + 1.0))
                """;

        LuaChunkVisitor chunkVisitor = new LuaChunkVisitor();
        chunkVisitor.visit(createParseTree(luaCode));

        byte[] bytecode = chunkVisitor.getBytecodeGenerator().toByteArray();

        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);

        String[] lines = output.split("\n");

        assertEquals(9.0, Double.parseDouble(lines[0]));
        assertEquals(-1.0, Double.parseDouble(lines[1]));
        assertEquals(3.0, Double.parseDouble(lines[2]));
        assertEquals(1.0, Double.parseDouble(lines[3]));
        assertEquals(3.0, Double.parseDouble(lines[4]));
        assertEquals(1.0, Double.parseDouble(lines[5]));
    }
}
