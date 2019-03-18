package com.tech.haoran.LeetCodeDemo.business.concurrent;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 * ArrayList
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i <30 ; i++) {
            new Thread(()->{
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

    }

    private static void copyOnWriteArraySet() {
        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 1; i <= 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void copyOnWriteArrayList() {
        List<String> list = new CopyOnWriteArrayList<>();
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
         *  并发争抢修改导致
         * 3. 解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>())
         *      3.3 CopyOnWriteArrayList
         * 4。 优化建议（同样的错误不犯第二次）
         */}
}
