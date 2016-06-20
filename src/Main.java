import WordFilter.WordFilter;

public class Main {

    public static void main(String[] args)  throws MathExpr.ExprException{
//        MathExprTest();
//        GeneralizedTable();
//        BTree();
        WordFilter wordFilter=new WordFilter();
        wordFilter.Tester();
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
    public static void GeneralizedTable() {

        String p = "((),a,b,(a,b,c),(a,(a,b),c))";
        String p2 = "((()),2)";

        String big = "{{a,b},{{a,g},{h},{a,n,f,{a,b,c}}},c}";
        String middle = "[[p],[[d,f,[g]]],[h],[2]]";
        GeneralizedTable gTab = new GeneralizedTable(middle);

        System.out.println("length: " + gTab.length());
        System.out.println("depth: " + gTab.depth());
    }


    public static void BTree(){
        /**
         * @param args
         */
        Node a = new Node('A');
        Node b = new Node('B', null, a);
        Node c = new Node('C');
        Node d = new Node('D', b, c);
        Node e = new Node('E');
        Node f = new Node('F', e, null);
        Node g = new Node('G', null, f);
        Node h = new Node('H', d, g);

            BinaryTree tree = new BinaryTree(h);
            System.out.print(" Pre-Order:");
            BinaryTree.preorder(tree.getRoot());
            System.out.println();
            System.out.print(" In-Order:");
        BinaryTree.inorder(tree.getRoot());
            System.out.println();
            System.out.print("Post-Order:");
        BinaryTree.postorder(tree.getRoot());
            System.out.println();
            System.out.print(" 非递归实现前序遍历-Order:");
        BinaryTree.iterativePreorder(tree.getRoot());
            System.out.println();
            System.out.print("非递归实现前序遍历-Order2:");
        BinaryTree.iterativePreorder2(tree.getRoot());
            System.out.println();
            System.out.print(" In-Order:");
        BinaryTree.iterativeInorder(tree.getRoot());
            System.out.println();
            System.out.print(" In-Order2:");
        BinaryTree.iterativeInorder2(tree.getRoot());
            System.out.println();
            System.out.print(" Post-Order:");
        BinaryTree.iterativePostorder(tree.getRoot());
            System.out.println();
            System.out.print("Post-Order2:");
        BinaryTree.iterativePostorder2(tree.getRoot());
            System.out.println();
            System.out.print("Post-Order3:");
        BinaryTree.iterativePostorder3(tree.getRoot());
            System.out.println();
            System.out.print("Post-Order4:");
        BinaryTree.iterativePostorder4(tree.getRoot());
            System.out.println();

    }
    public static String echo(String str) {
        System.out.println(str);
        return str;
    }
}
