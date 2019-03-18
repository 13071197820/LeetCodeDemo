package com.tech.haoran.LeetCodeDemo.business.concurrent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@Getter
@ToString
@AllArgsConstructor
class User{
    String userName;
    int age;
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User u1 = new User("u1",20);
        User u2 = new User("u2",11);
        atomicReference.set(u1);

        System.out.println(atomicReference.compareAndSet(u1, u2)+"\t "+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(u1, u2)+"\t "+atomicReference.get().toString());
    }

}
