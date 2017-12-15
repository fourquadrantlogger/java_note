package jdk源码分析.多线程.多线程实现生产者消费者;


/**
 * Created by timeloveboy on 17-12-15.
 */
public class Test {
    public static void main(String[] args) {
        Container container = new Container(100);//定义箱子最大容量，此处为5
        Producer producer = new Producer("李老师", container);//箱子中的苹果数要同步，所以将箱子对象引用作为形参传给生产者和消费者
        Customer consumer = new Customer("张同学", container);//

        new Thread(producer).start();//启动生产消费模式

        new Thread(consumer).start();
        new Thread(consumer).start();


    }
}
