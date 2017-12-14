package jdk源码分析.设计模式.行为模式.观察者模式;

/**
 * Created by timeloveboy on 17-12-14.
 */
public class ObjObserver implements Observer {
    @Override
    public void update() {
        System.out.println("observer1 has received!");
    }
}
