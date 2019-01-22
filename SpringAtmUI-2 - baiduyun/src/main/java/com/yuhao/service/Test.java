package com.yuhao.service;

import java.math.BigDecimal;

public class Test {

    private String[] chars;
    private int[] count;
    public void countChar(String str) {
        chars = new String[str.length()];
        count = new int[str.length()];
        for (int i=0;i<str.length();i++) {
            for(int idx = 0;idx<chars.length;idx++) {
                if (chars[idx] == null) {
                    chars[idx] = str.substring(i, i + 1);
                    count[idx] = count[idx] + 1;
                    break;
                }
                if (chars[idx].equals(str.substring(i, i + 1))) {
                    count[idx] = count[idx] + 1;
                    break;
                }
            }
        }
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != null)
                System.out.println("字符[" + chars[i] + "]出现次数：" + count[i]);
        }
    }
    public static void main(String[] args) {
        Test test = new Test();
        test.countChar("ahudhjakhjafadashdwjaj");

    }
}
