package org.example.luja.test.target;

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

public class FibbonaciTest {

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
    public void testFibonacci() throws Exception {
        String luaCode = """
                function fibonacci(n)
                    if n <= 1 then
                        return n
                    else
                        return fibonacci(n - 1) + fibonacci(n - 2)
                    end
                end
                print(fibonacci(10))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("55", output.trim());
    }

    @Test
    public void testFibonacci_2() throws Exception {
        String luaCode = """
                function fibonacci(n)
                    if n <= 1 then
                        return n
                    else
                        return fibonacci(n - 1) + fibonacci(n - 2)
                    end
                end
                print(fibonacci(20))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("6765", output.trim());
    }

}
