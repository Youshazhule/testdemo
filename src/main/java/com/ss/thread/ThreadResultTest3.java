package com.ss.thread;

/**
 * 多线程测试3
 * @author ChengXiao
 * @date 2021/7/26 17:54
 **/
public class ThreadResultTest3 {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                if ("Thread-0".equals(Thread.currentThread().getName())) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {}
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
        }
        System.out.println("继续执行");
    }
}
