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

public class TableReassignmentTest {
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
    public void testTableReassignment() throws Exception {
        String luaCode = """
                local a = {1, 2, 3};
                a[1] = 4;
                a[2] = 5;
                a[3] = 6;
                print(a[1]);
                print(a[2]);
                print(a[3]);
                """;

        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("4", output.get(0));
        assertEquals("5", output.get(1));
        assertEquals("6", output.get(2));
    }

}
