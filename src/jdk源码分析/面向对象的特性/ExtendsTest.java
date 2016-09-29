package jdk源码分析.面向对象的特性;

/**
 * Created by timeloveboy on 16/8/19.
 */
public class ExtendsTest {
    public static void main(String[] args) {
        FatherClass fc = new FatherClass();
        ChildClass cc = new ChildClass();
    }
}
class FatherClass {
    public FatherClass() {
        System.out.println("FatherClass Create");
    }
}
class ChildClass extends FatherClass {
    public ChildClass() {
        System.out.println("ChildClass Create");
    }

}
//输出结果：
//        C:>java test.ChildClass
//     FatherClass Create
//     FatherClass Create
//     ChildClass Create