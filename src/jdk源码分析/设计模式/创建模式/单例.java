package jdk源码分析.设计模式.创建模式;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by timeloveboy on 16/8/18.
 */
public class 单例 {
    public List<Integer> values;
    private 单例(){
        values=new LinkedList<Integer>();
    }
    private static 单例 instance=new 单例();
    public static 单例 getInstance(){
        return instance;
    }

    public static void main(String[] args){
        单例 d1= 单例.getInstance();
        d1.values.add(1);
        单例 d2= 单例.getInstance();
        d2.values.add(2);

        if(d1==d2){
            System.out.println("same object");
        }
    }
}
