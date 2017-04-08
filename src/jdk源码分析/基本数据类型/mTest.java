package jdk源码分析.基本数据类型;

/**
 * Created by timeloveboy on 17-4-8.
 */
public class mTest {

    public static void main(String[] args) {
        Integer a = 1000, b = 1000;
        Integer c = 127, d = 127;

        System.out.println(a == b);
        System.out.println(c == d);
//        当我们声明一个Integer c = 100;的时候。此时会进行自动装箱操作，简单点说，也就是把基本数据类型转换成Integer对象，而转换成Integer对象正是调用的valueOf方法，可以看到，Integer中把-128-127 缓存了下来。官方解释是小的数字使用的频率比较高，所以为了优化性能，把这之间的数缓存了下来。这就是为什么这道题的答案回事false和ture了。当声明的Integer对象的值在-128-127之间的时候，引用的是同一个对象，所以结果是true。
    }

}
