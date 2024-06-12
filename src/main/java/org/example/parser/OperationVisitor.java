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

    @Override
    public BinaryOperation visitMultop(LuaParser.MultopContext ctx) {
        if (ctx.STAR() != null) {
            return BinaryOperation.MULTIPLY;
        } else if (ctx.SLASH() != null) {
            return BinaryOperation.DIVIDE;
        } else if (ctx.PER() != null) {
            return BinaryOperation.MODULO;
        } else if (ctx.SS() != null) {
            return BinaryOperation.DIVIDE_FLOOR;
        }
        return super.visitMultop(ctx);
    }

    @Override
    public BinaryOperation visitRelop(LuaParser.RelopContext ctx) {
        if (ctx.LT() != null) {
            return BinaryOperation.LESS_THAN;
        } else if (ctx.LE() != null) {
            return BinaryOperation.LESS_THAN_OR_EQUALS;
        } else if (ctx.GT() != null) {
            return BinaryOperation.GREATER_THAN;
        } else if (ctx.GE() != null) {
            return BinaryOperation.GREATER_THAN_OR_EQUALS;
        } else if (ctx.EE() != null) {
            return BinaryOperation.EQUALS;
        } else if (ctx.SQEQ() != null) {
            return BinaryOperation.NOT_EQUALS;
        }
        return super.visitRelop(ctx);
    }
}
