package org.example.luja.runtime;

import java.io.IOException;
import java.util.Scanner;

public class BuiltinUtil {

    public static Object read(String desc) throws IOException {
        Scanner sc = new Scanner(System.in);
        switch (desc) {
            case "n" -> {
                if (sc.hasNextInt()) {
                    return sc.nextInt();
                } else {
                    return sc.nextFloat();
                }
            }
            case "l" -> {
                return sc.nextLine();
            }
            default -> {
                return sc.next();
            }
        }
    }
}
