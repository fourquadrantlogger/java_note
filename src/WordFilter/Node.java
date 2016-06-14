package WordFilter;


/**
 * Created by timeloveboy on 16/6/14.
 */
public class Node {
    boolean b=false;
    Node[] nodes=new Node[256];

    public Node(boolean b) {
        this.b = b;
    }
    public void AddNode(byte[] bs){

        int node=bs[0];
        if(node<0)node+=256;
        if(nodes[node]==null) {
            nodes[node] = new Node(true);
        }

        if(bs.length>1) {
            byte[] newbs = new byte[bs.length - 1];
            System.arraycopy(bs, 1, newbs, 0, bs.length - 1);
            nodes[node].AddNode(newbs);
        }

    }
    public boolean ExistNode(byte[] bs){

        int node=bs[0];
        if(node<0)node+=256;
        if(nodes[node]==null) {
            return false;
        }

        if(bs.length>1) {
            byte[] newbs = new byte[bs.length - 1];
            System.arraycopy(bs, 1, newbs, 0, bs.length - 1);
            return nodes[node].ExistNode(newbs);
        }else {
            return true;
        }
    }
}
