package org.example.luja;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LuaBytecodeExecutor {
    public String execute(byte[] bytecode) throws Exception {
        BytecodeClassLoader loader = new BytecodeClassLoader();
        // TODO Remove generated class name
        Class<?> clazz = loader.defineClass("GeneratedClass", bytecode);

        // Redirect System.out to capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        // Execute the main method
        loader.executeMain(clazz);

        // Restore System.out
        System.setOut(originalOut);

        return outputStream.toString();
    }
}
