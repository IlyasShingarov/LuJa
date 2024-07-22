//package org.example.parser.expression;
//
//import lombok.RequiredArgsConstructor;
//import org.example.antlr.LuaParser;
//import org.example.antlr.LuaParserBaseVisitor;
//import org.example.symbol.FunctionSymbol;
//import org.example.symbol.Symbol;
//import org.example.symbol.SymbolTable;
//import org.objectweb.asm.Type;
//import org.springframework.stereotype.Component;
//
//public class ExpressionTypeVisitor extends LuaParserBaseVisitor<Type> {
//
//    private final SymbolTable symbolTable;
//
//    @Override
//    public Type visitExp(LuaParser.ExpContext ctx) {
//        if (ctx.TRUE() != null || ctx.FALSE() != null) {
//            return Type.BOOLEAN_TYPE;
//        } else if (ctx.number() != null) {
//            return visitNumber(ctx.number());
//        } else if (ctx.string() != null) {
//            return visitString(ctx.string());
//        } else if (ctx.prefixexp() != null) {
//            return visitPrefixexp(ctx.prefixexp());
//        } else if (ctx.binop() != null) {
//            Type left = visitExp(ctx.exp(0));
//            Type right = visitExp(ctx.exp(1));
//            if (left.equals(Type.INT_TYPE) && right.equals(Type.INT_TYPE)) {
//                return Type.INT_TYPE;
//            } else if (left.equals(Type.FLOAT_TYPE) || right.equals(Type.FLOAT_TYPE)) {
//                return Type.FLOAT_TYPE;
//            }
//            return visitBinop(ctx.binop());
//        } else {
//            return Type.VOID_TYPE; // Default case, should be handled as needed
//        }
//    }
//
//    @Override
//    public Type visitNumber(LuaParser.NumberContext ctx) {
//        if (ctx.INT() != null) {
//            return Type.INT_TYPE;
//        } else if (ctx.FLOAT() != null) {
//            return Type.FLOAT_TYPE;
//        }
//        return Type.VOID_TYPE; // Default case, should be handled as needed
//    }
//
//    @Override
//    public Type visitString(LuaParser.StringContext ctx) {
//        return Type.getType(String.class);
//    }
//
//    @Override
//    public Type visitPrefixexp(LuaParser.PrefixexpContext ctx) {
//        if (!ctx.NAME().isEmpty() && ctx.NAME(0) != null) {
//            // We need to look up the variable type in the symbol table.
//            Symbol symbol = symbolTable.getLocalVariable(ctx.NAME(0).getText());
//            // If it's an array access we need to get the type of the element.
//            if (ctx.exp() != null) {
//                return Type.getType(Object.class);
//            }
//            return symbol != null ? symbol.type() : Type.VOID_TYPE;
//        } else if (ctx.functioncall() != null) {
//            FunctionSymbol functionSymbol = symbolTable.getFunction(ctx.functioncall().NAME(0).getText());
//            return functionSymbol != null ? functionSymbol.type() : Type.VOID_TYPE;
//        }
//        return super.visitPrefixexp(ctx);
//    }
//
//}
