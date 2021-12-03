package com.ss.stopwatch;

import cn.hutool.core.date.StopWatch;

/**
 * 计时器
 * @author ChengXiao
 * @date 2021/10/11 12:07
 **/
public class Test {

    public static void main(String[] args) throws InterruptedException {
//        Test.test0();
        Test.test1();
    }

    // 传统计时
    public static void test0() throws InterruptedException {
        long start = System.currentTimeMillis();
        // do something
        Thread.sleep(100);
        long end = System.currentTimeMillis();
        long start2 = System.currentTimeMillis();
        // do something
        Thread.sleep(200);
        long end2 = System.currentTimeMillis();
        System.out.println("某某1执行耗时:" + (end - start));
        System.out.println("某某2执行耗时:" + (end2 - start2));
    }

    // stopWatch计时
    public static void test1() throws InterruptedException {
        StopWatch sw = new StopWatch("test");
        sw.start("task1");
        // do something
        Thread.sleep(100);
        sw.stop();
        sw.start("task2");
        // do something
        Thread.sleep(200);
        sw.stop();
        System.out.println("sw.prettyPrint()~~~~~~~~~~~~~~~~~");
        System.out.println(sw.prettyPrint());
    }
}
