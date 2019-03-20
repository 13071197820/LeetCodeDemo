package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Administrator on 2019/3/18 0018.
 * 自旋锁
 * SpinLockDemo 实现一个自旋锁
 * 好处: 循环比较知道成功为止,不阻塞
 * 坏处: 循环消耗CPU
 */
public class SpinLockDemo {
	//原子引用线程
	AtomicReference<Thread> atomicReference = new AtomicReference<>();

	public void myLock(){
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName()+"\t come in");
		while(!atomicReference.compareAndSet(null,thread)){

		}

	}

	public void myUnlock(){
		Thread thread = Thread.currentThread();

		atomicReference.compareAndSet(thread,null);
		System.out.println(thread.getName()+"\t myUnlock");

	}

	public static void main(String[] args)   {
		SpinLockDemo spinLockDemo = new SpinLockDemo();
		new Thread(()->{
			spinLockDemo.myLock();
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spinLockDemo.myUnlock();
		},"t1").start();

		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		new Thread(()->{
			spinLockDemo.myLock();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			spinLockDemo.myUnlock();
		},"t2").start();

	}
}
