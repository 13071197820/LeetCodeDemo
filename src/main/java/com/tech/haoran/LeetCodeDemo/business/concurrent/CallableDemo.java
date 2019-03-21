package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements  Runnable{

	@Override
	public void run() {

	}
}

class MyThread2 implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		System.out.println("*** come in callable");
		TimeUnit.SECONDS.sleep(2);
		return 1024;
	}
}
/**
 * Created by Administrator on 2019/3/21 0021.
 */
public class CallableDemo {

	public static void main(String[] args) throws Exception{

		FutureTask<Integer> futureTask = new FutureTask<>(new MyThread2());
		new Thread(futureTask,"A").start();
		new Thread(futureTask,"B").start();
		new Thread(futureTask,"C").start();

		System.out.println(futureTask.get());


	}
}
