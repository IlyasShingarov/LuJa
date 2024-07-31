package org.example.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.domain.expression.Expression;
import org.example.domain.statement.StaticField;
import org.example.luja.compiler.symbol.MainContextManager;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.FieldNode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class GlobalVariableVisitor extends LuaParserBaseVisitor<List<StaticField>> implements Opcodes {

    private final List<StaticField> fields = new ArrayList<>();
    private final MainContextManager contextManager;

    @Override
    public List<StaticField> visitChunk(LuaParser.ChunkContext ctx) {
        log.info("Collecting global variables");
        visitChildren(ctx);
        return fields;
    }

    @Override
    public List<StaticField> visitVardecl(LuaParser.VardeclContext ctx) {
        if (ctx.LOCAL() != null) return null;
        log.info("Probable global variables - {}", ctx.getText());
        List<String> names = ctx.varlist().var().stream()
                .map(varContext -> {
                    if (varContext.NAME() != null) {
                        return varContext.NAME();
                    } else {
                        return varContext.prefixexp().NAME(0);
                    }
                })
                .map(ParseTree::getText)
                .filter(name -> contextManager.getCurrentScope().isGlobal(name))
                .toList();

        if (names.isEmpty()) {
            log.info("Variables are not global");
            return null;
        }

        LuaExpressionVisitor expVisitor = new LuaExpressionVisitor(contextManager);
        List<Expression> values = ctx.explist().exp().stream()
                .map(expVisitor::visit)
                .toList();
        log.info("Initializing expressions - {}", values);

        for (int i = 0; i < names.size(); i++) {
            String varname = names.get(i);
            Expression value = values.get(i);
            fields.add(
                    new StaticField(
                            new FieldNode(ACC_STATIC + ACC_PUBLIC, varname, Type.getDescriptor(Object.class), null, null),
                            value
                    )
            );
        }

        for (StaticField sf : fields) {
            log.info("Field: {}", sf);
        }

        return null;
    }
}
