package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class SharData{
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void incriment()  throws Exception{
		lock.lock();
		try{
			//判断
			while(number!=0){
				condition.await();
			}
			//干活
			number++;
			System.out.println(Thread.currentThread().getName()+"\t "+number);
			//通知唤醒
			condition.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

	public void decriment()  throws Exception{
		lock.lock();
		try{
			//判断
			while(number == 0){
				condition.await();
			}
			//干活
			number--;
			System.out.println(Thread.currentThread().getName()+"\t "+number);
			//通知唤醒
			condition.signalAll();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}

}

/**
 * Created by Administrator on 2019/3/21 0021.
 *
 * 高内聚,低耦合前提下线程操作资源类
 *
 *  判断 干活 唤醒 通知
 *  严防多线程并发下的虚假唤醒
 *
 */
public class ProdConsumerDemoLow {

	public static void main(String[] args) {
		SharData sharData = new SharData();

		new Thread(()->{
			for (int i = 1; i <=5 ; i++) {
				try {
					sharData.incriment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"AA").start();

		new Thread(()->{
			for (int i = 1; i <= 5; i++) {
				try {
					sharData.decriment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		},"BB").start();
	}

}
