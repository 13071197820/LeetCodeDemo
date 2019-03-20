package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class CyclicBarrierResource implements Runnable{
    public CyclicBarrierResource(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    CyclicBarrier cyclicBarrier =null;
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t 进行调用");
        if ("5".equals(Thread.currentThread().getName())) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName() + "\t 调用完成进入等待");
        try {
            cyclicBarrier.await(11,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }
}
public class CyclicBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                CyclicBarrierResource resource = new CyclicBarrierResource(cyclicBarrier);
                resource.run();
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){

        }

        System.out.println("全部执行完成");
        cyclicBarrier.reset();
        System.out.println("开始进行重用");
        TimeUnit.SECONDS.sleep(2);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                CyclicBarrierResource resource = new CyclicBarrierResource(cyclicBarrier);
                resource.run();
            }, String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){

        }

        System.out.println("重用完成");
    }
}
