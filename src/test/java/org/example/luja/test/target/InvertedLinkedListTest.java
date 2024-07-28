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

public class InvertedLinkedListTest {
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
    public void testInvertedLinkedList() throws Exception {
        String luaCode = """
                local function invertLinkedList(head)
                    local prev = nil
                    local current = head
                    while current do
                        local next = current.next
                        current.next = prev
                        prev = current
                        current = next
                    end
                    return prev
                end
                
                local function printLinkedList(head)
                    local current = head
                    while current do
                        print(current.value)
                        current = current.next
                    end
                end
                
                local function createLinkedList()
                    local head = {value = 1}
                    local current = head
                    for i = 2, 5 do
                        current.next = {value = i}
                        current = current.next
                    end
                    return head
                end
                
                local head = createLinkedList()
                head = invertLinkedList(head)
                printLinkedList(head)
                """;
        List<String> output = executor.execute(executeLuaCode(luaCode)).lines().toList();
        assertEquals("5", output.get(0));
        assertEquals("4", output.get(1));
        assertEquals("3", output.get(2));
        assertEquals("2", output.get(3));
        assertEquals("1", output.get(4));
    }
}
