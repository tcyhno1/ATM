package com.yuhao.atmboot.mianshiti.test;


import java.util.Arrays;

public class test1 {

    public static void main(String[] args) {
//        String string = "  123 45 67 8 90  ";
//        System.out.println(string.replace(" ",""));
//        System.out.println(string.trim());
        //1234567890

//        String a= "a";
//        String b= "b";
//        String c= "c";
//        String d= "d";
//        String e=a+b+c+d;
//        System.out.println(e);
//
//        String q = "1" + "2" + "3" + "4";
//        System.out.println(q);
//
//       int[] i = {3,1,5,3,2,6,2};
//       int[] j = bubbleSort(i);
//        System.out.println(Arrays.toString(j));

        //String a= diandao1("1234");




    }

    public static String diandao (String str) {

        char[] chars = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=chars.length-1; i>=0 ;i --) {
            stringBuilder.append(chars[i]);
        }

        String s = stringBuilder.toString();
        System.out.println(s);
        return  s;
    }

    public static String diandao1 (String str) {
        System.out.println(new StringBuilder(str).reverse().toString());
        return new StringBuilder(str).reverse().toString();
    }

    public static int[] bubbleSort (int[] arrays) {

        for (int j=0; j<= arrays.length-1;j++){
            int temp =-1;
            for (int i=0;i <=arrays.length-j-i;i++) {
                if (arrays[i]>arrays[i+1]){
                   temp =arrays[i+1];
                   arrays[i+1] = arrays[i];
                   arrays[i]=temp;
                }
            }
        }
        return arrays;
    }





}
