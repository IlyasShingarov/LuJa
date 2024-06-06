package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.Block;
import org.example.domain.Chunk;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChunkVisitor extends LuaParserBaseVisitor<Chunk> {

    private final BlockVisitor blockVisitor;

    @Override
    public Chunk visitChunk(LuaParser.ChunkContext ctx) {
        log.info("Visiting chunk");
        LuaParser.BlockContext blockContext = ctx.block();
        Block block = blockContext.accept(blockVisitor);

        log.info("Returning from chunk");
        return new Chunk(block);
    }

}
