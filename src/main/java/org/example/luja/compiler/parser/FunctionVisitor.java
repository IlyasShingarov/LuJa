package org.example.luja.compiler.parser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.tree.ParseTree;
import org.example.antlr.LuaParser;
import org.example.antlr.LuaParserBaseVisitor;
import org.example.luja.compiler.symbol.ContextManager;
import org.example.luja.compiler.symbol.ContextManagerFactory;
import org.example.temp.CodeGen;
import org.example.temp.StatementVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FunctionVisitor extends LuaParserBaseVisitor<List<MethodNode>> implements Opcodes {
    private final List<MethodNode> methodNodes = new ArrayList<>();
    private final ContextManagerFactory contextManagerFactory;
    private final CodeGen gen;
    private ContextManager contextManager;

    @Override
    public List<MethodNode> visitChunk(LuaParser.ChunkContext ctx) {
        visit(ctx.block());
        return methodNodes;
    }

    @Override
    public List<MethodNode> visitFuncdecl(LuaParser.FuncdeclContext ctx) {
        log.info("Visit function declaration {}", ctx.getText());
        if (ctx.LOCAL() != null) {
            return null;
        }

        String functionName = ctx.funcname().getText();
        contextManager = contextManagerFactory.createFunctionContextManager(functionName);
        contextManager.enterScope();

        LuaParser.ParlistContext parlist = ctx.funcbody().parlist();
        List<String> parameters = new ArrayList<>();
        if (parlist != null && parlist.namelist() != null) {
            parameters = parlist.namelist().NAME().stream()
                    .map(ParseTree::getText)
                    .toList();
        }

        String functionDescriptor = "(%s)%s".formatted(
                String.join("", parameters.stream().map(p -> "Ljava/lang/Object;").toList()),
                "Ljava/lang/Object;"
        );

        MethodNode methodNode = new MethodNode();
        methodNode.name = functionName;
        methodNode.desc = functionDescriptor;
        methodNode.access = ACC_PUBLIC | ACC_STATIC;

        LuaParser.BlockContext block = ctx.funcbody().block();
        InsnList instructions = new StatementVisitor(contextManager, gen).visit(block);
        methodNode.instructions.add(instructions);

        if (block != null && (block.retstat() == null || block.retstat().isEmpty())) {
            methodNode.instructions.add(new InsnNode(ACONST_NULL));
            methodNode.instructions.add(new InsnNode(ARETURN));
        }
        methodNodes.add(methodNode);
        log.info("Function name: {}  Parameters: {}", functionName, parameters);

        return null;
//        return super.visitFuncdecl(ctx);
    }
}
