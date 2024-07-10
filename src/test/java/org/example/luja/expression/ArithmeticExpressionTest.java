package org.example.luja.expression;

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
    public void testFloorDivisionOnLocalVariable() throws Exception {
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
    public void testModuloOnLocalVariable() throws Exception {
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


}
