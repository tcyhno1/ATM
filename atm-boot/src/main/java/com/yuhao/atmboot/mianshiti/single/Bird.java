package com.yuhao.atmboot.mianshiti.single;


/**
 * 静态内部类实现单例，一是解决了懒加载线程安全问题和获取单例时的判断问题；
 * 二是解决了饿汉式和枚举在加载时无论是否使用就分配内存的问题；
 * 三是可以和懒加载、饿汉式一样通过在私有构造中判断单例是否为null来进行非法构造方法反射的控制。
 *
 */
public class Bird {

    private Bird(){

    }

    private static class Inner {
        //外部类不能直接访问内部类的非静态成员变量，需要实例化出内部类才能访问
        //这里重点是 这个成员变量是static的，所以外部类可以直接访问
        public static Bird bird = new Bird();
    }

    public static Bird getBird(){
        return Inner.bird;
    }

}
