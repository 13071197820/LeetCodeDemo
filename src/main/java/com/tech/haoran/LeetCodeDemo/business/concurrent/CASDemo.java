package com.tech.haoran.LeetCodeDemo.business.concurrent;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1. CAS 是什么？  ----> compareAndSet
 * 比较并交换
 */
public class CASDemo    {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        //do something....

        System.out.println(atomicInteger.compareAndSet(5, 100)+"\t current date "+atomicInteger.get());

        System.out.println(atomicInteger.compareAndSet(5, 1)+"\t current date "+atomicInteger.get());
        atomicInteger.getAndIncrement();

    }
}
