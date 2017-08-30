#jstat

jstat -{以下} (pid)

-class：统计class loader行为信息 
-compile：统计编译行为信息 
-gc：统计jdk gc时heap信息 
-gccapacity：统计不同的generations（包括新生区，老年区，permanent区）相应的heap容量情况 
-gccause：统计gc的情况，（同-gcutil）和引起gc的事件 
-gcnew：统计gc时，新生代的情况 
-gcnewcapacity：统计gc时，新生代heap容量 
-gcold：统计gc时，老年区的情况 
-gcoldcapacity：统计gc时，老年区heap容量 
-gcpermcapacity：统计gc时，permanent区heap容量 
-gcutil：统计gc时，heap情况 
-printcompilation：不知道干什么的，一直没用过。 
一般比较常用的几个用法： 