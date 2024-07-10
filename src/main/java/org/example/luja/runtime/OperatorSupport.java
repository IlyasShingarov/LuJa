package org.example.luja.runtime;

import java.lang.invoke.*;

import static java.lang.invoke.MethodHandles.*;
import static java.lang.invoke.MethodType.methodType;

public class OperatorSupport {
    private OperatorSupport() {
        throw new UnsupportedOperationException("Don't instantiate invokedynamic bootstrap class");
    }

    static class MonomorphicInlineCache extends MutableCallSite {

        final MethodHandles.Lookup callerLookup;
        final String name;
        MethodHandle fallback;

        MonomorphicInlineCache(MethodHandles.Lookup callerLookup, String name, MethodType type) {
            super(type);
            this.callerLookup = callerLookup;
            this.name = name;
        }
    }

    private static final MethodHandle GUARD_2;
    private static final MethodHandle FALLBACK_2;

    static {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();

            GUARD_2 = lookup.findStatic(
                    OperatorSupport.class,
                    "guard_2",
                    methodType(boolean.class, Class.class, Class.class, Object.class, Object.class)
            );

            FALLBACK_2 = lookup.findStatic(
                    OperatorSupport.class,
                    "fallback_2",
                    methodType(Object.class, MonomorphicInlineCache.class, Object[].class)
            );
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new Error("Could not bootstrap the required method handles", e);
        }
    }

    public static boolean guard_2(Class<?> expected1, Class<?> expected2, Object arg1, Object arg2) {
        Class<?> t1 = (arg1 == null) ? Object.class : arg1.getClass();
        Class<?> t2 = (arg2 == null) ? Object.class : arg2.getClass();
        return (t1 == expected1) && (t2 == expected2);
    }

    public static Object fallback_2(MonomorphicInlineCache inlineCache, Object[] args) throws Throwable {

        Class<?> arg1Class = (args[0] == null) ? Object.class : args[0].getClass();
        Class<?> arg2Class = (args[1] == null) ? Object.class : args[1].getClass();
        MethodHandle target;

        try {
            target = inlineCache.callerLookup.findStatic(
                    OperatorSupport.class, inlineCache.name, methodType(Object.class, arg1Class, arg2Class));
        } catch (Throwable t1) {
            try {

                
                target = inlineCache.callerLookup.findStatic(
                        OperatorSupport.class, inlineCache.name + "_fallback", methodType(Object.class, Object.class, Object.class));
            } catch (Throwable t2) {
                return reject(args[0], args[1], inlineCache.name);
            }
        }

        target = target.asType(methodType(Object.class, Object.class, Object.class));
        if (arg1Class == String.class || arg2Class == String.class) {
            MethodHandle guard = insertArguments(GUARD_2, 0, arg1Class, arg2Class);
            target = guardWithTest(guard, target, inlineCache.fallback);
        } else {
            target = catchException(target, ClassCastException.class, dropArguments(inlineCache.fallback, 0, ClassCastException.class));
        }
        inlineCache.setTarget(target);

        return target.invokeWithArguments(args);
    }

    public static CallSite bootstrap(Lookup caller, String name, MethodType type, int arity) {
        MonomorphicInlineCache callSite = new MonomorphicInlineCache(caller, name, type);
        MethodHandle fallback;
        if (arity == 2) {
            fallback = FALLBACK_2;
        } else {
            fallback = null;
//            fallback = FALLBACK_1;
        }
        MethodHandle fallbackHandle = fallback
                .bindTo(callSite)
                .asCollector(Object[].class, type.parameterCount())
                .asType(type);
        callSite.fallback = fallbackHandle;
        callSite.setTarget(fallbackHandle);
        return callSite;
    }


    public static Object add(Integer a, Integer b) { return a + b; }
    public static Object add(Double a, Double b) { return a + b; }
    public static Object add(String a, String b) {
        try {
            return Integer.parseInt(a) + Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) + Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "+");
            }
        }
    }
    public static Object add(Integer a, Double b) { return a + b; }
    public static Object add(Double a, Integer b) { return a + b; }
    public static Object add(String a, Integer b) {
        try {
            return Integer.parseInt(a) + b;
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) + b;
            } catch (NumberFormatException e1) {
                return reject(a, b, "+");
            }
        }
    }
    public static Object add(Integer a, String b) {
        try {
            return a + Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return a + Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "+");
            }
        }
    }
    public static Object add(Double a, String b) {
        try {
            return a + Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return a + Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "+");
            }
        }
    }
    public static Object add(String a, Double b) {
        try {
            return Integer.parseInt(a) + b;
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) + b;
            } catch (NumberFormatException e1) {
                return reject(a, b, "+");
            }
        }
    }

    public static Object subtract(Integer a, Integer b) { return a - b; }
    public static Object subtract(Double a, Double b) { return a - b; }
    public static Object subtract(Integer a, Double b) { return a - b; }
    public static Object subtract(Double a, Integer b) { return a - b; }
    public static Object subtract(String a, String b) {
        try {
            return Integer.parseInt(a) - Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) - Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "-");
            }
        }
    }
    public static Object subtract(String a, Integer b) {
        try {
            return Integer.parseInt(a) - b;
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) - b;
            } catch (NumberFormatException e1) {
                return reject(a, b, "-");
            }
        }
    }
    public static Object subtract(Integer a, String b) {
        try {
            return a - Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return a - Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "-");
            }
        }
    }
    public static Object subtract(Double a, String b) {
        try {
            return a - Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return a - Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "-");
            }
        }
    }
    public static Object subtract(String a, Double b) {
        try {
            return Integer.parseInt(a) - b;
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) - b;
            } catch (NumberFormatException e1) {
                return reject(a, b, "-");
            }
        }
    }

    public static Object multiply(Integer a, Integer b) { return a * b; }
    public static Object multiply(Double a, Double b) { return a * b; }
    public static Object multiply(Integer a, Double b) { return a * b; }
    public static Object multiply(Double a, Integer b) { return a * b; }
    public static Object multiply(String a, String b) {
        try {
            return Integer.parseInt(a) * Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) * Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "*");
            }
        }
    }
    public static Object multiply(String a, Integer b) {
        try {
            return Integer.parseInt(a) * b;
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) * b;
            } catch (NumberFormatException e1) {
                return reject(a, b, "*");
            }
        }
    }
    public static Object multiply(Integer a, String b) {
        try {
            return a * Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return a * Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "*");
            }
        }
    }
    public static Object multiply(Double a, String b) {
        try {
            return a * Integer.parseInt(b);
        } catch (NumberFormatException e) {
            try {
                return a * Double.parseDouble(b);
            } catch (NumberFormatException e1) {
                return reject(a, b, "*");
            }
        }
    }
    public static Object multiply(String a, Double b) {
        try {
            return Integer.parseInt(a) * b;
        } catch (NumberFormatException e) {
            try {
                return Double.parseDouble(a) * b;
            } catch (NumberFormatException e1) {
                return reject(a, b, "*");
            }
        }
    }


    public static Object add_fallback(Object a, Object b) {
        return reject(a, b, "+");
    }


    private static Object reject(Object a, String symbol) throws IllegalArgumentException {
        throw new IllegalArgumentException(
                String.format("Unsupported operand type for %s: %s", symbol, a.getClass().getSimpleName())
        );
    }

    private static Object reject(Object a, Object b, String symbol) throws IllegalArgumentException {
        throw new IllegalArgumentException(
                String.format("Unsupported operand types for %s: %s and %s", symbol, a.getClass().getSimpleName(), b.getClass().getSimpleName())
        );
    }
}
