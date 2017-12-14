package jdk源码分析.Object;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by timeloveboy on 17-12-14.
 */
public class User {
    String name;

    public User(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Map<String, User> userMap = new HashMap<>();
        userMap.put("1", new User("李鹏"));
        userMap.put("2", new User("鹏"));

        System.out.println(userMap.get("1").name);
        System.out.println(userMap.get("2").name);
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
