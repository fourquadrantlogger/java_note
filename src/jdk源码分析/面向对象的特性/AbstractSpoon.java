package jdk源码分析.面向对象的特性;

/**
 * Created by timeloveboy on 16/9/30.
 */
public abstract class AbstractSpoon implements Cloneable {
    String spoonName;

    public void setSpoonName(String spoonName) {
        this.spoonName = spoonName;
    }

    public String getSpoonName() {
        return this.spoonName;
    }

    public void make() {

    }

    ;

    public Object clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("AbstractSpoon is not Cloneable");
        }
        return object;
    }
}