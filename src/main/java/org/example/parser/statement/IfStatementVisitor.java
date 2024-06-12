package org.example.parser.statement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.domain.Condition;
import org.example.domain.statement.IfElseStatement;
import org.example.parser.ExpressionVisitor;
import org.example.parser.InnerBlockVisitor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class IfStatementVisitor extends LuaParserBaseVisitor<IfElseStatement> {

    private final InnerBlockVisitor innerBlockVisitor;
    private final ExpressionVisitor expressionVisitor;

    private final LuaBytecodeGenerator bytecodeGenerator;

    @Override
    public IfElseStatement visitIfstat(LuaParser.IfstatContext ctx) {
        log.info("Visiting if statement {}", ctx.getText());

        // Формируем условия
        List<Condition> conditions = new ArrayList<>();
        for (int i = 0; i < ctx.exp().size(); i++) {
            log.info("Visiting condition: {}", ctx.exp(i).getText());
            LuaParser.ExpContext expContext = ctx.exp(i);
            LuaParser.BlockContext blockContext = ctx.block(i);
            conditions.add(new Condition(
                    expressionVisitor.visit(expContext),
                    () -> innerBlockVisitor.visit(blockContext)
            ));
        }

        // Формируем блок else
        Runnable elseBlock = ctx.block().size() > ctx.exp().size()
                ? () -> innerBlockVisitor.visit(ctx.block(ctx.block().size() - 1))
                : null;

        bytecodeGenerator.generateIfElseStatement(conditions, elseBlock);

        return super.visitIfstat(ctx);
    }
}
