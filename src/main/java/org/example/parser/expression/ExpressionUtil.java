//package org.example.parser.expression;
//
//import org.example.domain.expression.BinaryExpression;
//import org.example.domain.expression.BinaryOperation;
//import org.example.domain.expression.Expression;
//import org.example.domain.expression.VariableExpression;
//import org.example.domain.expression.constant.FloatExpression;
//import org.example.domain.expression.constant.IntegerExpression;
//import org.example.domain.expression.constant.StringExpression;
//
//public class ExpressionUtil {
//    public static Expression handleAddition(Expression left, Expression right) {
//        if (left instanceof BinaryExpression binaryLeft) {
//            return new BinaryExpression(
//                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
//                    right,
//                    BinaryOperation.ADD
//            );
//        }
//
//        if (left instanceof VariableExpression || right instanceof VariableExpression) {
//            return new BinaryExpression(left, right, BinaryOperation.ADD);
//        }
//
//        if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
//            return new IntegerExpression(((IntegerExpression) left).value() + ((IntegerExpression) right).value());
//        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
//            return new FloatExpression(((FloatExpression) left).value() + ((FloatExpression) right).value());
//        } else if (left instanceof StringExpression && right instanceof StringExpression) {
//            return new StringExpression(((StringExpression) left).value() + ((StringExpression) right).value());
//        }
//        return null;
//    }
//
//    public static Expression handleSubtraction(Expression left, Expression right) {
//        if (left instanceof BinaryExpression binaryLeft) {
//            return new BinaryExpression(
//                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
//                    right,
//                    BinaryOperation.SUBTRACT
//            );
//        }
//        if (left instanceof VariableExpression || right instanceof VariableExpression) {
//            return new BinaryExpression(left, right, BinaryOperation.SUBTRACT);
//        } else if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
//            return new IntegerExpression(((IntegerExpression) left).value() - ((IntegerExpression) right).value());
//        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
//            return new FloatExpression(((FloatExpression) left).value() - ((FloatExpression) right).value());
//        }
//        return null;
//    }
//
//    public static Expression handleDivision(Expression left, Expression right) {
//        if (left instanceof BinaryExpression binaryLeft) {
//            return new BinaryExpression(
//                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
//                    right,
//                    BinaryOperation.DIVIDE
//            );
//        }
//        if (left instanceof VariableExpression || right instanceof VariableExpression) {
//            return new BinaryExpression(left, right, BinaryOperation.DIVIDE);
//        } else if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
//            return new IntegerExpression(((IntegerExpression) left).value() / ((IntegerExpression) right).value());
//        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
//            return new FloatExpression(((FloatExpression) left).value() / ((FloatExpression) right).value());
//        }
//        return null;
//    }
//
//    public static Expression handleMultiplication(Expression left, Expression right) {
//        if (left instanceof BinaryExpression binaryLeft) {
//            return new BinaryExpression(
//                    new BinaryExpression(binaryLeft.left(), binaryLeft.right(), binaryLeft.operation()),
//                    right,
//                    BinaryOperation.MULTIPLY
//            );
//        }
//        if (left instanceof VariableExpression || right instanceof VariableExpression) {
//            return new BinaryExpression(left, right, BinaryOperation.MULTIPLY);
//        } else if (left instanceof IntegerExpression && right instanceof IntegerExpression) {
//            return new IntegerExpression(((IntegerExpression) left).value() * ((IntegerExpression) right).value());
//        } else if (left instanceof FloatExpression && right instanceof FloatExpression) {
//            return new FloatExpression(((FloatExpression) left).value() * ((FloatExpression) right).value());
//        }
//        return null;
//    }
//
//    public static Expression handleEquals(Expression left, Expression right) {
//        return new BinaryExpression(left, right, BinaryOperation.EQUALS);
//    }
//
//    public static Expression handleGreaterThan(Expression left, Expression right) {
//        return new BinaryExpression(left, right, BinaryOperation.GREATER_THAN);
//    }
//
//    public static Expression handleGreaterThanEquals(Expression left, Expression right) {
//        return new BinaryExpression(left, right, BinaryOperation.GREATER_THAN_OR_EQUALS);
//    }
//
//    public static Expression handleLessThan(Expression left, Expression right) {
//        return new BinaryExpression(left, right, BinaryOperation.LESS_THAN);
//    }
//
//    public static Expression handleLessThanEquals(Expression left, Expression right) {
//        return new BinaryExpression(left, right, BinaryOperation.LESS_THAN_OR_EQUALS);
//    }
//
//    public static Expression handleNotEquals(Expression left, Expression right) {
//        return new BinaryExpression(left, right, BinaryOperation.NOT_EQUALS);
//    }
//}
