package org.example.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.expression.*;
import org.example.domain.variable.SymbolType;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExpressionVisitor extends LuaParserBaseVisitor<Expression> {

    @Override
    public Expression visitNumber(LuaParser.NumberContext ctx) {
        if (ctx.INT() != null) {
            return new IntegerExpression(Integer.parseInt(ctx.INT().getText()));
        } else if (ctx.FLOAT() != null) {
            return new FloatExpression(Float.parseFloat(ctx.FLOAT().getText()));
        }
        return null;
    }

    @Override
    public Expression visitString(LuaParser.StringContext ctx) {
        if (ctx.NORMALSTRING() != null) {
            return new StringExpression(ctx.NORMALSTRING().getText());
        } else if (ctx.CHARSTRING() != null) {
            return new StringExpression(ctx.CHARSTRING().getText());
        } else if (ctx.LONGSTRING() != null) {
            return new StringExpression(ctx.LONGSTRING().getText());
        }
        return null;
    }

    //    @Override
//    public Expression visitExp(LuaParser.ExpContext ctx) {
//        log.info("Visiting expression: {}", ctx.getText());
//        if (ctx.binop() != null) {
//            log.info("Binary operation detected");
//            var operation = ctx.binop();
//            Value operand1 = (Value) ctx.exp(0).accept(this);
//            Value operand2 = (Value) ctx.exp(1).accept(this);
//            if (operation.addop() != null) {
//                log.info("Addition operation detected {}", operation.addop().getText());
//                log.info("Evaluated operands: {} and {}", operand1, operand2);
//                if (operation.addop().PLUS() != null) {
//                    var value = Integer.parseInt(operand1.value().toString()) + Integer.parseInt(operand2.value().toString());
//                    log.error("VALUE IS {}", value);
//                    return new Value(value, SymbolType.NUMBER);
//                }
//            } else if (operation.multop() != null) {
//                log.info("Multiplication operation detected {}", operation.multop().getText());
//            } else {
//                log.info("Unrecognized operation");
//            }
//        } else if (ctx.number() != null) {
//            log.info("Number detected {}", ctx.number().getText());
//            if (ctx.number().INT() != null)
//                return new Value(ctx.number().INT().getText(), SymbolType.NUMBER);
//        } else {
//            log.info("Unrecognized expression");
//        }
//        log.info("Returning from expression");
//        return null;
//    }
}
