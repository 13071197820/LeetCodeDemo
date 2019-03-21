package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareResource{
	private volatile boolean flag = true;//默认开启,进行生产消费
	private AtomicInteger atomicInteger = new AtomicInteger(0);
	BlockingQueue<String> blockingQueue = null;

	public ShareResource(BlockingQueue<String> blockingQueue){
		this.blockingQueue = blockingQueue;
		System.out.println(blockingQueue.getClass().getName());
	}

	public void myProduct() throws  Exception{
		//1.判断
		String date = null;
		boolean resultValue;
		while(flag){
			date = atomicInteger.incrementAndGet()+"";
			resultValue = blockingQueue.offer(date,2, TimeUnit.SECONDS);
			if(resultValue){
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+date+"成功");
			}else{
				System.out.println(Thread.currentThread().getName()+"\t 插入队列"+date+"失败");
			}
			TimeUnit.SECONDS.sleep(1);
		}

		System.out.println(Thread.currentThread().getName()+"\t 停止 flag = false 生产动作结束");
	}

	public void myConsumer() throws  Exception{
		String result = null;
		while(flag){
			result = blockingQueue.poll(2,TimeUnit.SECONDS);
			if(result == null|| "".equals(result)){
				flag = false;
				System.out.println(Thread.currentThread().getName()+"\t 超过2秒钟没有取到,消费退出");
				return;
			}
			System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
		}
	}

	public void stop() throws  Exception{
		this.flag= false;
	}



}
/**
 * Created by Administrator on 2019/3/21 0021.
 */
public class ProdConsumerDemBlockQuque {

	public static void main(String[] args) throws Exception {
		ShareResource shareResource = new ShareResource(new LinkedBlockingQueue<String>(10));

		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"/t 生产线程启动");
			try {
				shareResource.myProduct();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"Product").start();

		new Thread(()->{
			System.out.println(Thread.currentThread().getName()+"/t 消费线程启动");
			try {
				shareResource.myConsumer();
			} catch (Exception e) {
				e.printStackTrace();
			}
		},"Consumer").start();


		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("停止运行");
		shareResource.stop();
	}
}
