package com.tech.haoran.LeetCodeDemo.business.alipay;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

class Tool{

    public static double fenToYuan(int amount){
        BigDecimal HUNDRED= new BigDecimal(100);
        return  new BigDecimal(amount).divide(HUNDRED).doubleValue();
    }
}

class LeftMoneyPackage {
    volatile AtomicInteger peoples;
    volatile AtomicStampedReference<Integer> leftMoney;

    public static final int MIN_GIFT_AMOUNT=1;
    public static final int GET_GIFT_FAIL=0;
    public static final int PEOPLE_LAST=1;
    public static final int PEOPLE_FAIL=0;

    BlockingQueue<String> blockingQueue = null;

    public LeftMoneyPackage() {
        this.blockingQueue = new LinkedBlockingQueue<>();
    }

    public LeftMoneyPackage(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public Integer getRandomMoney() {
        int stamp = leftMoney.getStamp();
        int leftAmount = leftMoney.getReference();
        if (peoples.get() == PEOPLE_FAIL) {
            return GET_GIFT_FAIL;
        }
        if (peoples.get() == PEOPLE_LAST) {
            peoples.getAndDecrement();
            return leftAmount;
        }
        Random r     = new Random();
        int max   = (leftAmount/ peoples.get() * 2);
        System.out.println(Thread.currentThread().getName()+"\t people:"+peoples.get());
        System.out.println(Thread.currentThread().getName()+"\t max:"+max);
        int money = (int)(r.nextDouble() * max);
        money = money < MIN_GIFT_AMOUNT ? MIN_GIFT_AMOUNT : money;
        leftMoney.compareAndSet(leftAmount,leftAmount-money,stamp,stamp+1);
        peoples.getAndDecrement();
        return money;
    }
}

public class WeChantGift {


    public static void main(String[] args) {
        LeftMoneyPackage leftMoneyPackage = new LeftMoneyPackage();
        leftMoneyPackage.peoples = new AtomicInteger(3);
        leftMoneyPackage.leftMoney = new AtomicStampedReference<>(20000,1);
        System.out.println("分配红包金额"+Tool.fenToYuan(leftMoneyPackage.leftMoney.getReference()));
        System.out.println("分配红包人数"+leftMoneyPackage.peoples.intValue());

        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                final int amount = leftMoneyPackage.getRandomMoney();
                if(amount == 0){
                    System.out.println(Thread.currentThread().getName()+"\t 抢红包失败");
                }else{
                    System.out.println(Thread.currentThread().getName()+"\t 抢到金额："+Tool.fenToYuan(amount));
                }

            },String.valueOf(i)).start();
        }
    }

}
