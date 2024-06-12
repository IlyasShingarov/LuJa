package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.expression.Expression;
import org.example.domain.statement.WhileLoop;
import org.example.parser.ExpressionVisitor;
import org.example.parser.InnerBlockVisitor;
import org.example.symbol.SymbolTable;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WhileLoopVisitor extends LuaParserBaseVisitor<WhileLoop> {

    private final ExpressionVisitor expressionVisitor;
    private final InnerBlockVisitor innerBlockVisitor;

    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

    @Override
    public WhileLoop visitWhileloop(LuaParser.WhileloopContext ctx) {
        log.info("Visiting while loop {}", ctx.getText());
        log.info("Condition: {}", ctx.exp().getText());
        log.info("Block: {}", ctx.block().getText());

        Expression condition = expressionVisitor.visit(ctx.exp());

        bytecodeGenerator.generateWhileLoop(condition, () -> {
            log.info("Entering while loop block");
            innerBlockVisitor.visit(ctx.block());
        });

        return super.visitWhileloop(ctx);
    }

}
