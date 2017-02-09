package jdk源码分析;

import util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by paidian on 17-2-8.
 */
public class 对象指针 {
    public  Map<Integer,Integer> add(){
        Map<Integer,Integer> likesmap =new HashMap<>();
        for (int i=0;i<100;i++) {
            Integer v = likesmap.getOrDefault(i, new Integer(0));
            v += i;
            likesmap.put(i,v);
        }
        return likesmap;
    }
    public static void main(String[] args){
        Log.v(new 对象指针().add());
    }
}
