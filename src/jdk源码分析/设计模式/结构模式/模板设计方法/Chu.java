package jdk源码分析.设计模式.结构模式.模板设计方法;

/**
 * Created by timeloveboy on 17-12-14.
 */
public class Chu extends AbstractCalculator {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}