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

public class ForLoopStatementTest {

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
    public void testForLoopBasic_1() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1, 5 do
                    a = a + i;
                end
                print(a);
                """;

        String output = executor.execute(executeLuaCode(luaCode)).strip();
        assertEquals("15", output);
    }

    @Test
    public void testForLoopBasic_2() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1, 5, 2 do
                    a = a + i;
                end
                print(a);
                """;

        String output = executor.execute(executeLuaCode(luaCode)).strip();
        assertEquals("9", output);
    }

    @Test
    public void testForLoopBasic_3() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 5, 1, -1 do
                    a = a + i;
                end
                print(a);
                """;

        String output = executor.execute(executeLuaCode(luaCode)).strip();
        assertEquals("15", output);
    }

    @Test
    public void testForLoopIntInitIntLimit() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1, 5 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("1", output.get(0));
        assertEquals("3", output.get(1));
        assertEquals("6", output.get(2));
        assertEquals("10", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopIntInitIntLimitIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1, 5, 2 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(3, output.size());
        assertEquals("1", output.get(0));
        assertEquals("4", output.get(1));
        assertEquals("9", output.get(2));
    }

    @Test
    public void testForLoopIntInitIntLimitNegativeIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 5, 1, -1 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("5", output.get(0));
        assertEquals("9", output.get(1));
        assertEquals("12", output.get(2));
        assertEquals("14", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopIntInitFloatLimit() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1, 5.5 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("1", output.get(0));
        assertEquals("3", output.get(1));
        assertEquals("6", output.get(2));
        assertEquals("10", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopIntInitFloatLimitIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1, 5.5, 2 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(3, output.size());
        assertEquals("1", output.get(0));
        assertEquals("4", output.get(1));
        assertEquals("9", output.get(2));
    }

    @Test
    public void testForLoopIntInitFloatLimitNegativeIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 5, 1.5, -1 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("5", output.get(0));
        assertEquals("9", output.get(1));
        assertEquals("12", output.get(2));
        assertEquals("14", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopFloatInitIntLimit() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1.5, 5 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("1.5", output.get(0));
        assertEquals("3.5", output.get(1));
        assertEquals("6.5", output.get(2));
        assertEquals("10.5", output.get(3));
        assertEquals("15.5", output.get(4));
    }

    @Test
    public void testForLoopFloatInitIntLimitIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1.5, 5, 2 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(2, output.size());
        assertEquals("1.5", output.get(0));
        assertEquals("5.0", output.get(1));
    }

    @Test
    public void testForLoopFloatInitIntLimitNegativeIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 5.5, 1, -1 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("5.5", output.get(0));
        assertEquals("10.5", output.get(1));
        assertEquals("15.5", output.get(2));
        assertEquals("20.5", output.get(3));
        assertEquals("26.5", output.get(4));
    }

    @Test
    public void testForLoopFloatInitFloatLimit() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1.5, 5.5 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("1.5", output.get(0));
        assertEquals("3.5", output.get(1));
        assertEquals("6.5", output.get(2));
        assertEquals("10.5", output.get(3));
        assertEquals("15.5", output.get(4));
    }

    @Test
    public void testForLoopFloatInitFloatLimitIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1.5, 5.5, 2 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(3, output.size());
        assertEquals("1.5", output.get(0));
        assertEquals("4.5", output.get(1));
        assertEquals("9.5", output.get(2));
    }

    @Test
    public void testForLoopFloatInitFloatLimitNegativeIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 5.5, 1.5, -1 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("5.5", output.get(0));
        assertEquals("10.5", output.get(1));
        assertEquals("15.5", output.get(2));
        assertEquals("20.5", output.get(3));
        assertEquals("26.5", output.get(4));
    }

    @Test
    public void testForLoopFloatInitFloatLimitFloatStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 1.5, 5.5, 2.5 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(2, output.size());
        assertEquals("1.5", output.get(0));
        assertEquals("4.0", output.get(1));
    }

    @Test
    public void testForLoopFloatInitFloatLimitNegativeFloatStep() throws Exception {
        String luaCode = """
                local a = 0;
                for i = 5.5, 1.5, -2.5 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(2, output.size());
        assertEquals("5.5", output.get(0));
        assertEquals("10.0", output.get(1));
    }

    @Test
    public void testForLoopIntInitVarLimit() throws Exception {
        String luaCode = """
                local a = 0;
                local b = 5;
                for i = 1, b do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("1", output.get(0));
        assertEquals("3", output.get(1));
        assertEquals("6", output.get(2));
        assertEquals("10", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopIntInitVarLimitIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                local b = 5;
                for i = 1, b, 2 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(3, output.size());
        assertEquals("1", output.get(0));
        assertEquals("4", output.get(1));
        assertEquals("9", output.get(2));
    }

    @Test
    public void testForLoopIntInitVarLimitNegativeIntStep() throws Exception {
        String luaCode = """
                local a = 0;
                local b = 5;
                for i = b, 1, -1 do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("5", output.get(0));
        assertEquals("9", output.get(1));
        assertEquals("12", output.get(2));
        assertEquals("14", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopIntInitVarLimitVarStep() throws Exception {
        String luaCode = """
                local a = 0;
                local b = 2;
                for i = 1, 5, b do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(3, output.size());
        assertEquals("1", output.get(0));
        assertEquals("3", output.get(1));
        assertEquals("6", output.get(2));
    }

    @Test
    public void testForLoopIntInitVarLimitNegativeVarStep() throws Exception {
        String luaCode = """
                local a = 0;
                local b = -1;
                for i = 5, 1, b do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(5, output.size());
        assertEquals("5", output.get(0));
        assertEquals("9", output.get(1));
        assertEquals("12", output.get(2));
        assertEquals("14", output.get(3));
        assertEquals("15", output.get(4));
    }

    @Test
    public void testForLoopIntInitVarLimitVarStepNegative() throws Exception {
        String luaCode = """
                local a = 0;
                local b = 2;
                for i = 5, 1, -b do
                    a = a + i;
                    print(a);
                end
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals(3, output.size());
        assertEquals("5", output.get(0));
        assertEquals("9", output.get(1));
        assertEquals("12", output.get(2));
    }


}
