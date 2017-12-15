package jdk源码分析.多线程.多线程实现生产者消费者;

/**
 * Created by timeloveboy on 17-12-15.
 */
public class Customer implements Runnable {

    Container con;
    String name;

    public Customer(String myname, Container con) {
        this.con = con;
        name = myname;
    }

    void sleeping() {
        try {
            Thread.sleep(150);//调节生产者频率，过快容易猝死~~
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (con) {
                if (con.currentNum > 0) {

                    System.out.println(name + "消费了");
                    con.currentNum--;


                    con.notify();

                } else {
                    System.out.println("物品数已空");
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
