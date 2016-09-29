#Object 方法重写

## Object 的 toString()默认定义
```
public String toString() {
 return getClass().getName() + "@" + Integer.toHexString(hashCode());
}
```

##  重新定义 equals()
