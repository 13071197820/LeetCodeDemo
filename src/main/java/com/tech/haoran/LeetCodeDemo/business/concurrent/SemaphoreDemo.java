package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class SemaphoreResource implements Runnable{
    Semaphore semaphore = null;

    public SemaphoreResource(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"\t 正在使用资源");
            int sleepTime = 3;
            if("5".equals(Thread.currentThread().getName()))sleepTime=20;
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName()+"\t 释放资源");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(10,true);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                SemaphoreResource semaphoreResource = new SemaphoreResource(semaphore);
                semaphoreResource.run();
            },String.valueOf(i)).start();
        }
        while(Thread.activeCount()>2){}
        System.out.println("完成");
    }
}
