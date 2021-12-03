package com.ss.countdownlatch;

/**
 * 测试多线程异常
 */
public class CantCatchDirectly implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        //try catch 只能捕获线程内的异常
        try{
            new Thread(new CantCatchDirectly(),"myThread-1").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(),"myThread-2").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(),"myThread-3").start();
            Thread.sleep(300);
            new Thread(new CantCatchDirectly(),"myThread-4").start();
        }catch (RuntimeException e){
            System.out.println("Caught Exception.");
        }
    }
    @Override
    public void run() {
        try{
            throw new RuntimeException();
        }catch (RuntimeException e){
            System.out.println("Caught Exception.");
        }
    }
}