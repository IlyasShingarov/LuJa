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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogicExpressionTest {

    private ParseTree createParseTree(String code) {
        CharStream input = CharStreams.fromString(code);
        LuaLexer lexer = new LuaLexer(input);
        LuaParser parser = new LuaParser(new CommonTokenStream(lexer));
        return parser.start_();
    }

    @Test
    public void testConjunctionExpression() throws Exception {
        String luaCode = """
                print(true and true)
                print(true and false)
                print(false and true)
                print(false and false)
                """;

        LuaChunkVisitor visitor = new LuaChunkVisitor();
        visitor.visit(createParseTree(luaCode));

        byte[] bytecode = visitor.getBytecodeGenerator().toByteArray();
        LuaBytecodeExecutor executor = new LuaBytecodeExecutor();
        String output = executor.execute(bytecode);
        List<String> lines = output.lines().toList();

        assertTrue(Boolean.parseBoolean(lines.get(0)));
        assertFalse(Boolean.parseBoolean(lines.get(1)));
        assertFalse(Boolean.parseBoolean(lines.get(2)));
        assertFalse(Boolean.parseBoolean(lines.get(3)));
    }
}
