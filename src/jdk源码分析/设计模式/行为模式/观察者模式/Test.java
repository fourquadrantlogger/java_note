package jdk源码分析.设计模式.行为模式.观察者模式;

/**
 * Created by timeloveboy on 17-12-14.
 */
public class Test {
    public static void main(String[] args) {
        Subject sub = new ObjSubject();
        sub.add(new ObjObserver());

        sub.operation();
    }
}