# jvm

![jvm](/img/jvm.gif)

Java代码编译是由Java源码编译器来完成
![jvm](/img/complete.gif)

Java字节码的执行是由JVM执行引擎来完成
![jvm](/img/run.gif)

Java代码编译和执行的整个过程包含了以下三个重要的机制：

Java源码编译机制
类加载机制
类执行机制
Java源码编译机制

Java 源码编译由以下三个过程组成：

分析和输入到符号表
注解处理
语义分析和生成class文件


JVM的类加载是通过ClassLoader及其子类来完成的
![jvm](/img/classloader.gif)

1）Bootstrap ClassLoader

负责加载$JAVA_HOME中jre/lib/rt.jar里所有的class，由C++实现，不是ClassLoader子类

2）Extension ClassLoader

负责加载java平台中扩展功能的一些jar包，包括$JAVA_HOME中jre/lib/*.jar或-Djava.ext.dirs指定目录下的jar包

3）App ClassLoader

负责记载classpath中指定的jar包及目录中class

4）Custom ClassLoader

属于应用程序根据自身需要自定义的ClassLoader，如tomcat、jboss都会根据j2ee规范自行实现ClassLoader

加载过程中会先检查类是否被已加载
+ 检查顺序是自底向上，从Custom ClassLoader到BootStrap ClassLoader逐层检查，只要某个classloader已加载就视为已加载此类，保证此类只所有ClassLoader加载一次。
+ 而加载的顺序是自顶向下，也就是由上层来逐层尝试加载此类。