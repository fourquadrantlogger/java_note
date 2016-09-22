# StringBuffer,StringBuilder 的区别

java.lang.StringBuffer 线程安全的可变字符序列。

一个类似于 String 的字符串缓冲区,但不能修改。

StringBuilder 与该类相比,通常应该优先使用 StringBuilder 类,因为她支持所有相同的操作,但由于她不执行同步,所以速度更快。为了获得更好的性能,在构造 StringBuffer
或 StringBuilder 时应尽量指定她的容量。当然如果不超过 16 个字符时就不用了。
相同情况下,使用 StringBuilder 比使用 StringBuffer 仅能获得 10%~15%的性
能提升,但却要冒多线程不安全的风险。综合考虑还是建议使用 StringBuffer


String 对象的使用中,出现字符串连接情况时应使用
StringBuffer 代替