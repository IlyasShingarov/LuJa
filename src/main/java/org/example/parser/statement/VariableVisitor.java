//package org.example.parser.statement;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.example.antlr.LuaParser;
//import org.example.antlr.LuaParserBaseVisitor;
//import org.example.domain.VariableAccess;
//import org.example.domain.expression.constant.StringExpression;
//import org.example.parser.expression.ExpressionVisitor;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class VariableVisitor extends LuaParserBaseVisitor<VariableAccess> {
//
//    private final ExpressionVisitor expressionVisitor;
//
//    @Override
//    public VariableAccess visitVar(LuaParser.VarContext ctx) {
//        if (ctx.NAME() != null && ctx.prefixexp() == null && ctx.exp().isEmpty()) {
//            return new VariableAccess(ctx.NAME().getText(), null);
//        } else if (ctx.prefixexp() != null) {
//            if (ctx.DOT() != null) {
//                return new VariableAccess(ctx.prefixexp().getText(), new StringExpression(ctx.NAME().getText()));
//            }
//            return new VariableAccess(ctx.prefixexp().getText(), ctx.exp().accept(expressionVisitor));
//        }
//        return null;
//    }
//}
