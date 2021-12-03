package com.ss.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程测试2-> 主线程等待子线程执行完毕再继续执行
 * @author chuan
 * @title: ThreadResultTest
 * @description: TODO(获取Thread的返回值)
 */
public class ThreadResultTest2 implements Runnable{
    /**
     * 在没有获取Thread的返回值之前，先判断一下线程是否执行完成
     */

    // 线程安全的List集合
    private static List<String> sycnList = Collections.synchronizedList(new ArrayList<String>());
    // 创建一个线程池
    private static ExecutorService executor = Executors.newFixedThreadPool(1);

    private int count = 0;

    public ThreadResultTest2(int count) {
        this.count = count;
    }

    @Override
    public void run() {
        try {
            sycnList.add("1");
            Thread.currentThread().setName("Thread-Pool");
            int nextInt = new Random().nextInt(1000);
            Thread.sleep(nextInt);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        for (int i = 0; i < 12; i++) {
            executor.execute(new ThreadResultTest2(i));
        }
        System.out.println("execute info : 线程已添加到队列，正在执行中...");
        executor.shutdown();

        while (true) {
            Thread.sleep(1000);
            if(executor.isTerminated()) {
                System.out.println(sycnList);
                System.out.printf("execute result : %s","所有的线程执行完毕！");
                break;
            }
        }
    }
}