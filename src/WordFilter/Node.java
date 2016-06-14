package WordFilter;


/**
 * Created by timeloveboy on 16/6/14.
 */
public class Node {
    boolean word =false;
    Node[] isnodes =new Node[256];

    public Node(boolean isword) {
        this.word = isword;
    }
    public void AddNode(byte[] bs){

        int node=bs[0];
        if(node<0)node+=256;
        if(isnodes[node]==null) {
            isnodes[node] = new Node(false);
        }

        if(bs.length>1) {
            byte[] newbs = new byte[bs.length - 1];
            System.arraycopy(bs, 1, newbs, 0, bs.length - 1);
            isnodes[node].AddNode(newbs);
        }else {
            word =true;
        }

    }
    public boolean ExistNode(byte[] bs){

        int node=bs[0];
        if(node<0)node+=256;
        if(isnodes[node]==null) {
            return false;
        }

        if(bs.length>1) {
            byte[] newbs = new byte[bs.length - 1];
            System.arraycopy(bs, 1, newbs, 0, bs.length - 1);
            return isnodes[node].ExistNode(newbs);
        }else {
            return true&& word;
        }
    }
}
