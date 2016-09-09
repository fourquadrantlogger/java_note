package java语言基础.多线程;

/**
 * Created by timeloveboy on 16/8/26.
 */
public class Doing_Thread extends Thread {
    public static String data = "";

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        data += "" + getThreadGroup().getName() + "\n";
    }
}
