package jdk源码分析.设计模式.结构模式.模板设计方法;

/**
 * Created by timeloveboy on 17-12-14.
 */
public class StrategyTest {
    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}