package com.yuhao.atmboot.mianshiti.single;

public class Test {

    public static void main(String[] args) {
//        Dog dog1 = Dog.getDog();
//        Dog dog2 = Dog.getDog();
//        System.out.println(dog1==dog2);

//        Cat cat1 = Cat.getCat3();
//        Cat cat2 = Cat.getCat3();
//        System.out.println(cat1==cat2);
//
//        Bird bird1 = Bird.getBird();
//        Bird bird2 = Bird.getBird();
//        System.out.println(bird1==bird2);

        Fish fish1 = Fish.INSTANCE;
        Fish fish2 = Fish.INSTANCE;
        System.out.println(fish1==fish2);
        fish1.say();
        fish2.say();


    }


}
