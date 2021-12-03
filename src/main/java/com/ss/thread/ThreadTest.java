package com.ss.thread;

/**
 * 多线程测试4
 * @author ChengXiao
 * @date 2021/7/26 12:25
 **/
public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("主线程开始执行");
        for(int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "子线程开始执行");
                if (finalI == 0) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {}
                }
                System.out.println(Thread.currentThread().getName() + "子线程执行完毕");
            }).start();
        }
        System.out.println("主线程执行完毕");
    }
}
