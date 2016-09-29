package jdk源码分析.多线程;

/**
 * Created by timeloveboy on 16/8/25.
 */
public class Runnable_Thread_Test {
    public static void main(String[] args) {
        MyThread mt1 = new MyThread();
        new Thread(mt1).start();
        new Thread(mt1).start();
        new Thread(mt1).start();

        //Runnable实现多线程可以达到资源共享目的
    }
}
