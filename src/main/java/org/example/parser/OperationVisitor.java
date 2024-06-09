package org.example.parser;

import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.expression.BinaryOperation;
import org.springframework.stereotype.Component;

@Component
public class OperationVisitor extends LuaParserBaseVisitor<BinaryOperation> {

    @Override
    public BinaryOperation visitAddop(LuaParser.AddopContext ctx) {
        if (ctx.PLUS() != null) {
            return BinaryOperation.ADD;
        } else if (ctx.MINUS() != null) {
            return BinaryOperation.SUBTRACT;
        }
        return super.visitAddop(ctx);
    }
}
