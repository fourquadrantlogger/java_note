package jdk源码分析.设计模式.结构模式.模板设计方法;

/**
 * Created by timeloveboy on 17-12-14.
 */
public abstract class AbstractCalculator {
    public final int calculate(String exp, String opt) {
        int array[] = split(exp, opt);
        return calculate(array[0], array[1]);
    }

    //    /被子类重写的方法/
    abstract public int calculate(int num1, int num2);

    public int[] split(String exp, String opt) {
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}

