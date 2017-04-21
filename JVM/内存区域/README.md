# 对象

## 对象创建
+ 指针碰撞

> 空闲区和已用区，以指针为界限

Serial、ParNew等带COmpact的收集器
+ 空闲列表

>java用列表，记录空闲的堆区域

CMS基于Mark-Sweep算法的