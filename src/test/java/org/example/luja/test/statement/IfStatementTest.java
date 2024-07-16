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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IfStatementTest {

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
    public void testElseIfStatementBasic_1() throws Exception {
        String luaCode = """
                local a = 5;
                if a == 10 then
                    print("a is 10");
                elseif a == 5 then
                    print("a is 5");
                else
                    print("a is not 10")
                end
                """;

        String output = executor.execute(executeLuaCode(luaCode)).strip();
        assertEquals("a is 5", output);
    }

    @Test
    public void testElseIfStatementBasic_2() throws Exception {
        String luaCode = """
                local a = 10;
                if a == 10 then
                    print("a is 10");
                elseif a == 5 then
                    print("a is 5");
                else
                    print("a is not 10")
                end
                """;

        String output = executor.execute(executeLuaCode(luaCode)).strip();
        assertEquals("a is 10", output);
    }

    @Test
    public void testElseIfStatementBasic_3() throws Exception {
        String luaCode = """
                local a = 2;
                if a == 10 then
                    print("a is 10");
                elseif a == 5 then
                    print("a is 5");
                else
                    print("a is not 10")
                end
                """;

        String output = executor.execute(executeLuaCode(luaCode)).strip();
        assertEquals("a is not 10", output);
    }
}
