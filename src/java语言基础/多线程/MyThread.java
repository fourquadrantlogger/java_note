package java语言基础.多线程;

/**
 * Created by timeloveboy on 16/8/26.
 */
class MyThread implements Runnable {
    private int loop = 0;

    public void run() {
        while (loop < 100) {
            loop++;
            System.out.println(getClass().getName() + "count:" + loop);
        }
    }
};