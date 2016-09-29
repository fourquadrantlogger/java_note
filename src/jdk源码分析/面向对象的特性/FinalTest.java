package jdk源码分析.面向对象的特性;

import java.util.Vector;

/**
 * Created by timeloveboy on 16/8/19.
 */
public class FinalTest {
    public static void main(String[] args){
        FinalTest f=new FinalTest();
        Vector<Integer> integers=new Vector<Integer>();
        f.addFinalOne(integers);
        f.addOne(integers);
    }
    public Vector<Integer> addFinalOne(final Vector<Integer> integers) {
        Vector<Integer>  y=integers;
        y.add(1);
        return y;
    }
    public Vector<Integer> addOne(Vector<Integer> integers ) {
        Vector<Integer> y=integers;
        y.add(1);
        return y;
    }
}
