package com.ss.j8function;

import java.util.function.BiFunction;

/**
 * @author ChengXiao
 * @date 2021/12/3 9:48
 **/
public class FunctionalInterfaceTest {

    public static void main(String[] args) {
        /**
         * Bi类型的接口创建
         */
        BiFunction<String, String, Integer> biFunction = (str1, str2) -> str1.length()+str2.length();

    }
}
