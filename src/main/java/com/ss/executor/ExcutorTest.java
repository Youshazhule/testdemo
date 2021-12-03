package com.ss.executor;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author ChengXiao
 * @date 2021/6/22 11:35
 **/
public class ExcutorTest {

    public static void main(String[] args) {
        /**
         * 默认开启2个线程
         * 最大开启5个
         * 当线程开启3秒后未被使用则关闭该线程
         * 允许3个用户进入阻塞队列
         * AbortPolicy拒绝后抛出RejectedExecutionException异常
         */
        ExecutorService threadPool = new ThreadPoolExecutor(
                0,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        final List<Future> threadList = Lists.newArrayList();

        for (int i = 0; i < 3; i++) {
            Future result = threadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName()+"执行中...");
                try {
                    // 业务逻辑
                    Thread.sleep(8000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"执行完毕");
            });
            threadList.add(result);
        }

        threadList.forEach(future -> {
            try {

                // 如果在超时时间内，没有数据返回：则抛出TimeoutException异常
                Object callResult = future.get(6, TimeUnit.SECONDS);
                System.out.println(callResult);
            } catch (InterruptedException e) {
                future.cancel(true);
                System.out.println("InterruptedException发生");
            } catch (ExecutionException e) {
                future.cancel(true);
                System.out.println("ExecutionException发生");
            } catch (TimeoutException e) {
                future.cancel(true);
                System.out.println("TimeoutException发生，意味着线程超时报错");
            }
        });
        System.out.println("主程序执行完成……");
    }
}
