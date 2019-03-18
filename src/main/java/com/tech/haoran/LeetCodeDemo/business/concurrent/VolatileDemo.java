package com.tech.haoran.LeetCodeDemo.business.concurrent;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyDate{
    volatile int number = 0;
    AtomicInteger atomicInteger = new AtomicInteger();

    public void addNumber(){
        this.number = 60;
    }

    public  void addPlusPlus(){
        number++;
    }

    public void addMyAtomic(){
        atomicInteger.incrementAndGet();
    }
}

/**
 * 验证volatile 可见性和不保证原子性
 *
 * 1. 原子性指的是什么意思。
 *     不可分割，完整性， 某个线程正在做某个具体业务时候，中间不可以被加塞或者被分割
 *     要么同时成功，要么同时失败
 * 2. volatile 是否可以保证原子性。
 *      不能保证
 * 3. 为什么不能保证原子性
 *
 * 4. 如何解决原子性
 *   1. 加Syncronize
 */
public class VolatileDemo {

    public static void main(String[] args) {
        MyDate myDate = new MyDate();
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myDate.addPlusPlus();
                    myDate.addMyAtomic();
                }
            },String.valueOf(i)).start();
        }
        //需要等待线程全部计算完成后，再用main线程取得最终结果值。
        //Main + GC 两个线程
        while(Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName()+"\t int result"+myDate.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger result"+myDate.atomicInteger);
    }

    //volatile 可以保证可见性，及时通知其他线程，主内存的值已经被修改。
    private static void volatileVisibility() {
        MyDate myDate = new MyDate();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t start");
            try {
                TimeUnit.SECONDS.sleep(3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myDate.addNumber();
            System.out.println(Thread.currentThread().getName()+"\t update 60 over");

        },"AAA").start();

        while (myDate.number==0){

        }
        System.out.println("查询成功:"+myDate.number);
    }
}
