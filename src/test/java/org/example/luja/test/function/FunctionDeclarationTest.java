package org.example.luja.test.function;

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

public class FunctionDeclarationTest {

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
    public void testSimpleFunctionDeclaration() throws Exception {
        String luaCode = """
                function test()
                    print("Hello, World!")
                end
                test()
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("Hello, World!", output.trim());
    }

    @Test
    public void testFunctionDeclarationWithParameters() throws Exception {
        String luaCode = """
                function test(a, b)
                    print(a + b)
                end
                test(1, 2)
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("3", output.trim());
    }

    @Test
    public void testFunctionDeclarationWithReturn() throws Exception {
        String luaCode = """
                function test(a, b)
                    return a + b
                end
                print(test(1, 2))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("3", output.trim());
    }

    @Test
    public void testFunctionDeclarationWithInnerIfStatement() throws Exception {
        String luaCode = """
                function test(a, b)
                    if a > b then
                        return a
                    else
                        return b
                    end
                end
                print(test(1, 2))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("2", output.trim());
    }

    @Test
    public void testFunctionDeclarationWithInnerFunctionCall() throws Exception {
        String luaCode = """
                function test(a, b)
                    return a + b
                end
                function test2(a, b)
                    return test(a, b)
                end
                print(test2(1, 2))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("3", output.trim());
    }

    @Test
    public void testFunctionDeclarationWithInnerForLoopStatement() throws Exception {
        String luaCode = """
                function test(a, b)
                    local sum = 0
                    for i = a, b do
                        sum = sum + i
                    end
                    return sum
                end
                print(test(1, 10))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("55", output.trim());
    }

    @Test
    public void testFunctionDeclarationWithInnerWhileLoopStatement() throws Exception {
        String luaCode = """
                function test(a, b)
                    local sum = 0
                    while a <= b do
                        sum = sum + a
                        a = a + 1
                    end
                    return sum
                end
                print(test(1, 10))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("55", output.trim());
    }

    @Test
    public void testRecursiveFunctionDeclaration() throws Exception {
        String luaCode = """
                function factorial(n)
                    if n == 0 then
                        return 1
                    else
                        return n * factorial(n - 1)
                    end
                end
                print(factorial(5))
                """;
        String output = executor.execute(executeLuaCode(luaCode));
        assertEquals("120", output.trim());
    }
}
