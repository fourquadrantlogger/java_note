// 为工厂模式就相当于创建对象的 new

package jdk源码分析.设计模式.创建模式;


/**
 * Created by timeloveboy on 16/9/30.
 */
public class 工厂 {
    public static Sample creator() {
        return new Sample();
    }
}
