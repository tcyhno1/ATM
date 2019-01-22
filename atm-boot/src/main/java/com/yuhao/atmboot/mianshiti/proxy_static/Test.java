package com.yuhao.atmboot.mianshiti.proxy_static;

public class Test {

    public static void main(String[] args) {

        //应用了多态，我们new一个代理类，然后调用方法，实际上底层调用的是代理类中的增强方法和被代理的实现类的方法
        PayService payService = new PayServiceProxy();
        payService.say();
        payService.pay();

    }

}
