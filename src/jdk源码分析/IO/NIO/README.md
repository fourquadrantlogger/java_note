# NIO

作者：阿里云云栖社区
链接：https://www.zhihu.com/question/29005375/answer/147516503
来源：知乎
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

> IO与NIO的区别IO是面向流的，而NIO是面向块的。

面向块的方式中，一次性可以获取或者写入一整块数据，而不需要一个字节一个字节的从流中读取。面向块的方式处理数据的速度会比流方式更快。NIO基础通道 Channel与缓冲器BufferBuffer是一个保存数据的地方，包括刚刚写入的数据，以及被读取的数据，主要用来追踪系统的读写进程。Channel与流模式比较类似，但是，永远无法将数据直接写入到Channel或者从Channel中读取数据。需要通过Buffer与Channel交互。NIO的读与写读取第一步，获取通道FileInputStream inputStream = new FileInputStream("read.txt");
FileChannel inputChannel = inputStream.getChannel();
第二步，创建缓冲器ByteBuffer buffer = ByteBuffer.allocate(1024);
第三步，将channel中的数据读取到buffer中。相当于写入bufferint readBytes = inputChannel.read(buffer);
写入将数据写入到buffer中，然后再将buffer中的数据写入到channel中
```
FileOutputStream outputStream = new FileOutputStream("output.txt");
FileChannel outputChannel = inputStream.getChannel();
ByteBuffer buffer = ByteBuffer.allocate(1024);
buffer.put(new String("message").getBytes());
buffer.filp();
outputChannel.write(buffer);
```
Buffer细节通过 position, limit, capacity 可以控制读取和写入的数据。以下均为伪代码：put方法bytes[postion++] = c;
flip方法：limit = position;
position = 0;
get方法:byte c = bytes[position++];
clear方法：position = 0;
limit = capacity;
选择器 SelectorSelector 就是您注册对各种 I/O 事件的兴趣的地方，而且当那些事件发生时，就是这个对象告诉您所发生的事件。以下我们看一下服务端如何将channel感兴趣的事件绑定在selector上。
```
  // 获得一个ServerSocket通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 设置通道为非阻塞
        serverChannel.configureBlocking(false);
        // 将该通道对应的ServerSocket绑定到port端口
        serverChannel.socket().bind(new InetSocketAddress(port));
        // 获得一个通道管理器
        Selector selector = Selector.open();
        //将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件,注册该事件后，
        //当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()会一直阻塞。
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
```
内部循环：
现在我们已经将感兴趣的IO事件注册到selector上，下面将进入主循环：
```
int num = selector.select();

Set selectedKeys = selector.selectedKeys();
Iterator it = selectedKeys.iterator();

while (it.hasNext()) {
     SelectionKey key = (SelectionKey)it.next();
     // ... deal with I/O event ...
}
```
这样，当时select返回时，channel中已经可以read或者write了。总结选择器selector 将 通道channel感兴趣的IO事件注册监听，当其返回时，channel即可对这些IO事件进行处理，一般将这些读写操作都会放到单独的线程中执行，提高吞吐率。