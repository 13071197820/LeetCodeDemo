package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.*;

/**
 * 集合类不安全问题
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

        for (int i = 1; i <= 3; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        //java.util.ConcurrentModificationException
        /**
         * 1. 故障现象
         *  java.util.ConcurrentModificationException
         * 2. 导致原因
         *
         * 3. 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>())
         *      3.3
         * 4。 优化建议（同样的错误不犯第二次）
         */

    }
}
