package jdk源码分析.面向对象的特性;

/**
 * Created by timeloveboy on 17-4-7.
 */
//
//定义不可变对象的策略
//
//        以下的一些创建不可变对象的简单策略。并非所有不可变类都完全遵守这些规则，不过这不是编写这些类的程序员们粗心大意造成的，很可能的是他们有充分的理由确保这些对象在创建后不会被修改。但这需要非常复杂细致的分析，并不适用于初学者。
//        不要提供 setter 方法。（包括修改字段的方法和修改字段引用对象的方法）
//        将类的所有字段定义为 final、private 的。
//        不允许子类重写方法。简单的办法是将类声明为 final，更好的方法是将构造函数声明为私有的，通过工厂方法创建对象。
//        如果类的字段是对可变对象的引用，不允许修改被引用对象。
//        不提供修改可变对象的方法。
//        不共享可变对象的引用。当一个引用被当做参数传递给构造函数，而这个引用指向的是一个外部的可变对象时，一定不要保存这个引用。如果必须要保存，那么创建可变对象的拷贝，然后保存拷贝对象的引用。同样如果需要返回内部的可变对象时，不要返回可变对象本身，而是返回其拷贝。
//        将这一策略应用到 SynchronizedRGB 有以下几步：
//        SynchronizedRGB 类有两个 setter 方法。第一个 set 方法只是简单的为字段设值，第二个 invert 方法修改为创建一个新对象，而不是在原有对象上修改。
//        所有的字段都已经是私有的，加上 final 即可。
//        将类声明为 final 的
//        只有一个字段是对象引用，并且被引用的对象也是不可变对象。
//        经过以上这些修改后，我们得到了 ImmutableRGB：
public class 不可变对象 {
    // Values must be between 0 and 255.
    final private int red;
    final private int green;
    final private int blue;
    final private String name;

    public 不可变对象(int red,
                 int green,
                 int blue,
                 String name) {
        check(red, green, blue);
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    private void check(int red,
                       int green,
                       int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    public 不可变对象 invert() {
        return new 不可变对象(255 - red,
                255 - green,
                255 - blue,
                "Inverse of " + name);
    }
}