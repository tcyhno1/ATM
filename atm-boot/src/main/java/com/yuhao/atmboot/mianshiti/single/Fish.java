package com.yuhao.atmboot.mianshiti.single;


/**
 * 通过枚举实现单例，最简单的方法。
 */
public enum Fish {

    INSTANCE;

    //业务代码
    public void say(){
        System.out.println("Fish.say()");
    }

}
