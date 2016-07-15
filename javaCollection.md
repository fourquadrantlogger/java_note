## Set
Set是一个不包含重复元素的collection

HashSet : HashSet类按照哈希算法来存取集合中的对象，存取速度比较快
TreeSet   : TreeSet类实现了SortedSet接口，能够对集合中的对象进行排序。

## map
java中的map其实就是以键值对形式的存放数据的容器，其常用的实现类主要是哈希map
例如：
Map map = new HashMap();
插入元素：map.put("key", obj); 
移除元素: map.remove("key");
清空: map.clear();

## list
ArrayList() : 代表长度可以改变得数组。可以对元素进行随机的访问，向ArrayList()中插入与与删除元素的速度慢。
LinkedList(): 在实现中采用链表数据结构。插入和删除速度快，访问速度慢。
对于List的随机访问来说，就是只随机来检索位于特定位置的元素。

## java list copy方法
```
 List<Xunzhang> list_data = new ArrayList<>(Arrays.asList(new Xunzhang[list_localall.size()]));
 Collections.copy(list_data,list_localall);
```