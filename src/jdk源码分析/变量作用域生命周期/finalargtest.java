package jdk源码分析.变量作用域生命周期;

/**
 * Created by timeloveboy on 17-4-8.
 */
public class finalargtest {
    public static void main(String[] args) {
        new finalargtest().mRun("ok");
    }

    public void mRun(String name) {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(name);
            }
        }.start();
    }

//    这种代码相信大家写过很多，当内部类访问局部变量的时候，需要在局部变量前加final修饰符，不然编译器就会报错。通常我们也是这么干的。好的，第二个问题来了，为什么要加final修饰符？相信大多数小伙伴都没有思考过这个问题，但凡使用的时候，直接加上就得了，从来没去深究过其中的原理。这对于一个优秀的程序员来说是不可取，我们不仅要知其然还要知其所以然。
//    现在我们来分析一下，为什么要加final关键字。首先内部类的生命周期是成员级别的，而局部变量的生命周期实在方法体之类。也就是说会出现这样一种情况，当mRun方法执行，new 的线程运行，新线程里面会睡一秒。主线程会继续执行，mRun执行完毕，name属性生命周期结束。1秒之后，Syetem.out.printh(name)执行。然而此时name已经寿终正寝，不在内存中了。Java就是为了杜绝这种错误，严格要求内部类中方位局部变量，必须使用final关键字修饰。局部变量被final修饰之后，此时会在内存中保有一份局部变得的复制品，当内部类访问的时候其实访问的是这个复制品。这就好像是把局部变量的生命周期变长了。说到底还是Java工程师提前把这个坑给我们填了，不然不知道又会有多少小伙伴会为了内部类局部变量而发愁了。
}
