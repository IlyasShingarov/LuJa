package org.example.luja;

import java.lang.reflect.Method;

public class BytecodeClassLoader extends ClassLoader {
    public Class<?> defineClass(String name, byte[] bytecode) {
        return defineClass(name, bytecode, 0, bytecode.length);
    }

    public void executeMain(Class<?> clazz) throws Exception {
        Method main = clazz.getMethod("main", String[].class);
        main.invoke(null, (Object) new String[]{});
    }
}