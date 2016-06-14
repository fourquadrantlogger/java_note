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

        System.out.print("ExistString(\"1234\")"+ExistString("1234"));
    }
    Node[] nodes=new Node[256];
    public void addFilter(String filter){
        byte[] midbytes=filter.getBytes();
        int index=midbytes[0];
        if(index<0)index+=256;
        nodes[index]=new Node(true);

        if(midbytes.length>1) {
            byte[] newbs = new byte[midbytes.length - 1];
            System.arraycopy(midbytes, 1, newbs, 0, midbytes.length - 1);
            nodes[index].AddNode(newbs);
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
            return true;
        }
    }
}
