package com.ss.thread;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程测试2-> 主线程等待子线程执行完毕再继续执行
 * @author chuan
 * @title: ThreadResultTest
 * @description: TODO(获取Thread的返回值)
 */
public class ThreadResultTest implements Runnable{
    /**
     * 在没有获取Thread的返回值之前，先判断一下线程是否执行完成
     */
 
    // 线程安全的List集合
    private static List<Object> sycnList = Collections.synchronizedList(new ArrayList<Object>());

    private String sellerId;

    public ThreadResultTest(String sellerId){
        this.sellerId = sellerId;
    }

    @Override
    public void run() {
        try {
            List<Object> data = Lists.newArrayList();
            if ("二号线".equals(sellerId)) {
                // 假设二号线数据量大产生耗时操作
                Thread.sleep(5000);
                data.add("二号线数据");
                sycnList.addAll(data);
            } else {
                data.add(sellerId + "数据");
                sycnList.addAll(data);
            }
            System.out.println(sellerId + " 执行下载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) throws Exception{
        // 模拟多账号下载任务
        List<String> list = Lists.newArrayList();
        list.add("一号线");
        list.add("二号线");
        list.add("三号线");

        // 创建一个线程池 根据账号数量
        ExecutorService executor = Executors.newFixedThreadPool(list.size());

        System.out.println("==========================================下载中心");
        for (String s : list) {
            executor.execute(new ThreadResultTest(s));
        }
        System.out.println("execute info : 下载任务线程已添加到队列，正在执行中...");
        System.out.println("初始状态->下载中");
        executor.shutdown();
        while (true) {
            if(executor.isTerminated()) {
                System.out.println("多账号最终数据汇总" + sycnList);
                System.out.printf("execute result : %s","所有的线程执行完毕！");
                System.out.println("修改状态->下载完毕");
                break;
            }
        }
    }
}