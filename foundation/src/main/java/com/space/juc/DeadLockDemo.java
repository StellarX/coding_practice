package com.space.juc;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Description  死锁示例程序
 * @author space
 */
class Res implements Runnable{
    private final String lock1;
    private final String lock2;
    Res(String lock1, String lock2){
        this.lock1 = lock1;
        this.lock2 = lock2;
    }
    @SneakyThrows
    public void run(){
        synchronized (lock1){
            System.out.println(Thread.currentThread().getName() + " get " + lock1);
            TimeUnit.SECONDS.sleep(2);
            System.out.println(Thread.currentThread().getName() + " try to get " + lock2);
            synchronized (lock2){
                System.out.println(Thread.currentThread().getName() + " get!!! " + lock2);
            }
        }
    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        new Thread(new Res(lock1, lock2)).start();
        new Thread(new Res(lock2, lock1)).start();
    }

}
