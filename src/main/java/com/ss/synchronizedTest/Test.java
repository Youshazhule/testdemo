package com.ss.synchronizedTest;

import org.checkerframework.checker.units.qual.C;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ChengXiao
 * @date 2021/8/14 11:52
 **/
public class Test {

    private volatile int counts = 0;

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test();
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {

            service.execute(() -> {
                test.add(countDownLatch);
            });
        }
        countDownLatch.await();
        System.out.println(test.counts);
    }

    public synchronized void add(CountDownLatch countDownLatch){
        counts++;
        countDownLatch.countDown();
    }
}
