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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSortTest {
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
    public void testBubbleSort() throws Exception {
        String luaCode = """
                function bubbleSort(arr)
                    local n = 7
                    for i = 1, n do
                        for j = 1, n - i do
                            if arr[j] > arr[j + 1] then
                                local temp = arr[j]
                                arr[j] = arr[j + 1]
                                arr[j + 1] = temp
                            end
                        end
                    end
                    return arr
                end
                local arr = {64, 34, 25, 12, 22, 11, 90}
                arr = bubbleSort(arr)
                for i = 1, 7 do
                    print(arr[i])
                end
                """;
        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("11", output.get(0));
        assertEquals("12", output.get(1));
        assertEquals("22", output.get(2));
        assertEquals("25", output.get(3));
        assertEquals("34", output.get(4));
        assertEquals("64", output.get(5));
        assertEquals("90", output.get(6));
    }

}
