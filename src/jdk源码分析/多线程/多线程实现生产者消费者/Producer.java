package jdk源码分析.多线程.多线程实现生产者消费者;

/**
 * Created by timeloveboy on 17-12-15.
 */
public class Producer implements Runnable {

    Container con;
    String name;

    public Producer(String myname, Container con) {
        this.con = con;
        name = myname;
    }

    void sleeping() {
        try {
            Thread.sleep(100);//调节生产者频率，过快容易猝死~~
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (con) {
                if (con.currentNum < con.max) {

                    System.out.println(name + "生产中");

                    con.notify();


                    con.currentNum++;
                    System.out.println(name + "生产完成  当前总数:\t" + con.currentNum);


                } else {
                    System.out.println("物品数已满");
                    try {
                        con.wait();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }

            sleeping();
        }
    }
}
