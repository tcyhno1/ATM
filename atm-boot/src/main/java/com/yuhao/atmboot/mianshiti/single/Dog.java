package com.yuhao.atmboot.mianshiti.single;


/**
 * 单例设计模式之饿汉式
 * 饿汉式是典型的空间换时间，当类装载的时候就会创建类实例，
 * 不管你用不用，先创建出来，然后每次调用的时候，
 * 就不需要再判断了，节省了运行时间。
 */
public class Dog {

    private static final Dog dog = new Dog();


    //自定义类中，如果不写构造方法，java系统会默认添加一个无参的构造方法。如果写了一个有参的构造方法，就一定要写无参构造方法。
    private Dog() {

    }
    /**
     *     单例设计模式的精髓就在于构造函数是私有的，
     *     不能被外界直接调用，需要通过调用类的静态方法由类自己
     *     调用构造函数，这样才能保证单例的实现。
     */
    public static Dog getDog() {
        return Dog.dog;
    }

}
