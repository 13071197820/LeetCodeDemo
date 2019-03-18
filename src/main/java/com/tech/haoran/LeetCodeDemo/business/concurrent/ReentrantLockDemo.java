package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
	public synchronized void sendSMS() throws Exception{
		System.out.println(Thread.currentThread().getName()+"\t invoke sendSMS");
		sendEmail();
	}

	public synchronized void sendEmail() throws Exception{
		System.out.println(Thread.currentThread().getName()+"\t ###### invoke sendEmail");
	}

	Lock lock = new ReentrantLock();
	@Override
	public void run() {
		get();
	}
	public void get(){
		lock.lock();
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getName()+"\t ###### invoke get");
			set();
		}finally {
			lock.unlock();
			lock.unlock();
		}
	}
	public void set(){
		lock.lock();
		lock.lock();
		lock.lock();
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getName()+"\t ###### invoke set");
		}finally {
			lock.unlock();
			lock.unlock();
			//lock.unlock();
			lock.unlock();
		}
	}
}
/**
 * Created by Administrator on 2019/3/18 0018.
 * 可重入锁(递归锁)
 *
 * 指的是同一线程在外层函数获得锁之后,内层递归函数仍然能获取该锁的代码
 * 在同一个线程外层方法获取锁的时候,在进入内层方法会自动获得锁..
 *
 * 也就是说,线程可以进入任何一个它已经拥有的锁 所同步的代码块
 */
public class ReentrantLockDemo {
	public static void main(String[] args) {
		Phone phone = new Phone();

		new Thread(()->{
			try {
				phone.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"t1").start();
		new Thread(()->{
			try {
				phone.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"t2").start();

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread thread3 = new Thread(phone);
		Thread thread4 = new Thread(phone);
		thread3.start();
		thread4.start();
	}
}
