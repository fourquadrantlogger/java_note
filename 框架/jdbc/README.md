JDBC与Hibernate在性能上相比，JDBC灵活性有优势。而Hibernate在易学性，易用性上有些优势。当用到很多复杂的多表联查和复杂的数据库操作时，JDBC有优势。

相同点：

◆两者都是JAVA的数据库操作中间件。

◆两者对于数据库进行直接操作的对象都不是线程安全的，都需要及时关闭。

◆两者都可以对数据库的更新操作进行显式的事务处理。

不同点：

◆使用的SQL语言不同：JDBC使用的是基于关系型数据库的标准SQL语言，Hibernate使用的是HQL(Hibernate query language)语言

◆操作的对象不同：JDBC操作的是数据，将数据通过SQL语句直接传送到数据库中执行，Hibernate操作的是持久化对象，由底层持久化对象的数据更新到数据库中。

◆数据状态不同：JDBC操作的数据是“瞬时”的，变量的值无法与数据库中的值保持一致，而Hibernate操作的数据是可持久的，即持久化对象的数据属性的值是可以跟数据库中的值保持一致的。

JDBC与Hibernate读取性能

1、JDBC仍然是最快的访问方式，不论是Create还是Read操作，都是JDBC快。

2、Hibernate使用uuid.hex构造主键，性能稍微有点损失，但是不大。

3、Create操作，JDBC在使用批处理的方式下速度比Hibernate快，使用批处理方式耗用JVM内存比不使用批处理方式要多得多。

4、读取数据，Hibernate的Iterator速度非常缓慢，因为他是每次next的时候才去数据库取数据，这一点从观察任务管理器的java进程占用内存的变化也可以看得很清楚，内存是几十K几十K的增加。

5、读取数据，Hibernate的List速度很快，因为他是一次性把数据取完，这一点从观察任务管理器的java进程占用内存的变化也可以看得很清楚，内存几乎是10M的10M的增加。

6、JDBC读取数据的方式和Hibernate的List方式是一样的（这跟JDBC驱动有很大关系，不同的JDBC驱动，结果会很不一样），这从观察java进程内存变化可以判断出来，由于JDBC不需要像Hibernate那样构造一堆Cat对象实例，所以占用JVM内存要比Hibernate的List方式大概少一半左右。

7、Hibernate的Iterator方式并非一无是处，它适合于从大的结果集中选取少量的数据，即不需要占用很多内存，又可以迅速得到结果。另外Iterator适合于使用JCS缓冲。最终结论：

由于MySQL的JDBC驱动的重大缺陷，使得测试结果变得毫无意义，不具备任何参考价值，只是我们能够大概判断出一些结论：

一、精心编写的JDBC无论如何都是最快的。

二、Hibernate List和Iterator适用的场合不同，不存在孰优孰劣的问题

我个人认为Hibernate Iterator是JDBC Result的封装，Hibernate List是Scrollable Result的封装，所以我推测，如果在Oracle或者DB2上面做同样的Read测试，如果结果集小于FetchSize，4者在速度上应该都不会有差别；如果结果集大于FetchSize的话，但是不是FetchSize的很多倍，速度排名应该是：

JDBC Scrollable Result (消耗时间最少) < Hibernate List < JDBC Result < Hibernate Iterator

如果结果集非常大，但是只取结果集中的部分记录，那么速度排名：

JDBC Result < Hibernate Iterator < JDBC Scrollable Result < Hibernate List

为了避免造成误导，我最后强调一下我的结论：

一、“精心编写”的JDBC一定是性能最好的

实际上，不管CMP，Hibernate，JDO等等，所有的ORM都是对JDBC的封装，CMP则是一个重量级封装，JDO中度封装，Hibernate是轻量级的封装。从理论上来说，ORM永远也不可能比JDBC性能好。就像任何高级语言的运行性能永远也不会好过汇编语言一个道理。

对于Create和Update操作来说，由于普通的Java程序员未必会使用JDBC的Batch的功能，所以Hibernate会表现出超过JDBC的运行速度。

对于Read的操作来说，ORM普遍都会带有双层缓冲，即PrepreadStatement缓冲和ResultSet缓冲，而JDBC本身没有缓冲机制，在使用连接池的情况下，一些连接池将会提供PrepreadStatement缓冲，有的甚至提供ResultSet缓冲，但是普遍情况下，Java程序员一般都不会考虑到在写JDBC的时候优化缓冲，而且这样做也不太现实，所以在某些情况下，ORM会表现出超过JDBC的Read速度。

二、Hibernate List和Iterator方式的比较

JDBC与Hibernate在测试中想要重点考察的方面是 List与Iterator，但是由于JDBC驱动问题，结果变的很不可信，不过仍然可以得到一些有用的结论。

Read操作包括两步：第一步是把数据库的数据取出，构造结果集，把数据放入到结果集中；第二步是遍历结果集，取每行数据。

List方式是1次性把所有的数据全部取到内存中，构造一个超大的结果集，主要的时间开销是这一步，这一步的时间开销要远远超过JDBC和Iterator方式下构造结果集的时间开销，并且内存开销也很惊人；而对结果集的遍历操作，速度则是非常的惊人（从上面的测试结果来看，30万记录的内存遍历不到100ms，由于这一步不受JDBC影响，因此结果可信）。因此，List方式适合于对结果集进行反复多次操作的情况，例如分页显示，往后往前遍历，跳到第一行，跳到最后一行等等。

Iterator方式只取记录id到内存中，并没有把所有数据取到内存中，因此构造结果集的时间开销很小，比JDBC和List方式都要少，并且内存开销也小很多。而对结果集的遍历的操作的时候，Iterator仍然要访问数据库，所有主要的时间开销都花在这里。因此，Iterator方式适合于只对结果集进行1次遍历操作的情况，并且Iterator方式特别适合于从超大结果集中取少量数据，这种情况Iterator性能非常好。

另外Iterator方式可以利用JCS缓冲，在使用缓冲的情况下Iterator方式的遍历操作速度将不受数据库访问速度的影响，得到彻底的提升。Hibernate Iterator JCS方式应该是最快的，Hibernate List速度与JDBC比较接近，而Hibernate Iterator速度还是慢的离谱。另外JDBC和List受到Fetch Size的影响很大，当Fetch Size大于50的时候，速度有非常显著的提升，而Hibernate Iterator的速度似乎不受Fetch Size的影响。