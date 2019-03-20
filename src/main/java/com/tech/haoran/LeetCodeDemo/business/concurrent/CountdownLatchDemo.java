package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

class Resource implements Runnable {

    private CountDownLatch countDownLatch;

    public Resource(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "\t 进行调用");
        if ("5".equals(Thread.currentThread().getName())) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Thread.currentThread().getName() + "\t 调用完成计数器-1");
        countDownLatch.countDown();

    }
}
/**
 * CountdownLatch 能够等待其他线程完成后再执行
 *
 */
public class CountdownLatchDemo {
    public static void main(String[] args) {
        int threadNum = 9;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 1; i <= threadNum; i++) {
            new Thread(() -> {
                Resource resource = new Resource(countDownLatch);
                resource.run();
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
