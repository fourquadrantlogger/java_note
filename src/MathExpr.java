import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * 表达式计算类<br /> 
 * 1.支持符号："+"（加号）"-"（减号）"*"（乘号）"/"（除号）"^"（乘方）"("（左括号）")"（右括号）"."（小数点）。<br />
 * 2.支持数学函数：abs(x) , sin(x) , cos(x) , tan(x) , asin(x) , acos(x) , atan(x) , exp(x) , ln(x) , log(x) , sqrt(x) ,
 * power(x,y) , min(x,y) , max(x,y) , signum(x) , round(x) , random() , toDegrees(x) , toRadians(x)。<br />
 * 3.支持常量：E , PI。<br />
 * 4.允许使用字母开头的变量，不区分大小写。<br />
 * 5.表达式字符串支持使用全角字符。
 *
 * @author ming
 * @version 1.0.0
 */
public class MathExpr {

    /**
     * 表达式计算类错误
     */
    public static class ExprException extends Exception {

        /**
         * 错误编码类
         */
        public static enum NUM {
            
            EXPR_MISSING, UNRECOGNIZABLE_CHAR, UNEXCEPT_NEGATIVE_SYM, MISSING_OPER_SYM, MISSING_OPER_NUM,
            BAD_FUNC_NAME, BAD_VAR_NAME, BAD_NUMBER, BAD_OPER_SYM,
            NO_MATCH_BRACKET, MISSING_FUNCTION, NO_RESULT, MORE_RESULT, UNKNOW_VAR, UNKNOW_FUNC,
            UNSET_VAR, UNSET_FUNC, COMPUTE_PARAM_NOT_MATCH, UNRECOGNIZABLE_OPER_SYM, COMPUTE_EXCEPTION,
            UNKNOW_ERROR;

            /**
             * 获取默认错误消息
             *
             * @return 错误消息
             */
            public String getDefaultMsg() {
                switch (this) {
                    case EXPR_MISSING:
                        return "expression missing";
                    case UNRECOGNIZABLE_CHAR:
                        return "unrecognizable character";
                    case UNEXCEPT_NEGATIVE_SYM:
                        return "unexcept negative symbol";
                    case MISSING_OPER_SYM:
                        return "missing operating symbol";
                    case MISSING_OPER_NUM:
                        return "missing operating number";
                    case BAD_FUNC_NAME:
                        return "bad function name";
                    case BAD_VAR_NAME:
                        return "bad variable name";
                    case BAD_NUMBER:
                        return "bad number";
                    case BAD_OPER_SYM:
                        return "bad operating symbol";
                    case NO_MATCH_BRACKET:
                        return "right bracket find no match left bracket";
                    case MISSING_FUNCTION:
                        return "missing function";
                    case NO_RESULT:
                        return "no result";
                    case MORE_RESULT:
                        return "more result";
                    case UNSET_VAR:
                        return "unset variable";
                    case UNSET_FUNC:
                        return "unset function";
                    case UNKNOW_VAR:
                        return "unknow variable";
                    case UNKNOW_FUNC:
                        return "unknow function";
                    case COMPUTE_PARAM_NOT_MATCH:
                        return "compute parameter not match";
                    case UNRECOGNIZABLE_OPER_SYM:
                        return "unrecognizable operating symbol";
                    case COMPUTE_EXCEPTION:
                        return "compute expcetion";
                    default:
                        return "unknow error";
                }
            }
        }
        /**
         * 错误编码
         */
        public NUM num;
        /**
         * 错误消息
         */
        public String msg;

        /**
         * 构造函数
         *
         * @param num 错误编码
         */
        public ExprException(NUM num) {
            this.num = num;
            this.msg = getDefaultMsg(num);
        }

        /**
         * 构造函数
         *
         * @param num 错误编码
         * @param msg 错误消息
         */
        public ExprException(NUM num, String msg) {
            this.num = num;
            this.msg = msg;
        }

        /**
         * 获取默认错误消息
         *
         * @param num 错误编码
         * @return 错误消息
         */
        public static String getDefaultMsg(NUM num) {
            if (num == null) {
                return NUM.UNKNOW_ERROR.getDefaultMsg();
            } else {
                return num.getDefaultMsg();
            }
        }

        /**
         * 获取错误消息
         *
         * @return 错误消息
         */
        @Override
        public String toString() {
            return this.msg;
        }
    }

    /**
     * 函数接口类，用于注册自定义函数
     */
    public static interface Func {

        /**
         * 计算函数
         *
         * @param params 参数数组
         * @return 结算结果
         */
        public double compute(double[] params);
    }

    /**
     * 字符串及字符支持类
     */
    private static class Str {

        /**
         * 全角小写字母表
         */
        private static final char[] EnLowerSBC = {'ａ', 'ｂ', 'ｃ', 'ｄ', 'ｅ', 'ｆ',
            'ｇ', 'ｈ', 'ｉ', 'ｊ', 'ｋ', 'ｌ',
            'ｍ', 'ｎ', 'ｏ', 'ｐ', 'ｑ', 'ｒ',
            'ｓ', 'ｔ', 'ｕ', 'ｖ', 'ｗ', 'ｘ',
            'ｙ', 'ｚ'};
        /**
         * 全角大写字母表
         */
        private static final char[] EnUpperSBC = {'Ａ', 'Ｂ', 'Ｃ', 'Ｄ', 'Ｅ', 'Ｆ',
            'Ｇ', 'Ｈ', 'Ｉ', 'Ｊ', 'Ｋ', 'Ｌ',
            'Ｍ', 'Ｎ', 'Ｏ', 'Ｐ', 'Ｑ', 'Ｒ',
            'Ｓ', 'Ｔ', 'Ｕ', 'Ｖ', 'Ｗ', 'Ｘ',
            'Ｙ', 'Ｚ'};
        /**
         * 全角数字表
         */
        private static final char[] NumSBC = {'０', '１', '２', '３', '４',
            '５', '６', '７', '８', '９'};
        /**
         * 全角符号表
         */
        private static final char[] SymSBC = {
            '＋', '－', '＊', '／', '（', '）', '．', '，', '×', '÷'
        };
        /**
         * 半角符号表
         */
        private static final char[] SymDBC = {
            '+', '-', '*', '/', '(', ')', '.', ',', '*', '/'
        };
        /**
         * 合法字符字符串
         */
        private static final String LEGAL_CHARS = "+-*/^().,";
        /**
         * 单词链分割字符
         */
        private static final String SYM_CUT = "+-*/^(),";
        /**
         * 二目运算符
         */
        private static final String SYM_OPER2 = "+-*/^";
        /**
         * 系统函数
         */
        private static final String[] SYS_FUNC = {
            "abs(", "sin(", "cos(", "tan(", "asin(", "acos(", "atan(", "exp(", "ln(", "log(", "sqrt(",
            "pow(", "min(", "max(", "signum(", "round(", "random(", "degrees(", "radians("
        };

        /**
         * 修正表达式字符串，将全角转换为半角，可选删除空格，null将被转换为空字符串
         *
         * @param str 表达式字符串
         * @param delSpace 是否删除空格
         * @return 修正后的字符串
         */
        public static String fixExprStr(String str, boolean delSpace) throws ExprException {
            if (str == null || str.isEmpty()) {
                throw new ExprException(ExprException.NUM.EXPR_MISSING);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                //skip space
                if (delSpace && (ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n')) {
                    continue;
                }
                //fix SBC character
                if (ch >= EnLowerSBC[0] && ch <= EnLowerSBC[EnLowerSBC.length - 1]) {
                    ch = (char) (ch - EnLowerSBC[0] + 'a');
                } else if (ch >= EnUpperSBC[0] && ch <= EnUpperSBC[EnUpperSBC.length - 1]) {
                    ch = (char) (ch - EnUpperSBC[0] + 'A');
                } else if (ch >= NumSBC[0] && ch <= NumSBC[NumSBC.length - 1]) {
                    ch = (char) (ch - NumSBC[0] + '0');
                } else {
                    for (int j = 0; j < SymSBC.length && j < SymDBC.length; j++) {
                        if (ch == SymSBC[j]) {
                            ch = SymDBC[j];
                            break;
                        }
                    }
                }
                //test unrecognizable character
                boolean unrecognizable = true;
                if (ch >= 'a' && ch <= 'z') {
                    unrecognizable = false;
                } else if (ch >= 'A' && ch <= 'Z') {
                    unrecognizable = false;
                } else if (ch >= '0' && ch <= '9') {
                    unrecognizable = false;
                } else if (LEGAL_CHARS.indexOf(ch) >= 0) {
                    unrecognizable = false;
                }
                if (unrecognizable) {
                    throw new ExprException(ExprException.NUM.UNRECOGNIZABLE_CHAR,
                            ExprException.NUM.UNRECOGNIZABLE_CHAR.getDefaultMsg() + " '" + ch + "'");
                }
                //append character
                sb.append(ch);
            }
            return sb.toString();
        }

        /**
         * 判断一个字符是否是分割字符
         *
         * @param ch 字符
         * @return 是否是分割字符
         */
        public static boolean isSymCut(char ch) {
            return SYM_CUT.indexOf(ch) >= 0;
        }

        /**
         * 判断一个字符是否是二目操作符
         *
         * @param ch 字符
         * @return 是否是二目操作符
         */
        public static boolean isSymOper2(char ch) {
            return SYM_OPER2.indexOf(ch) >= 0;
        }

        /**
         * 判断字符串是否是系统函数
         *
         * @param func 函数字符串（包含结尾'('）
         * @return 是否是系统函数
         */
        public static boolean isSysFunc(String func) {
            for (String sysFunc : SYS_FUNC) {
                if (sysFunc.equals(func)) {
                    return true;
                }
            }
            return false;
        }
        
        public static boolean isLetter(char ch) {
            return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
        }
        
        public static boolean isNum(char ch) {
            return (ch >= '0' && ch <= '9');
        }

        /**
         * 测试函数名称
         *
         * @param name 函数名称
         * @return 函数名称
         */
        public static String testFuncName(String name) throws ExprException {
            if (name == null || name.length() == 0) {
                throw new ExprException(ExprException.NUM.BAD_FUNC_NAME,
                        ExprException.NUM.BAD_FUNC_NAME.getDefaultMsg() + ":" + name);
            }
            if (!isLetter(name.charAt(0))) {
                throw new ExprException(ExprException.NUM.BAD_FUNC_NAME,
                        ExprException.NUM.BAD_FUNC_NAME.getDefaultMsg() + ":" + name
                        + ",should start with a letter");
            }
            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);
                if (!isLetter(ch) && !isNum(ch)) {
                    throw new ExprException(ExprException.NUM.BAD_FUNC_NAME,
                            ExprException.NUM.BAD_FUNC_NAME.getDefaultMsg() + ":" + name
                            + ",should use only english letter or number letter");
                }
            }
            return name;
        }

        /**
         * 测试变量名称
         *
         * @param name 变量名称
         * @return 变量名称
         */
        public static String testVarName(String name) throws ExprException {
            if (name == null || name.length() == 0) {
                throw new ExprException(ExprException.NUM.BAD_VAR_NAME,
                        ExprException.NUM.BAD_VAR_NAME.getDefaultMsg() + ":" + name);
            }
            if (!isLetter(name.charAt(0))) {
                throw new ExprException(ExprException.NUM.BAD_VAR_NAME,
                        ExprException.NUM.BAD_VAR_NAME.getDefaultMsg() + ":" + name
                        + ",should start with a letter");
            }
            for (int i = 0; i < name.length(); i++) {
                char ch = name.charAt(i);
                if (!isLetter(ch) && !isNum(ch)) {
                    throw new ExprException(ExprException.NUM.BAD_VAR_NAME,
                            ExprException.NUM.BAD_VAR_NAME.getDefaultMsg() + ":" + name
                            + ",should use only english letter or number letter");
                }
            }
            return name;
        }
    }

    /**
     * 计算节点<br /> 在计算树构造完成后，如果params为null，则为叶子值节点（常量或变量），非操作符和函数
     */
    private static class ComputeNode {

        /**
         * 节点字符串
         */
        public String str = null;
        /**
         * 节点值（节点字符串为null时有效）
         */
        public double val = Double.NaN;
        /**
         * 需要操作数数目
         */
        public int operCnt = 0;
        /**
         * 是否是负数
         */
        public boolean negative = false;
        /**
         * 计算参数
         */
        public ArrayList<ComputeNode> params = null;
    }
    /**
     * 表达式字符串
     */
    private String exprStr;
    /**
     * 忽略大小写
     */
    private boolean ignoreCase;
    /**
     * 变量池
     */
    private HashMap<String, Double> varMap;
    /**
     * 自定义函数池
     */
    private HashMap<String, Func> funcMap;
    /**
     * 表达式计算树
     */
    private ComputeNode computeTree;

    /**
     * 构造表达式计算类
     *
     * @param exprStr 表达式字符串
     */
    public MathExpr(String exprStr) throws ExprException {
        this.exprStr = exprStr;//record old expression string
        this.ignoreCase = true;//set ignore case
        this.varMap = new HashMap<String, Double>();//create empty variable map
        this.funcMap = new HashMap<String, Func>();//create empty function map
        this.computeTree = createTree(exprStr);//create compute tree
        this.computeTree = optimizeTree(computeTree);//optimize compute tree
    }

    /**
     * 构造表达式计算类
     *
     * @param exprStr 表达式字符串
     * @param ignoreCase 是否忽略大小写
     */
    public MathExpr(String exprStr, boolean ignoreCase) throws ExprException {
        this.exprStr = exprStr;//record old expression string
        this.ignoreCase = ignoreCase;//set ignore case
        this.varMap = new HashMap<String, Double>();//create empty variable map
        this.funcMap = new HashMap<String, Func>();//create empty function map
        this.computeTree = createTree(exprStr);//create compute tree
        this.computeTree = optimizeTree(computeTree);//optimize compute tree
    }

    /**
     * 获取表达式字符串
     *
     * @return 表达式字符串
     */
    public String getExprStr() {
        return exprStr;
    }

    /**
     * 获取变量名称集合
     *
     * @return 变量名称集合
     */
    public Set<String> getVars() {
        return varMap.keySet();
    }

    /**
     * 设置变量值
     *
     * @param name 变量名称
     * @param val 变量值
     * @return 变量旧值
     */
    public Double setVar(String name, Double val) throws ExprException {
        if (ignoreCase) {
            name = name.toLowerCase();
        }
        if (!varMap.containsKey(name)) {
            throw new ExprException(ExprException.NUM.UNKNOW_VAR,
                    ExprException.NUM.UNKNOW_VAR.getDefaultMsg() + ":" + name);
        }
        return varMap.put(name, val);
    }

    /**
     * 获取变量值
     *
     * @param name 变量名称
     * @return 变量值
     */
    public Double getVar(String name) {
        if (ignoreCase) {
            name = name.toLowerCase();
        }
        return varMap.get(name);
    }

    /**
     * 获取函数名称集合
     *
     * @return 函数名称集合
     */
    public Set<String> getFuncs() {
        return funcMap.keySet();
    }

    /**
     * 修正函数名称
     *
     * @param name 函数名称
     * @return 修正后的函数名称，确保结尾有左括号
     */
    private String fixFuncName(String name) {
        return name.endsWith("(") ? name : name + "(";
    }

    /**
     * 设置函数接口
     *
     * @param name 函数名称
     * @param func 函数接口
     * @return 函数旧接口
     */
    public Func setFunc(String name, Func func) throws ExprException {
        if (ignoreCase) {
            name = name.toLowerCase();
        }
        name = fixFuncName(name);
        if (!funcMap.containsKey(name)) {
            throw new ExprException(ExprException.NUM.UNKNOW_FUNC,
                    ExprException.NUM.UNKNOW_FUNC.getDefaultMsg() + ":" + name);
        }
        return funcMap.put(name, func);
    }

    /**
     * 设置函数接口
     *
     * @param name 函数名称
     * @return 函数接口
     */
    public Func getFunc(String name) {
        if (ignoreCase) {
            name = name.toLowerCase();
        }
        return funcMap.get(fixFuncName(name));
    }

    /**
     * 计算表达式的值
     *
     * @return 表达式的值
     */
    public double compute() throws ExprException {
        return compute(computeTree);
    }

    /**
     * 计算表达式的值
     *
     * @param root 根节点
     * @return 表达式的值
     */
    private double compute(ComputeNode root) throws ExprException {
        int sig = root.negative ? -1 : 1;
        if (root.params != null) {
            double[] params = new double[root.params.size()];
            for (int i = 0; i < params.length; i++) {
                params[i] = compute(root.params.get(i));
            }
            if (root.str.equals("+")) {
                checkParamLength(root.str, params, 2);
                return sig * (params[0] + params[1]);
            } else if (root.str.equals("-")) {
                checkParamLength(root.str, params, 2);
                return sig * (params[0] - params[1]);
            } else if (root.str.equals("*")) {
                checkParamLength(root.str, params, 2);
                return sig * (params[0] * params[1]);
            } else if (root.str.equals("/")) {
                checkParamLength(root.str, params, 2);
                try {
                    return sig * (params[0] / params[1]);
                } catch (Exception ex) {
                    throw new ExprException(ExprException.NUM.COMPUTE_EXCEPTION,
                            ExprException.NUM.COMPUTE_EXCEPTION.getDefaultMsg() + ":" + ex.getMessage());
                }
            } else if (root.str.equals("^")) {
                checkParamLength(root.str, params, 2);
                return sig * Math.pow(params[0], params[1]);
            } else if (root.str.equals("abs(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.abs(params[0]);
            } else if (root.str.equals("sin(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.sin(params[0]);
            } else if (root.str.equals("cos(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.cos(params[0]);
            } else if (root.str.equals("tan(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.tan(params[0]);
            } else if (root.str.equals("asin(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.asin(params[0]);
            } else if (root.str.equals("acos(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.acos(params[0]);
            } else if (root.str.equals("atan(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.atan(params[0]);
            } else if (root.str.equals("exp(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.exp(params[0]);
            } else if (root.str.equals("ln(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.log(params[0]);
            } else if (root.str.equals("log(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.log10(params[0]);
            } else if (root.str.equals("sqrt(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.sqrt(params[0]);
            } else if (root.str.equals("pow(")) {
                checkParamLength(root.str, params, 2);
                return sig * Math.pow(params[0], params[1]);
            } else if (root.str.equals("min(")) {
                checkParamLength(root.str, params, 2);
                return sig * Math.min(params[0], params[1]);
            } else if (root.str.equals("max(")) {
                checkParamLength(root.str, params, 2);
                return sig * Math.max(params[0], params[1]);
            } else if (root.str.equals("signum(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.signum(params[0]);
            } else if (root.str.equals("round(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.round(params[0]);
            } else if (root.str.equals("random(")) {
                checkParamLength(root.str, params, 0);
                return sig * Math.random();
            } else if (root.str.equals("degrees(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.toDegrees(params[0]);
            } else if (root.str.equals("radians(")) {
                checkParamLength(root.str, params, 1);
                return sig * Math.toRadians(params[0]);
            } else {
                if (root.str.endsWith("(")) {
                    Func func = funcMap.get(root.str);
                    if (func == null) {
                        throw new ExprException(ExprException.NUM.UNSET_FUNC,
                                ExprException.NUM.UNSET_FUNC.getDefaultMsg() + ":" + root.str);
                    }
                    return sig * func.compute(params);
                } else {
                    throw new ExprException(ExprException.NUM.UNRECOGNIZABLE_OPER_SYM,
                            ExprException.NUM.UNRECOGNIZABLE_OPER_SYM.getDefaultMsg() + ":" + root.str);
                }
            }
        } else if (root.str != null) {
            Double val = varMap.get(root.str);
            if (val == null) {
                throw new ExprException(ExprException.NUM.UNSET_VAR,
                        ExprException.NUM.UNSET_VAR.getDefaultMsg() + ":" + root.str);
            }
            return sig * val.doubleValue();
        } else {
            return sig * root.val;
        }
    }
    
    private void checkParamLength(String name, double[] params, int need) throws ExprException {
        if (params.length != need) {
            throw new ExprException(ExprException.NUM.COMPUTE_PARAM_NOT_MATCH,
                    ExprException.NUM.COMPUTE_PARAM_NOT_MATCH.getDefaultMsg() + ":"
                    + name + " need " + need + " parameter,but found " + params.length);
        }
    }

    /**
     * 构造表达式计算树
     *
     * @param exprStr 表达式字符串
     * @return 表达式计算树根节点
     */
    private ComputeNode createTree(String exprStr) throws ExprException {
        ArrayList<ComputeNode> numStack = new ArrayList<ComputeNode>();
        ArrayList<ComputeNode> symStack = new ArrayList<ComputeNode>();
        StringBuilder buff = new StringBuilder();
        exprStr = Str.fixExprStr(exprStr, true);
        if (ignoreCase) {
            exprStr = exprStr.toLowerCase();
        }
        boolean newStart = true;
        boolean negative = false;
        for (int i = 0; i < exprStr.length(); i++) {
            char ch = exprStr.charAt(i);
            if (newStart && ch == '-') {
                if (negative) {
                    throw new ExprException(ExprException.NUM.UNEXCEPT_NEGATIVE_SYM);
                }
                negative = true;
                continue;
            }
            newStart = false;
            if (Str.isSymCut(ch)) {
                if (buff.length() != 0) {
                    if (ch == '(') {
                        ComputeNode node = new ComputeNode();
                        node.str = Str.testFuncName(buff.toString()) + "(";
                        //register user function
                        if (!Str.isSysFunc(node.str) && !funcMap.containsKey(node.str)) {
                            funcMap.put(node.str, null);
                        }
                        char nextCh = exprStr.charAt(i + 1);
                        node.operCnt = nextCh == ')' ? 0 : 1;
                        node.negative = negative;
                        pushSym(numStack, symStack, node);
                        buff = new StringBuilder();
                        negative = false;
                        newStart = true;
                        continue;
                    } else {
                        pushNum(numStack, buff.toString(), negative);
                        buff = new StringBuilder();
                        negative = false;
                    }
                }
                if (Str.isSymOper2(ch)) {
                    ComputeNode symNode = new ComputeNode();
                    symNode.str = Character.toString(ch);
                    symNode.operCnt = 2;
                    pushSym(numStack, symStack, symNode);
                } else if (ch == '(') {
                    ComputeNode symNode = new ComputeNode();
                    symNode.str = Character.toString(ch);
                    pushSym(numStack, symStack, symNode);
                    newStart = true;
                } else if (ch == ')') {
                    //push until find '('
                    boolean matchFunction = false;
                    while (!symStack.isEmpty()) {
                        ComputeNode symNode = symStack.get(symStack.size() - 1);
                        if (symNode.str.equals("(")) {
                            matchFunction = false;
                            break;
                        } else if (symNode.str.endsWith("(")) {
                            matchFunction = true;
                            break;
                        }
                        computeStack(numStack, symStack);
                    }
                    if (symStack.isEmpty()) {
                        throw new ExprException(ExprException.NUM.NO_MATCH_BRACKET,
                                ExprException.NUM.NO_MATCH_BRACKET.getDefaultMsg());
                    }
                    if (matchFunction) {
                        computeStack(numStack, symStack);
                    } else {
                        symStack.remove(symStack.size() - 1);
                    }
                } else if (ch == ',') {
                    //push until find function
                    while (!symStack.isEmpty()) {
                        ComputeNode symNode = symStack.get(symStack.size() - 1);
                        if (symNode.str.endsWith("(")) {
                            break;
                        }
                        computeStack(numStack, symStack);
                    }
                    if (symStack.isEmpty()) {
                        throw new ExprException(ExprException.NUM.MISSING_FUNCTION,
                                ExprException.NUM.MISSING_FUNCTION.getDefaultMsg());
                    }
                    symStack.get(symStack.size() - 1).operCnt++;
                    newStart = true;
                } else {
                    throw new ExprException(ExprException.NUM.BAD_OPER_SYM,
                            ExprException.NUM.BAD_OPER_SYM.getDefaultMsg() + ":" + ch);
                }
            } else {
                buff.append(ch);
            }
        }
        if (buff.length() > 0) {
            pushNum(numStack, buff.toString(), negative);
        }
        while (!symStack.isEmpty()) {
            computeStack(numStack, symStack);
        }
        if (numStack.isEmpty()) {
            throw new ExprException(ExprException.NUM.NO_RESULT);
        } else if (numStack.size() > 1) {
            throw new ExprException(ExprException.NUM.MORE_RESULT);
        }
        return numStack.get(0);
    }

    /**
     * 优化树
     *
     * @param root 优化根节点
     * @return 优化后根节点
     */
    private ComputeNode optimizeTree(ComputeNode root) throws ExprException {
        //skip left node,can not optimize
        if (root.params == null) {
            return root;
        }
        //optimize child nodes
        for (int i = 0; i < root.params.size(); i++) {
            root.params.set(i, optimizeTree(root.params.get(i)));
        }
        for (ComputeNode child : root.params) {
            //not constant node,can not optimize
            if (child.params != null || child.str != null) {
                return root;
            }
        }
        //not user function
        if (root.str.endsWith("(") && !Str.isSysFunc(root.str)) {
            return root;
        }
        //compute result for constant node
        ComputeNode newNode = new ComputeNode();
        newNode.val = compute(root);
        return newNode;
    }

    /**
     * 将数字或变量压入数字栈
     *
     * @param numStack 数字栈
     * @param numStr 数字或变量字符串
     * @param negative 是否为负
     */
    private void pushNum(ArrayList<ComputeNode> numStack, String numStr, boolean negative) throws ExprException {
        ComputeNode numNode = new ComputeNode();
        numNode.str = numStr;
        numNode.negative = negative;
        if (Str.isNum(numStr.charAt(0))) {
            //parse and test number
            try {
                numNode.val = Double.parseDouble(numStr);
                numNode.str = null;
            } catch (NumberFormatException ex) {
                throw new ExprException(ExprException.NUM.BAD_NUMBER,
                        ExprException.NUM.BAD_NUMBER.getDefaultMsg() + ":" + numStr);
            }
        } else {
            if ("e".equals(numStr)) {
                numNode.val = Math.E;
                numNode.str = null;
            } else if ("pi".equals(numStr)) {
                numNode.val = Math.PI;
                numNode.str = null;
            } else {
                //test variable name
                Str.testVarName(numStr);
                if (!varMap.containsKey(numStr)) {
                    varMap.put(numStr, null);
                }
            }
        }
        numStack.add(numNode);
    }

    /**
     * 将符号压入符号栈，如果优先级不足则计算堆栈
     *
     * @param numStack 数字栈（用于计算）
     * @param symStack 符号栈
     * @param symNode 计算符号节点
     */
    private void pushSym(ArrayList<ComputeNode> numStack, ArrayList<ComputeNode> symStack, ComputeNode symNode) throws ExprException {
        while (!symStack.isEmpty()) {
            ComputeNode symTopNode = symStack.get(symStack.size() - 1);
            if (getInStackLevel(symTopNode.str) < getOutStackLevel(symNode.str)) {
                break;
            }
            computeStack(numStack, symStack);
        }
        symStack.add(symNode);
    }

    /**
     * 计算一次堆栈，弹出一个操作符及相应数目的操作数并组合节点将结果压入数字栈
     *
     * @param numStack 数字栈
     * @param symStack 符号栈
     */
    private void computeStack(ArrayList<ComputeNode> numStack, ArrayList<ComputeNode> symStack) throws ExprException {
        if (symStack.isEmpty()) {
            throw new ExprException(ExprException.NUM.MISSING_OPER_SYM);
        }
        ComputeNode symNode = symStack.remove(symStack.size() - 1);
        int numStackSize = numStack.size();
        if (numStackSize < symNode.operCnt) {
            throw new ExprException(ExprException.NUM.MISSING_OPER_NUM);
        }
        symNode.params = new ArrayList<ComputeNode>(symNode.operCnt);
        for (int i = 0; i < symNode.operCnt; i++) {
            symNode.params.add(numStack.get(numStackSize - symNode.operCnt + i));
        }
        for (int i = 0; i < symNode.operCnt; i++) {
            numStack.remove(numStack.size() - 1);
        }
        numStack.add(symNode);
    }

    /**
     * 获取符号的站内优先级
     *
     * @param symStr 符号字符串
     * @return 站内优先级
     */
    private static int getInStackLevel(String symStr) {
        if (symStr.equals("(")) {
            return 0;
        }
        if (symStr.equals("+") || symStr.equals("-")) {
            return 2;
        }
        if (symStr.equals("*") || symStr.equals("/")) {
            return 4;
        }
        if (symStr.equals("^")) {
            return 6;
        }
        if (symStr.endsWith("(")) {//is function
            return 0;
        }
        return -1;
    }

    /**
     * 获取符号的站外优先级
     *
     * @param symStr 符号字符串
     * @return 站外优先级
     */
    private static int getOutStackLevel(String symStr) {
        if (symStr.equals("(")) {
            return 9;
        }
        if (symStr.equals("+") || symStr.equals("-")) {
            return 1;
        }
        if (symStr.equals("*") || symStr.equals("/")) {
            return 3;
        }
        if (symStr.equals("^")) {
            return 5;
        }
        if (symStr.endsWith("(")) {//is function
            return 9;
        }
        return -1;
    }

    /**
     * 计算表达式
     *
     * @param expr 表达式字符串（无变量和自定义函数）
     * @return 表达式结果
     */
    public static double compute(String expr) throws ExprException {
        return (new MathExpr(expr)).compute();
    }
}
