package jdk源码分析.运算细节;

/**
 * Created by timeloveboy on 17-4-5.
 */
public class 对象引用 {
    public static void main(String[] args) {
        String str1 = new String("abc");
        String str2 = new String("abc");
        String str3 = str1;
        if (str1.equals(str2)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        if (str1 == str3) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }

        //true
        //true
    }
}
