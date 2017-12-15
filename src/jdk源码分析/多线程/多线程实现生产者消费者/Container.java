package jdk源码分析.多线程.多线程实现生产者消费者;

/**
 * Created by timeloveboy on 17-12-15.
 */
public class Container {
    int max;
    int currentNum;

    public Container(int m) {
        max = m;
    }
}
