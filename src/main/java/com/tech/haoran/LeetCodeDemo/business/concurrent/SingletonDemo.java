package com.tech.haoran.LeetCodeDemo.business.concurrent;

class ShareResourceCommon{
    //常规多线程下单例模式——1
    private static ShareResourceCommon shareResource = null;

    private ShareResourceCommon() {
        System.out.println(Thread.currentThread().getName()+"\t 常规方式资源创建");
    }

    public static synchronized ShareResourceCommon getResource(){
        if(shareResource ==null){
            shareResource = new ShareResourceCommon();
        }
        return shareResource;
    }
    //2. 枚举方式

    public static ShareResourceCommon getInstance(){
        return ShareResourceEnum.INSTANCE.getInstance();
    }
    enum ShareResourceEnum{
        INSTANCE;
        private ShareResourceCommon shareResourceEnum;
        ShareResourceEnum(){
            shareResourceEnum = new ShareResourceCommon();
            System.out.println(Thread.currentThread().getName()+"\t 枚举方式资源创建");

        }
        public ShareResourceCommon getInstance(){
            return shareResourceEnum;
        }
    }
    //3. 静态内部类。
    private static ShareResourceCommon resourceInner = new ShareResourceCommon();
    public static final ShareResourceCommon getResourceInner(){
        return resourceInner;
    }
    //4. 双层校验
    private static volatile ShareResourceCommon shareResourceVolatile;
    public static ShareResourceCommon getResourceVolatile(){
        if(shareResourceVolatile==null){
            synchronized (ShareResourceCommon.class){
                if(shareResourceVolatile==null){
                    shareResourceVolatile = new ShareResourceCommon();
                    System.out.println(Thread.currentThread().getName()+"\t 双层校验方式资源创建");
                }
            }
        }
        return shareResourceVolatile;
    }
}



public class SingletonDemo {

    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        for (int i = 0; i < 2000; i++) {
            new Thread(()->{
                //ShareResourceCommon.getResource();
                //ShareResourceCommon.getInstance();
                //ShareResourceCommon.getResourceInner();
                ShareResourceCommon.getResourceVolatile();
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){

        }
        System.out.println("执行完成，耗时："+(System.currentTimeMillis()-time)+" ms");

    }

}
