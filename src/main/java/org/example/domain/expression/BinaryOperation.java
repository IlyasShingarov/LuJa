package org.example.domain.expression;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public enum BinaryOperation implements Opcodes {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    MODULO("%"),
    DIVIDE_FLOOR("//"),
    EQUALS("=="),
    NOT_EQUALS("!="),
    LESS_THAN("<"),
    LESS_THAN_OR_EQUALS("<="),
    GREATER_THAN(">"),
    GREATER_THAN_OR_EQUALS(">=");

    private final String symbol;

    BinaryOperation(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getOpcode(Type type) {
        return switch (this) {
            case ADD -> type.getSort() == Type.LONG || type.getSort() == Type.DOUBLE ? DADD : IADD;
            case SUBTRACT -> type.getSort() == Type.LONG || type.getSort() == Type.DOUBLE ? DSUB : ISUB;
            case MULTIPLY -> type.getSort() == Type.LONG || type.getSort() == Type.DOUBLE ? DMUL : IMUL;
            case DIVIDE -> type.getSort() == Type.LONG || type.getSort() == Type.DOUBLE ? DDIV : IDIV;
            case MODULO -> type.getSort() == Type.LONG || type.getSort() == Type.DOUBLE ? DREM : IREM;
            case DIVIDE_FLOOR -> type.getSort() == Type.LONG ? LDIV : type.getSort() == Type.DOUBLE ? DDIV : IDIV;
            case EQUALS, NOT_EQUALS, LESS_THAN, LESS_THAN_OR_EQUALS, GREATER_THAN, GREATER_THAN_OR_EQUALS -> {
                if (type.getSort() == Type.LONG) {
                    yield LCMP;
                } else if (type.getSort() == Type.DOUBLE) {
                    yield DCMPG;
                } else if (type.getSort() == Type.FLOAT) {
                    yield FCMPG;
                } else if (type.getSort() == Type.INT) {
                    yield -1; // специальное значение для обозначения целочисленного сравнения
                } else {
                    throw new IllegalArgumentException("Unsupported type for comparison: " + type);
                }
            }
            default -> throw new IllegalArgumentException("Unknown operation: " + this);
        };
    }

    public static BinaryOperation fromSymbol(String symbol) {
        for (BinaryOperation op : values()) {
            if (op.symbol.equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Unknown operation: " + symbol);
    }
}
