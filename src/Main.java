public class Main {

    public static void main(String[] args)  throws MathExpr.ExprException{
        MathExprTest();
    }

    public static void MathExprTest()  throws MathExpr.ExprException {
        String expr1 = "12+4*25";
        echo(expr1 + "=" + MathExpr.compute(expr1));
        //variable expression compute
        MathExpr expr2 = new MathExpr("sin(pi/2)+4*x");
        expr2.setVar("x", 25.0);
        echo(expr2.getExprStr() + "=" + expr2.compute());
        //user function expression compute
        MathExpr expr3 = new MathExpr("sum(4,3,2,1)+4*x");
        expr3.setVar("x", 25.0);
        expr3.setFunc("sum", new MathExpr.Func() {

            @Override
            public double compute(double[] params) {
                double sum = 0;
                for (int i = 0; i < params.length; i++) {
                    sum += params[i];
                }
                return sum;
            }
        });
        echo(expr3.getExprStr() + "=" + expr3.compute());
    }
    public static String echo(String str) {
        System.out.println(str);
        return str;
    }
}
