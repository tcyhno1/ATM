package com.yuhao.atmboot.mianshiti;

public class FourThreads {
    private int j;

    public static void main(String[] args) {
        FourThreads many = new FourThreads();
        Inc inc = many.new Inc();
        Dec dec = many.new Dec();
        for (int i=0;i<2;i++) {
            Thread t =new Thread(inc);
            t.start();
            t = new Thread(dec);
            t.start();
        }

    }

    private synchronized void inc() {
        j++;
        System.out.println(Thread.currentThread().getName()+"inc"+j);
    }

    class Inc implements Runnable {
        @Override
        public void run() {
            for (int i=0;i<20;i++) {
                inc();
            }
        }
    }

    private synchronized void dec() {
        j--;
        System.out.println(Thread.currentThread().getName()+"dec"+j);
    }

    class Dec implements Runnable {
        @Override
        public void run() {
            for (int i=0;i<20;i++){
                dec();
            }
        }
    }

}
