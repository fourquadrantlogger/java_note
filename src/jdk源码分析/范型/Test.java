package jdk源码分析.范型;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timeloveboy on 16/9/9.
 */
public class Test {
    private static Map<String, Class<? extends MClass>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Class<?> unknownClass = Class.forName("jdk源码分析.范型." + "NClass");
        if (NClass.class.isAssignableFrom(unknownClass)) {
            map.put("1", (Class<? extends MClass>) unknownClass);
        }
    }
}
