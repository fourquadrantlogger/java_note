package jdk源码分析.设计模式.行为模式.观察者模式;

/**
 * Created by timeloveboy on 17-12-14.
 */
public class ObjSubject extends AbstractSubject {
    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers();
    }
}
