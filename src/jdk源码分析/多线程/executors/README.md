为加载和管理线程定义了高级 API。Executors 的实现由 java.util.concurrent 包提供，提供了适合大规模应用的线程池管理。
在之前所有的例子中，Thread 对象表示的线程和 Runnable 对象表示的线程所执行的任务之间是紧耦合的。这对于小型应用程序来说没问题，但对于大规模并发应用来说，合理的做法是将线程的创建与管理和程序的其他部分分离开。封装这些功能的对象就是执行器，接下来的部分将讲详细描述执行器。
执行器接口

在 java.util.concurrent 中包括三个执行器接口：
Executor，一个运行新任务的简单接口。
ExecutorService，扩展了 Executor 接口。添加了一些用来管理执行器生命周期和任务生命周期的方法。
ScheduledExecutorService，扩展了 ExecutorService。支持 future 和（或）定期执行任务。
通常来说，指向 executor 对象的变量应被声明为以上三种接口之一，而不是具体的实现类
Executor 接口

Executor 接口只有一个 execute 方法，用来替代通常创建（启动）线程的方法。例如：r 是一个 Runnable 对象，e 是一个 Executor 对象。可以使用
e.execute(r);
代替

(new Thread(r)).start();
但 execute 方法没有定义具体的实现方式。对于不同的 Executor 实现，execute 方法可能是创建一个新线程并立即启动，但更有可能是使用已有的工作线程运行r，或者将 r放入到队列中等待可用的工作线程。（我们将在线程池一节中描述工作线程。）
ExecutorService 接口

ExecutorService 接口在提供了 execute 方法的同时，新加了更加通用的 submit 方法。submit 方法除了和 execute 方法一样可以接受 Runnable 对象作为参数，还可以接受 Callable 对象作为参数。使用 Callable对象可以能使任务返还执行的结果。通过 submit 方法返回的 Future 对象可以读取 Callable 任务的执行结果，或是管理 Callable 任务和 Runnable 任务的状态。 ExecutorService 也提供了批量运行 Callable 任务的方法。最后，ExecutorService 还提供了一些关闭执行器的方法。如果需要支持即时关闭，执行器所执行的任务需要正确处理中断。
ScheduledExecutorService 接口

ScheduledExecutorService 扩展 ExecutorService接口并添加了 schedule 方法。调用 schedule 方法可以在指定的延时后执行一个Runnable 或者 Callable 任务。ScheduledExecutorService 接口还定义了按照指定时间间隔定期执行任务的 scheduleAtFixedRate 方法和 scheduleWithFixedDelay 方法。+

线程池

线程池是最常见的一种执行器的实现。
在 java.util.concurrent 包中多数的执行器实现都使用了由工作线程组成的线程池，工作线程独立于所它所执行的 Runnable 任务和 Callable 任务，并且常用来执行多个任务。
使用工作线程可以使创建线程的开销最小化。在大规模并发应用中，创建大量的 Thread 对象会占用占用大量系统内存，分配和回收这些对象会产生很大的开销。
一种最常见的线程池是固定大小的线程池。这种线程池始终有一定数量的线程在运行，如果一个线程由于某种原因终止运行了，线程池会自动创建一个新的线程来代替它。需要执行的任务通过一个内部队列提交给线程，当没有更多的工作线程可以用来执行任务时，队列保存额外的任务。
使用固定大小的线程池一个很重要的好处是可以实现优雅退化(degrade gracefully)。例如一个 Web 服务器，每一个 HTTP 请求都是由一个单独的线程来处理的，如果为每一个 HTTP 都创建一个新线程，那么当系统的开销超出其能力时，会突然地对所有请求都停止响应。如果限制 Web 服务器可以创建的线程数量，那么它就不必立即处理所有收到的请求，而是在有能力处理请求时才处理。
创建一个使用线程池的执行器最简单的方法是调用 java.util.concurrent.Executors 的 newFixedThreadPool 方法。Executors 类还提供了下列一下方法：
newCachedThreadPool 方法创建了一个可扩展的线程池。适合用来启动很多短任务的应用程序。
newSingleThreadExecutor 方法创建了每次执行一个任务的执行器。
还有一些 ScheduledExecutorService 执行器创建的工厂方法。
如果上面的方法都不满足需要，可以尝试 java.util.concurrent.ThreadPoolExecutor 或者java.util.concurrent.ScheduledThreadPoolExecutor。