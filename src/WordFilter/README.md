# 敏感词过滤算法

 这是目前我所发现的最快的敏感词过滤算法,支持 全词匹配。
 ``` java
public void Tester(){
        addFilter("李鹏");
        addFilter("呜呜呜呜");
        addFilter("1234");
        addFilter("234123");

        System.out.println("ExistString(\"1234\")"+ExistString("1234"));
        System.out.println("ExistString(\"12\")"+ExistString("12"));
        System.out.println("ExistString(\"呜呜\")"+ExistString("呜呜"));
        System.out.println("ExistString(\"234123\")"+ExistString("234123"));
    }


ExistString("1234")true
ExistString("12")false
ExistString("呜呜")false
ExistString("234123")true
 ```

