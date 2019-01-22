package com.yuhao.atmboot.mianshiti.proxy_static;

public class PayServiceProxy implements PayService{

    private PayService payService = new PayServiceImpl();  //这里是一个多态的应用

    public void pay(){

        System.out.println("方法前增强");
        payService.pay();
        System.out.println("方法后增强");

    }

    public void say() {

    }
}
