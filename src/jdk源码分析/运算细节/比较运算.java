package jdk源码分析.运算细节;

/**
 * Created by timeloveboy on 17-4-5.
 */
public class 比较运算 implements Comparable<Object> {
    int id;
    String name;

    public 比较运算(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /*
     * Getters and Setters
    */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (this == o) {
            return 0;
        } else if (o != null && o instanceof 比较运算) {
            比较运算 u = (比较运算) o;
            if (id <= u.id) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }

}
