package jdk源码分析.容器类;


import util.Log;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by timeloveboy on 16-10-24.
 */
public class 跳表 {
    public static void main(String[] args) {
        ConcurrentSkipListMap<Integer, Integer> skipListMap = new ConcurrentSkipListMap();
        for (int i = 0; i < 100; i++) {
            skipListMap.put(i, i);
        }
        Log.v(skipListMap.subMap(50, 100));

    }
}
