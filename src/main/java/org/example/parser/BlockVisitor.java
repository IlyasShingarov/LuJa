package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.Block;
import org.example.domain.statement.Statement;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BlockVisitor extends LuaParserBaseVisitor<Block> {

    private final StatementVisitor statementVisitor;

    @Override
    public Block visitBlock(LuaParser.BlockContext ctx) {
        log.info("Visiting block");
        List<Statement> statements = ctx.stat().stream()
                .map(statement -> statement.accept(statementVisitor))
                .toList();

        log.info("Returning from block");
        return new Block(statements);
    }

}
