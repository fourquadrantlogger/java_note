package WordFilter;

/**
 * Created by timeloveboy on 16/6/14.
 */
public class WordFilter {
    public void Tester(){
        addFilter("李鹏");
        addFilter("呜呜呜呜");
        addFilter("1234");
        addFilter("234123");

        System.out.println("ExistString(\"1234\")"+ExistString("1234"));
        System.out.println("ExistString(\"12\")"+ExistString("12"));
        System.out.println("ExistString(\"呜呜\")"+ExistString("呜呜"));
        System.out.println("ExistString(\"234123\")"+ExistString("234123"));
    }
    Node[] nodes=new Node[256];
    public void addFilter(String filter){
        byte[] midbytes=filter.getBytes();
        int node=midbytes[0];
        if(node<0)node+=256;
        nodes[node]=new Node(false);

        if(midbytes.length>1) {
            byte[] newbs = new byte[midbytes.length - 1];
            System.arraycopy(midbytes, 1, newbs, 0, midbytes.length - 1);
            nodes[node].AddNode(newbs);
        }
    }
    public boolean ExistString(String str){
        byte[] midbytes=str.getBytes();
        int index=midbytes[0];
        if(index<0)index+=256;

        if(nodes[index]==null)
            return false;

        if(midbytes.length>1) {
            byte[] newbs = new byte[midbytes.length - 1];
            System.arraycopy(midbytes, 1, newbs, 0, midbytes.length - 1);
            return nodes[index].ExistNode(newbs);
        }else {
            return nodes[index].word;
        }
    }
}
