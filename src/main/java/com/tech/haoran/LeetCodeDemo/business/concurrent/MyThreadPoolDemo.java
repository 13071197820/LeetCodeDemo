package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2019/3/21 0021.
 */
public class MyThreadPoolDemo {
	public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().availableProcessors());
		System.out.println(Runtime.getRuntime().maxMemory());
		ExecutorService executorService = new ThreadPoolExecutor(
				2,
				5,
				1L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(3),
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.CallerRunsPolicy()
		);

		try {
			for (int i = 1; i <=10; i++) {
				final int number = i;
				executorService.execute(
						()->{
							System.out.println(Thread.currentThread().getName()+"\t 正在进行处理"+number);
						}
				);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}

	}
}
