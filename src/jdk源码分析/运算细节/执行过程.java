package jdk源码分析.运算细节;

import java.io.DataInputStream;

/**
 * Created by timeloveboy on 17-4-5.
 */

class Base {
    public static void amethod() throws Exception {
    }
}

public class 执行过程 extends Base {
    执行过程() {
        amethod(99);
    }

    public static void main(String argv[]) {
        执行过程 e = new 执行过程();
    }

    public boolean amethod(int i) {
        try {
            DataInputStream din = new DataInputStream(System.in);
            System.out.println("Pausing");
            din.readChar();
            System.out.println("Continuing");
            this.amethod();
            return true;
        } catch (Exception ioe) {
        } finally {
            System.out.println("Doing finally");
        }
        return false;
    }
}