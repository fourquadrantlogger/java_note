# ArrayBlockingQueue

ArrayBlockingQueue的原理就是使用一个可重入锁和这个锁生成的两个条件对象进行并发控制

# LinkedBlockingQueue
LinkedBlockingQueue是一个使用链表完成队列操作的阻塞队列。链表是单向链表，而不是双向链表。
内部使用放锁和拿锁，这两个锁实现阻塞(“two lock queue” algorithm)。