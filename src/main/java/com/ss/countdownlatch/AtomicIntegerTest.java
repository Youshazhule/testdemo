package com.ss.countdownlatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程安全Integer、线程池、CountDownLatch测试并发线程安全
 *
 * @author ChengXiao
 * @date 2021/8/12 9:42
 **/
public class AtomicIntegerTest {

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService threadPool = new ThreadPoolExecutor(
//                3,
//                10,
//                3,
//                TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(3),
//                Executors.defaultThreadFactory(),
//                new ThreadPoolExecutor.AbortPolicy());
        System.out.println(Thread.currentThread().getName()+"开始执行");
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(20);
        try {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                Future future = service.submit(() -> {
                    t(finalI, countDownLatch, atomicInteger);
                });
                future.get();
            }
            countDownLatch.await();
            System.out.println("全部执行完毕" + atomicInteger.get());
        } catch (Exception e) {
            System.out.println(Thread.currentThread().getName() + "发生异常" + e.getMessage());
        } finally {
            service.shutdown();
        }
    }

    public static void t(int i, CountDownLatch countDownLatch, AtomicInteger atomicInteger) {
        if (i == 10) {
//            Thread.sleep(10000);
            throw new RuntimeException("异常。。。");
        }
        System.out.println(Thread.currentThread().getName() + " 拿到-> " + i);
        atomicInteger.incrementAndGet();
        countDownLatch.countDown();
    }
}
