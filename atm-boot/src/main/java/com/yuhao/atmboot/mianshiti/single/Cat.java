package com.yuhao.atmboot.mianshiti.single;

/**
 * 单例设计模式之懒汉式
 *懒汉式是典型的时间换空间，也就是每次获取实例都会进行判断，
 * 看是否需要创建实例，浪费判断的时间。
 * 当然，如果一直没有人使用的话，那就不会创建实例，则节约内存空间。
 */
public class Cat {

    private static Cat cat;

    private Cat(){

    }

    /**
     * 懒汉式有并发问题，所以必须加锁。
     *  并发问题：因为不是和饿汉式一样，类一加载就创建好了单例对象
     *  而是在线程第一次用的时候才创建对象，那么在高并发的情况下，
     *  就有可能出现多个线程同时进入了 getCat() 方法，这时由于还没有
     *  单例实例，那么同时判断，同时创建，就达不到单例的效果了，
     *  所以需要加锁。
     */
    //当锁作用在静态方法上面锁住的是字节码对象，反之锁的是当前对象this
    public static synchronized Cat getCat(){
        if (null == cat) {
            cat = new Cat();
        }

        return Cat.cat;
    }

    //这个方法和上面一样，是为了证明锁住的是字节码对象
    public static Cat getCat2(){
        synchronized (Cat.class) {
            if (null == cat) {
                cat = new Cat();
            }

            return Cat.cat;
        }
    }

    /**
     * 上述的方法虽然通过加锁，解决了并发问题，但是每次有人调用单例
     * 的时候，都需要锁住getCat()方法，这样影响效率。所以应该在锁之前
     * 先进行一下判断，没有创建过实例再进入锁中进行创建，已经有实例了就直接
     * 返回实例，不需要再进入锁住的方法中。
     */

    public static Cat getCat3(){
        if (null == cat) {
            synchronized (Cat.class){
                cat = new Cat();
                return cat;
            }
        }

        return cat;
    }

}
