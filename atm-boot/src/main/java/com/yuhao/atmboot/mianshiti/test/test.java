package com.yuhao.atmboot.mianshiti.test;

public class test {
    public static void main(String[] args) {
        //字符串索引下标
//        String a = "我爱着你中国，哈哈哈哈";
//        System.out.println(a.indexOf("中国"));

        //构造一个空指针异常
//        Integer b = null;
//        String str = b.toString();
//        System.out.println(str);
//
        //冒泡
//        int[] arrays = {5,6,3,8,1,9,2};
//        int[] ints = bubbleSort(arrays);
//        System.out.println(Arrays.toString(ints));
//
        //99乘法表
        for(int i=1; i<=9;i++){//第一重循环 i 初始值 唯 1；
            for(int j=1; j<=i; j++){//第二重循环 每次j的初始值唯1 当 j 的值 等于 i 时结束当前循环。
                System.out.print(j+" * "+i+ " = "+(i*j) +"\t");  //'\t'制表符，起到对齐作用

            }
            System.out.println();//换行
        }

        //递归求阶乘
//        int b = Factorial(0);
//        System.out.println(b);

        long begin = System.currentTimeMillis();

//        String str = "";
//        1,测试String直接拼接
//        for (int i=0;i<10000;i++){
//            str=str+i;
//        }
//        2,测试String.concat拼接
//        for (int i=0;i<10000;i++){
//            str = str.concat(String.valueOf(i));
//        }
//        3,测试StringBuffer拼接
//        StringBuffer stringBuffer = new StringBuffer();
//        for (int i=0;i<10000;i++){
//           stringBuffer.append(i);
//        }
//        测试StringBuilder拼接
        StringBuilder stringBuilder= new StringBuilder();
        for (int i=0;i<10000;i++){
            stringBuilder.append(i);
        }

//        try {
//            Thread.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        long end = System.currentTimeMillis();
        long time = end - begin;
        System.out.println(time+"");

    }

    public static int Factorial(int n){
        if(n==0){
            return 1;
        }else {
            return n*Factorial(n-1);
        }
    }



    public static int[] bubbleSort(int[] arrays){

        for (int j=0; j<arrays.length-1;j++){
            int temp =0 ;
            for (int i=0;i<arrays.length-j-1;i++) {
                if (arrays[i]>arrays[i+1]){
                    temp=arrays[i];
                    arrays[i]=arrays[i+1];
                    arrays[i+1]=temp;
                }
            }
        }
        return arrays;
    }
}
