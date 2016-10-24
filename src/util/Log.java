package util;

/**
 * Created by timeloveboy on 16/7/19.
 */
public class Log {
    public static void v(Object... os) {
        for (Object o : os) {
            System.out.print(o);
        }
        System.out.println();
    }
}
