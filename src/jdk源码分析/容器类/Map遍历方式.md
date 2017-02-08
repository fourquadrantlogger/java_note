1. 阐述
　　对于Java中Map的遍历方式，很多文章都推荐使用entrySet，认为其比keySet的效率高很多。理由是：entrySet方法一次拿到所有key和value的集合；而keySet拿到的只是key的集合，针对每个key，都要去Map中额外查找一次value，从而降低了总体效率。那么实际情况如何呢？

　　为了解遍历性能的真实差距，包括在遍历key+value、遍历key、遍历value等不同场景下的差异，我试着进行了一些对比测试。

2. 对比测试
　　一开始只进行了简单的测试，但结果却表明keySet的性能更好，这一点让我很是费解，不都说entrySet明显好于keySet吗？为了进一步地进行验证，于是采用了不同的测试数据进行更详细的对比测试。

2.1 测试数据
2.1.1 HashMap测试数据

HashMap-1，大小为100万，key和value均为String，key的值为1、2、3……1000000：
Map<String, String> map = new HashMap<String, String>();

String key, value;

for (i = 1; i <= num; i++) {

    key = "" + i;

    value = "value";

    map.put(key, value);

}

HashMap-2，大小为100万，key和value均为String，key的值为50、100、150、200、……、50000000：
Map<String, String> map = new HashMap<String, String>();

String key, value;

for (i = 1; i <= num; i++) {

    key = "" + (i * 50);

    value = "value";

    map.put(key, value);

}

2.1.2 TreeMap测试数据

TreeMap-1，大小为100万，key和value均为String，key的值为1、2、3……1000000：
Map<String, String> map = new TreeMap<String, String>();

String key, value;

for (i = 1; i <= num; i++) {

    key = "" + i;

    value = "value";

    map.put(key, value);

}

TreeMap-2，大小为100万，key和value均为String，key的值为50、100、150、200、……、50000000，更离散：
Map<String, String> map = new TreeMap<String, String>();

String key, value;

for (i = 1; i <= num; i++) {

    key = "" + (i * 50);

    value = "value";

    map.put(key, value);

}

2.2 测试场景

　　分别使用keySet、entrySet和values的多种写法测试三种场景：遍历key+value、遍历key、遍历value的场景。

2.2.1 遍历key+value

keySet遍历key+value（写法1）：
Iterator<String> iter = map.keySet().iterator();

while (iter.hasNext()) {

    key = iter.next();

    value = map.get(key);

}

keySet遍历key+value（写法2）：
for (String key : map.keySet()) {

    value = map.get(key);

}

entrySet遍历key+value（写法1）：
Iterator<Entry<String, String>> iter = map.entrySet().iterator();

Entry<String, String> entry;

while (iter.hasNext()) {

    entry = iter.next();

    key = entry.getKey();

    value = entry.getValue();

}

 entrySet遍历key+value（写法2）：
for (Entry<String, String> entry: map.entrySet()) {

    key = entry.getKey();

    value = entry.getValue();

}

2.2.2 遍历key

keySet遍历key（写法1）：
Iterator<String> iter = map.keySet().iterator();

while (iter.hasNext()) {

    key = iter.next();

}

keySet遍历key（写法2）：
for (String key : map.keySet()) {

}

 entrySet遍历key（写法1）：
Iterator<Entry<String, String>> iter = map.entrySet().iterator();

while (iter.hasNext()) {

    key = iter.next().getKey();

}

entrySet遍历key（写法2）：
for (Entry<String, String> entry: map.entrySet()) {

    key = entry.getKey();

}

2.2.3 遍历value

keySet遍历value（写法1）：
Iterator<String> iter = map.keySet().iterator();

while (iter.hasNext()) {

    value = map.get(iter.next());

}

keySet遍历value（写法2）：
for (String key : map.keySet()) {

    value = map.get(key);

}

entrySet遍历value（写法1）：
Iterator<Entry<String, String>> iter = map.entrySet().iterator();

while (iter.hasNext()) {

value = iter.next().getValue();

}

entrySet遍历value（写法2）：
for (Entry<String, String> entry: map.entrySet()) {

    value = entry.getValue();

}

values遍历value（写法1）：
Iterator<String> iter = map.values().iterator();

while (iter.hasNext()) {

value = iter.next();

}

values遍历value（写法2）：
for (String value : map.values()) {

}

2.3 测试结果

2.3.1 HashMap测试结果

单位：毫秒

HashMap-1

HashMap-2

keySet遍历key+value（写法1）

39

93

keySet遍历key+value（写法2）

38

87

entrySet遍历key+value（写法1）

43

86

entrySet遍历key+value（写法2）

43

85

 

单位：毫秒

HashMap-1

HashMap-2

keySet遍历key（写法1）

27

65

keySet遍历key（写法2）

26

64

entrySet遍历key（写法1）

35

75

entrySet遍历key（写法2）

34

74

 

单位：毫秒

HashMap-1

HashMap-2

keySet遍历value（写法1）

38

87

keySet遍历value（写法2）

37

87

entrySet遍历value（写法1）

34

61

entrySet遍历value（写法2）

32

62

values遍历value（写法1）

26

48

values遍历value（写法2）

26

48

2.3.2 TreeMap测试结果

单位：毫秒

TreeMap-1

TreeMap-2

keySet遍历key+value（写法1）

430

451

keySet遍历key+value（写法2）

429

450

entrySet遍历key+value（写法1）

77

84

entrySet遍历key+value（写法2）

70

68

 

单位：毫秒

TreeMap-1

TreeMap-2

keySet遍历key（写法1）

50

49

keySet遍历key（写法2）

49

48

entrySet遍历key（写法1）

66

64

entrySet遍历key（写法2）

65

63

 

单位：毫秒

TreeMap-1

TreeMap-2

keySet遍历value（写法1）

432

448

keySet遍历value（写法2）

430

448

entrySet遍历value（写法1）

62

61

entrySet遍历value（写法2）

62

61

values遍历value（写法1）

46

46

values遍历value（写法2）

45

46

3. 结论

3.1 如果你使用HashMap
同时遍历key和value时，keySet与entrySet方法的性能差异取决于key的具体情况，如复杂度（复杂对象）、离散度、冲突率等。换言之，取决于HashMap查找value的开销。entrySet一次性取出所有key和value的操作是有性能开销的，当这个损失小于HashMap查找value的开销时，entrySet的性能优势就会体现出来。例如上述对比测试中，当key是最简单的数值字符串时，keySet可能反而会更高效，耗时比entrySet少10%。总体来说还是推荐使用entrySet。因为当key很简单时，其性能或许会略低于keySet，但却是可控的；而随着key的复杂化，entrySet的优势将会明显体现出来。当然，我们可以根据实际情况进行选择
只遍历key时，keySet方法更为合适，因为entrySet将无用的value也给取出来了，浪费了性能和空间。在上述测试结果中，keySet比entrySet方法耗时少23%。
只遍历value时，使用vlaues方法是最佳选择，entrySet会略好于keySet方法。
在不同的遍历写法中，推荐使用如下写法，其效率略高一些：
for (String key : map.keySet()) {

    value = map.get(key);

}

 

for (Entry<String, String> entry: map.entrySet()) {

    key = entry.getKey();

    value = entry.getValue();

}

 

for (String value : map.values()) {

}

3.2 如果你使用TreeMap

同时遍历key和value时，与HashMap不同，entrySet的性能远远高于keySet。这是由TreeMap的查询效率决定的，也就是说，TreeMap查找value的开销较大，明显高于entrySet一次性取出所有key和value的开销。因此，遍历TreeMap时强烈推荐使用entrySet方法。
只遍历key时，keySet方法更为合适，因为entrySet将无用的value也给取出来了，浪费了性能和空间。在上述测试结果中，keySet比entrySet方法耗时少24%。
只遍历value时，使用vlaues方法是最佳选择，entrySet也明显优于keySet方法。
在不同的遍历写法中，推荐使用如下写法，其效率略高一些：
for (String key : map.keySet()) {

    value = map.get(key);

}

 

for (Entry<String, String> entry: map.entrySet()) {

    key = entry.getKey();

    value = entry.getValue();

}

 

for (String value : map.values()) {

}
