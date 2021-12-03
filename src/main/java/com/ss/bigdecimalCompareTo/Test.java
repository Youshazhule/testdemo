package com.ss.bigdecimalCompareTo;

import java.math.BigDecimal;

/**
 * BigDecimal判断相等
 * @author ChengXiao
 * @date 2021/8/20 14:21
 **/
public class Test {

    public static void main(String[] args) {
//        BigDecimal a = new BigDecimal(1000);
//        BigDecimal b = new BigDecimal(1000);
//        if (a.compareTo(b) == 0) {
//            System.out.println("相等");
//        } else {
//            System.out.println("不相等");
//        }

//        compare(47,50);
        String sellable = "10";
        if (!Integer.valueOf(sellable).equals(14)) {
            System.out.println("不相等");
        } else {
            System.out.println("相等");
        }
    }

    public static void compare(int a,int b){
        String s=a>b? a+">"+b+" 相差"+(a-b):a+"<"+b+"相差"+(b-a);
        if (a > b) {
            // 盘亏
            System.out.println("盘亏" + (a-b));
        } else {
            System.out.println("盘盈" + (b-a));
        }
    }
}
