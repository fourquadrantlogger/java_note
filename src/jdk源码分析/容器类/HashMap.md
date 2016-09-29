# Hashmap

 在java编程语言中，最基本的结构就是两种，一个是数组，另外一个是模拟指针（引用），所有的数据结构都可以用这两个基本结构来构造的，HashMap也不例外。HashMap实际上是一个“链表散列”的数据结构，即数组和链表的结合体。

 ## 源码
 ```
 /**
  * The table, resized as necessary. Length MUST Always be a power of two.
  */
 transient Entry[] table;

 static class Entry<K,V> implements Map.Entry<K,V> {
     final K key;
     V value;
     Entry<K,V> next;
     final int hash;
 }
 ```

 HashMap底层就是一个数组结构，数组中的每一项又是一个链表。当新建一个HashMap的时候，就会初始化一个数组.