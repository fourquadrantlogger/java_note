正文
wait、notify和notifyAll方法是Object类的final native方法。所以这些方法不能被子类重写，Object类是所有类的超类，因此在程序中有以下三种形式调用wait等方法。

wait();//方式1：
this.wait();//方式2：
super.wait();//方式3
void notifyAll()

解除所有那些在该对象上调用wait方法的线程的阻塞状态。该方法只能在同步方法或同步块内部调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。

void notify()

随机选择一个在该对象上调用wait方法的线程，解除其阻塞状态。该方法只能在同步方法或同步块内部调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。

void wait()

导致线程进入等待状态，直到它被其他线程通过notify()或者notifyAll唤醒。该方法只能在同步方法中调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。

void wait(long millis)和void wait(long millis,int nanos)

导致线程进入等待状态直到它被通知或者经过指定的时间。这些方法只能在同步方法中调用。如果当前线程不是锁的持有者，该方法抛出一个IllegalMonitorStateException异常。

Object.wait()和Object.notify()和Object.notifyall()必须写在synchronized方法内部或者synchronized块内部，这是因为：这几个方法要求当前正在运行object.wait()方法的线程拥有object的对象锁。即使你确实知道当前上下文线程确实拥有了对象锁，也不能将object.wait()这样的语句写在当前上下文中。如：