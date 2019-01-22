package com.yuhao.atmboot.util;

import java.util.Random;

public class CardUtils {

    private static final int card_length = 5;

    public static String createCardNum() {
        final Random random = new Random();
        StringBuilder sx = new StringBuilder();

        for (int i = 0; i < card_length; i++) {
            final int number = random.nextInt();
            sx.append(String.valueOf(Math.abs(number % 10)));
        }

        System.out.println(sx.toString());
        return sx.toString();
    }

    public static void main(String[] args) {
        CardUtils.createCardNum();
    }
}
