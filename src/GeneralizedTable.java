import java.util.Stack;

/**
 * Created by timeloveboy on 16/6/1.
 */
public class GeneralizedTable {
    public static final int TAG_ITEM = 0; // 原子节点
    public static final int TAG_TABLE = 1; // 表节点
    /*
     * 广义表支持的符号包括'(' , ')' , '{' , '}' , '[' , ']'
     * 广义表表示符号,使用字符串构造广义表时第一个字符必须是'(', '{' , '[' 之一 并以')' , '}' , ']' 之一结束，
     * 并且各符号相对应
     */
    private char mStartSymb = '(';
    private char mEndSymb = ')';
    private Node mGenTable;

    public GeneralizedTable() {
        mGenTable = new Node(null, null, TAG_TABLE, null);
    }

    // 使用广义表 src 构造一个新的广义表
    public GeneralizedTable(GeneralizedTable src) {
        if (src != null) {
            mGenTable = src.mGenTable;
        }

    }

    /**
     * @param genTable
     */
    public GeneralizedTable(String genTable) {
        if (genTable == null) {
            throw new NullPointerException("genTable is null in constructor GeneralizedTable!...");
        }
        initTable(genTable);
    }

    private void initTable(String genTable) {
        String ts = genTable.replaceAll("\\s", "");
        int len = ts.length();
        Stack<Character> symbStack = new Stack<Character>();
        Stack<Node> nodeStck = new Stack<Node>();
        initSymbolicCharactor(ts);
        mGenTable = new Node(null, null, TAG_TABLE, null);
        Node itemNode, tableNode = mGenTable, tmpNode;
        for (int i = 0; i < len; i++) {
            if (ts.charAt(i) == mStartSymb) {
                tmpNode = new Node(null, null, TAG_TABLE, null);
                // tableNode = tableNode.next;
                symbStack.push(ts.charAt(i));
                if (symbStack.size() > 1) {
                    nodeStck.push(tableNode);
                    tableNode.nodes = tmpNode;
                    tableNode = tableNode.nodes;
                } else {
                    tableNode.next = tmpNode;
                    tableNode = tableNode.next;
                }
            } else if (ts.charAt(i) == mEndSymb) {
                if (symbStack.isEmpty()) {
                    throw new IllegalArgumentException(
                            "IllegalArgumentException in constructor GeneralizedTable!...");
                }
                if (symbStack.size() > 1) {
                    tableNode = nodeStck.pop();
                }
                symbStack.pop();
            } else if (ts.charAt(i) == ',') {
                tableNode.next = new Node(null, null, TAG_TABLE, null);
                tableNode = tableNode.next;
            } else {
                itemNode = new Node(null, null, TAG_ITEM, ts.charAt(i));
                tableNode.nodes = itemNode;
            }
        }

        if (!symbStack.isEmpty()) {
            throw new IllegalArgumentException(
                    "IllegalArgumentException in constructor GeneralizedTable!...");
        }
    }

    private void initSymbolicCharactor(String ts) {
        mStartSymb = ts.charAt(0);
        switch (mStartSymb) {
            case '(':
                mEndSymb = ')';
                break;
            case '{':
                mEndSymb = '}';
                break;
            case '[':
                mEndSymb = ']';
                break;
            default:
                throw new IllegalArgumentException(
                        "IllegalArgumentException ---> initSymbolicCharactor");
        }
    }

    public void print() {
        print(mGenTable);
    }

    private void print(Node node) {
        if (node == null) {
            return;
        }
        if (node.mTag == 0) {
            System.out.print(node.mData.toString() + " \t");
        }
        print(node.nodes);
        print(node.next);

    }

    public int depth() { // 广义表的深度
        if (mGenTable == null) {
            throw new NullPointerException("Generalized Table is null !.. ---> method depth");
        }
        return depth(mGenTable);
    }

    private int depth(Node node) {
        if (node == null || node.mTag == TAG_ITEM) {
            return 0;
        }
        int depHeader = 0, depTear = 0;
        depHeader = 1 + depth(node.nodes);
        depTear = depth(node.next);
        return depHeader > depTear ? depHeader : depTear;
    }

    public int length() { // 广义表的长度
        if (mGenTable == null || mGenTable.next == null) {
            return -1;
        }
        int tLen = 0;
        Node node = mGenTable;
        while (node.next != null) {
            node = node.next;
            if (node.nodes == null && node.next == null) {
                break;
            }
            tLen++;
        }
        return tLen;
    }

    public GeneralizedTable getHeader() {
        if (isEmpty())
            return null;
        Node node = mGenTable.next;
        GeneralizedTable gt = new GeneralizedTable();
        gt.mGenTable.next = node.nodes;
        return gt;
    }

    public GeneralizedTable getTear() {
        if (isEmpty())
            return null;
        Node node = mGenTable.next;
        GeneralizedTable gt = new GeneralizedTable();
        gt.mGenTable.next = node.next;
        return gt;
    }

    public boolean isEmpty() {
        if (mGenTable == null) {
            return true;
        }
        Node node = mGenTable.next;
        return node == null || node.nodes == null;
    }

    public class Node {// 广义表节点
        Node nodes; // 广义表的表节点
        Node next; // 广义表表尾节点
        int mTag; // mTag == 0 , 院子节点 ; mTag == 1 , 表节点 。
        Object mData; // 广义表的数据值

        public Node(Node ph, Node pt, int tag, Object data) {
            nodes = ph;
            next = pt;
            mTag = tag;
            mData = data;
        }
    }

}
