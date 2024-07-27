package org.example.temp;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.expression.*;
import org.example.domain.expression.constant.*;
import org.example.domain.statement.StaticField;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;

import java.lang.invoke.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Getter
public class ClassBuilder implements Opcodes {

    private final ClassNode classNode;
    private final List<MethodNode> methodNodes;
    private final List<FieldNode> fieldNodes;
    private final List<StaticField> staticFields;

    private static final Handle OPERATOR_HANDLE = makeHandle("OperatorSupport", Type.INT_TYPE.getDescriptor());

    private static Handle makeHandle(String methodName, String description) {
        return new Handle(H_INVOKESTATIC,
                "org/example/luja/runtime/" + methodName,
                "bootstrap",
                "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;"
                        + description + ")Ljava/lang/invoke/CallSite;", false);
    }

    public ClassBuilder(String className, String superClassName) {
        this.classNode = new ClassNode();
        this.classNode.version = V1_8;
        this.classNode.access = ACC_PUBLIC;
        this.classNode.name = className;
        this.classNode.superName = superClassName;

        this.methodNodes = new ArrayList<>();
        this.fieldNodes = new ArrayList<>();
        this.staticFields = new ArrayList<>();
    }

    public InsnList makeInvokeDynamic(String name) {
        InsnList instructions = new InsnList();
        instructions.add(new InvokeDynamicInsnNode(
                name,
                MethodType.genericMethodType(2).toMethodDescriptorString(),
                OPERATOR_HANDLE, (Integer) 2
                )
        );
        return instructions;
    }

    public MethodBuilder newMethod(int access, String name, String desc) {
        MethodNode methodNode = new MethodNode(access, name, desc, null, null);
        methodNodes.add(methodNode);
        return new MethodBuilder(methodNode);
    }

    public ClassBuilder addField(int access, String name, String desc, Object value) {
        FieldNode node = new FieldNode(access, name, desc, null, value);
        fieldNodes.add(node);
        return this;
    }

    public ClassBuilder addStaticFields(List<StaticField> fields) {
        log.debug("Adding static fields: {}", fields);
        staticFields.addAll(fields);
        return this;
    }

    public ClassBuilder addFields(List<FieldNode> fields) {
        fieldNodes.addAll(fields);
        return this;
    }

    public ClassBuilder addField(FieldNode fieldNode) {
        fieldNodes.add(fieldNode);
        return this;
    }

    private void makeStaticFields() {
        classNode.fields.addAll(staticFields.stream().map(StaticField::field).toList());
//        classNode.fields.add(new FieldNode(ACC_STATIC + ACC_PUBLIC, "c", Type.getDescriptor(Object.class), null, null));
    }

    private void makeClinitMethod() {
        MethodNode clinitMethod = new MethodNode(ACC_STATIC, "<clinit>", "()V", null, null);
        InsnList instructions = clinitMethod.instructions;
        for (StaticField staticField : staticFields) {
            if (!staticField.valueExpression().hasVariableExpression()) {
                loadExpressionOntoStack(staticField.valueExpression(), instructions);
                instructions.add(new FieldInsnNode(PUTSTATIC, classNode.name, staticField.field().name, staticField.field().desc));
            }
        }

        instructions.add(new InsnNode(RETURN));
        clinitMethod.maxStack = 1; // Adjust max stack size based on your requirements
        clinitMethod.maxLocals = 0; // <clinit> method doesn't use local variables
        methodNodes.add(clinitMethod);
    }

    public void loadExpressionOntoStack(Expression expression, InsnList instructions) {
        switch (expression) {
            case IntegerExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/lang/Integer"));
                instructions.add(new InsnNode(DUP));
                instructions.add(new LdcInsnNode(expr.value()));
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Integer", "<init>", "(I)V", false));
            }
            case FloatExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/lang/Double"));
                instructions.add(new InsnNode(DUP));
                instructions.add(new LdcInsnNode(expr.value()));
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Double", "<init>", "(D)V", false));
            }
            case BooleanExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/lang/Boolean"));
                instructions.add(new InsnNode(DUP));
                if (!expr.value()) {
                    instructions.add(new LdcInsnNode(0));
                } else {
                    instructions.add(new LdcInsnNode(1));
                }
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/lang/Boolean", "<init>", "(Z)V", false));
            }
            case StringExpression expr -> {
                instructions.add(new LdcInsnNode(expr.value()));
            }
            case VariableExpression expr -> {
                switch (expr.symbol().metaType()) {
                    case GLOBAL -> {
                        instructions.add(new FieldInsnNode(GETSTATIC, classNode.name, expr.symbol().name(), Type.getDescriptor(Object.class)));
                    }
                    case LOCAL -> {
                        instructions.add(new VarInsnNode(ALOAD, expr.symbol().index()));
                    }
                }
            }
            case BinaryExpression expr -> {
                loadExpressionOntoStack(expr.left(), instructions);
                loadExpressionOntoStack(expr.right(), instructions);
                String name = expr.operation().name().toLowerCase();
                instructions.add(new InvokeDynamicInsnNode(
                        name,
                        MethodType.genericMethodType(2).toMethodDescriptorString(),
                        OPERATOR_HANDLE, (Integer) 2
                        )
                );
            }
            case FunctionExpression expr -> {
                String functionDescriptor = "(%s)%s".formatted(
                        String.join("", expr.arguments().stream().map(p -> "Ljava/lang/Object;").toList()),
                        "Ljava/lang/Object;");
                for (Expression arg : expr.arguments()) {
                    loadExpressionOntoStack(arg, instructions);
                }
                // invokestatic
                instructions.add(new MethodInsnNode(INVOKESTATIC,
                        classNode.name, expr.name(), functionDescriptor, false)
                );
            }
            case TableExpression expr -> {
                instructions.add(new TypeInsnNode(NEW, "java/util/HashMap"));
                instructions.add(new InsnNode(DUP));
                instructions.add(new MethodInsnNode(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false));
            }
            case TableAccessExpression expr -> {
                loadExpressionOntoStack(expr.table(), instructions);
                int depth = 0;
                for (Expression key : expr.key()) {
                    switch (key) {
                        case ConstantExpression constexpr -> loadExpressionOntoStack(
                                new StringExpression(constexpr.value().toString()),
                                instructions
                        );
                        case VariableExpression varexpr -> loadExpressionOntoStack(varexpr, instructions);
                        default -> throw new IllegalStateException("Unexpected value: " + expr.key());
                    }
                    instructions.add(new MethodInsnNode(INVOKEVIRTUAL, "java/util/HashMap", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", false));
                    depth++;
                    if (depth < expr.key().size()) {
                        // If there are more keys to process, we expect a nested table
                        instructions.add(new TypeInsnNode(CHECKCAST, "java/util/HashMap"));
                    }
                }
            }
            default -> throw new IllegalArgumentException("Unsupported expression type: " + expression);
        }
    }

    public byte[] build() {
        makeStaticFields();
        makeClinitMethod();

//        classNode.fields.addAll(fieldNodes);
        classNode.methods.addAll(methodNodes);

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        classNode.accept(cw);
        return cw.toByteArray();
    }

    public class MethodBuilder {
        private final MethodNode methodNode;
        private final InsnList instructions;

        private MethodBuilder(MethodNode methodNode) {
            this.methodNode = methodNode;
            this.instructions = methodNode.instructions;
        }

        public InsnList instructions() {
            return instructions;
        }

        public MethodBuilder addVarInsn(int opcode, int var) {
            instructions.add(new VarInsnNode(opcode, var));
            return this;
        }

        public MethodBuilder addMethodInsn(int opcode, String owner, String name, String desc, boolean isInterface) {
            instructions.add(new MethodInsnNode(opcode, owner, name, desc, isInterface));
            return this;
        }

        public MethodBuilder addInsn(int opcode) {
            instructions.add(new InsnNode(opcode));
            return this;
        }

        public MethodBuilder addIntInsn(int opcode, int operand) {
            instructions.add(new IntInsnNode(opcode, operand));
            return this;
        }

        public MethodBuilder addLdcInsn(Object cst) {
            instructions.add(new LdcInsnNode(cst));
            return this;
        }

        public MethodBuilder setMaxs(int maxStack, int maxLocals) {
            methodNode.maxStack = maxStack;
            methodNode.maxLocals = maxLocals;
            return this;
        }

        public ClassBuilder endMethod() {
            return ClassBuilder.this;
        }
    }
}
