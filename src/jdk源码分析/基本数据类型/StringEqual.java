package jdk源码分析.基本数据类型;

/**
 * Created by timeloveboy on 17-4-8.
 */
public class StringEqual {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
//
//        按照==的语法来看， 首先s1、s2、s3是三个不同的对象，常理来说，输出都会是false。然而程序的运行结果确实true、false。第二个输出false可以理解，第一个输出true就又让人费解了。我们知道一些基本类型的变量和对象的引用变量都是在函数的栈内存中分配，而堆内存中则存放new 出来的对象和数组。然而除此之外还有一块区域叫做常量池。像我们通常想String s1 = "abc"; 这样申明的字符串对象，其值就是存储在常量池中。当我们创建String s1 = "abc"这样一个对象之后，"abc"就存储到了常量池（也可叫做字符串池）中，当我们创建引用String s2 = "abc" 的时候，Java底层会优先在常量池中查找是否存在"abc"，如果存在则让s2指向这个值，不会重新创建，如果常量池中没有则创建并添加的池中。这就是为什么答案是true 和false的原因。


    }
}
