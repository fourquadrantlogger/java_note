## jmap -histo 

## jmap -heap 13704
``` 
jmap -heap 13704
Attaching to process ID 13704, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.101-b13

using thread-local object allocation.
Parallel GC with 8 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 0
   MaxHeapFreeRatio         = 100
   MaxHeapSize              = 4181721088 (3988.0MB)
   NewSize                  = 87031808 (83.0MB)
   MaxNewSize               = 1393557504 (1329.0MB)
   OldSize                  = 175112192 (167.0MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 0 (0.0MB)

Heap Usage:
PS Young Generation
Eden Space:
   capacity = 1167589376 (1113.5MB)
   used     = 933327520 (890.0904846191406MB)
   free     = 234261856 (223.40951538085938MB)
   79.93628061240598% used
From Space:
   capacity = 11534336 (11.0MB)
   used     = 884736 (0.84375MB)
   free     = 10649600 (10.15625MB)
   7.670454545454546% used
To Space:
   capacity = 24641536 (23.5MB)
   used     = 0 (0.0MB)
   free     = 24641536 (23.5MB)
   0.0% used
PS Old Generation
   capacity = 153092096 (146.0MB)
   used     = 43186216 (41.185585021972656MB)
   free     = 109905880 (104.81441497802734MB)
   28.209304809570312% used

24201 interned Strings occupying 2887824 bytes.
```

## jmap导出jvm内存信息

sudo ./jmap -dump:format=b,file=mapfile  -F 12223

jhat查看内存
sudo ./jhat mapfile