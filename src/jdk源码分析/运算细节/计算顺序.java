package jdk源码分析.运算细节;

import util.Log;

/**
 * Created by timeloveboy on 17-4-5.
 */
public class 计算顺序 {
    public static void main(String[] args) {
        //从左向右计算
        Log.v(" " + 'a' + 1);
        // a1
        Log.v('a' + 1);
        //98
    }
}
