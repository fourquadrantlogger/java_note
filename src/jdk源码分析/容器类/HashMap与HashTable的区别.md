# HashMap和Hashtable的区别
HashMap是Hashtable的轻量级实现（非线程安全的实现），他们都完成了Map接口，主要区别在于

+ HashMap允许空（null）键值（key）,由于非线程安全，效率上可能高于Hashtable。
+ HashMap允许将null作为一个entry的key或者value，而Hashtable不允许。
+ HashMap把Hashtable的contains方法去掉了，改成containsvalue和containsKey。因为contains方法容易让人引起误解。
+ Hashtable继承自Dictionary类，而HashMap是Java1.2引进的Map interface的一个实现。
+ Hashtable的方法是Synchronize的，而HashMap不是，在多个线程访问Hashtable时，不需要自己为它的方法实现同步，而HashMap 就必须为之提供外同步（如果是ArrayList：List lst = Collections.synchronizedList(new ArrayList());如果是HashMap：Map map = Collections.synchronizedMap(new HashMap());）。
+ Hashtable和HashMap采用的hash/rehash算法都大概一样，所以性能不会有很大的差异。

## Hashtable rehash算法:拉链法

![hash](/img/hashtable.gif)

Hashtable的实质就是一个数组＋链表。图中的Entry就是链表的实现，Entry的结构中包含了对自己的另一个实例的 引用next，用以指向另外一个Entry.

在Hashtable的实现代码中，有一个名为rehash的方法用于扩充Hashtable的容量。很明显，当rehash方法被调用以后，每一个键值 对相应的index也会改变，也就等于将键值对重新排序了。这也是往不同容量的Hashtable放入相同的键值对会输出不同的键值对序列的原因。在 Java中，触发rehash方法的条件很简单：hahtable中的键值对超过某一阀值。默认情况下，该阀值等于hashtable中Entry数组的 长度×0.75