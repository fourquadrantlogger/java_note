package compute_exp;

/**
 * Created by timeloveboy on 16/5/30.
 */
public class ComputeExp {
    public String exp;//存储中缀表达式
    public char[] postexp;//存放后缀表达式
    public OpType op=new OpType();//运算符栈
    public ValueType value=new ValueType();//存放数据栈
}
