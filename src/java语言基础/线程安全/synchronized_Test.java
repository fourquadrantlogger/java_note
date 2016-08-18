// 当一个线程进入一个对象的一个synchronized方法后，其它线程是否可进入此对象的其它方法?
// 不能，一个对象的一个synchronized方法只能由一个线程访问


package  java语言基础.线程安全;

public class synchronized_Test{
    private int j;
    public static void main(String args[]){
        synchronized_Test tt=new synchronized_Test();
        Insertc inc=tt.new Insertc();
        Deletec dec=tt.new Deletec();
        for(int i=0;i<2;i++){
            Thread t=new Thread(inc);
            t.start();
            Thread t2=new Thread(dec);
            t2.start();
        }
    }
    private synchronized void inc(){
        j++;
        System.out.println(Thread.currentThread().getName()+"-inc:"+j);
    }
    private synchronized void dec(){
        j--;
        System.out.println(Thread.currentThread().getName()+"-dec:"+j);
    }
    class Insertc implements Runnable{
        public void run(){
            for(int i=0;i<100;i++){
                inc();
            }
        }
    }
    class Deletec implements Runnable{
        public void run(){
            for(int i=0;i<100;i++){
                dec();
            }
        }
    }
}