package org.example.parser.global;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.bytecode.LuaBytecodeGenerator;
import org.example.bytecode.MethodBytecodeBuilder;
import org.example.domain.expression.Expression;
import org.example.parser.statement.StatementVisitor;
import org.example.symbol.FunctionSymbol;
import org.example.symbol.SymbolTable;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GlobalFunctionDeclarationVisitor extends LuaParserBaseVisitor<Void> {

    private final LuaBytecodeGenerator bytecodeGenerator;
    private final SymbolTable symbolTable;

    private final StatementVisitor statementVisitor;

    @Override
    public Void visitFuncdecl(LuaParser.FuncdeclContext ctx) {
        if (ctx.LOCAL() != null) {
            return null;
        }
        log.info("Visiting function declaration {} ", ctx.getText());
        String functionName = ctx.funcname().getText();
        List<String> parameters = ctx.funcbody().parlist().namelist().NAME().stream()
                .map(ParseTree::getText)
                .toList();

        List<Type> parameterTypes = ctx.funcbody().parlist().namelist().typehint().stream()
                .map(this::convertToType)
                .toList();

        log.info("Function name: {}  Parameters: {}", functionName, parameters);

        Type returnType = convertToType(ctx.funcbody().typehint());

        FunctionSymbol functionSymbol = new FunctionSymbol(functionName, returnType, "global");
        symbolTable.addFunction(functionSymbol);

        symbolTable.enterScope();

        for (int i = 0; i < parameters.size(); i++) {
            symbolTable.addLocalVariable(parameters.get(i), parameterTypes.get(i), "parameter");
        }

        new MethodBytecodeBuilder(bytecodeGenerator.getClassWriter(), bytecodeGenerator)
                .withName(functionName)
                .withParameters(parameterTypes)
                .withReturnType(returnType)
                .withInstructions(
                        List.of(() -> statementVisitor.visit(ctx.funcbody().block()))
                )
                .initMethod()
                .endMethod();

//        statementVisitor.visit(ctx.funcbody().block());

        // Завершение метода
//        bytecodeGenerator.endMethod(mv, returnType);

        symbolTable.exitScope();

        return null;
    }

    private Type convertToType(LuaParser.TypehintContext ctx) {
        if (ctx == null) {
            return Type.getType(Object.class);
        }
        return switch (ctx.getText()) {
            case "int" -> Type.INT_TYPE;
            case "float" -> Type.FLOAT_TYPE;
            case "string" -> Type.getType(String.class);
            case "boolean" -> Type.BOOLEAN_TYPE;
            case "void" -> Type.VOID_TYPE;
            case "table" -> Type.getType(Object.class); // Пример для таблицы, нужно будет уточнить
            default -> throw new IllegalStateException("Unsupported type hint: " + ctx.getText());
        };
    }


}
