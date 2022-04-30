package com.himura;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerCustomer2 {
    public static void main(String[] args) {
        Good2 good2 = new Good2();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                good2.produce();
            }
        },"producer").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                good2.buy();
            }
        },"customer").start();
    }
}

class Good2 {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        // 等待
        try {
            while (num != 0) {
                condition.await();
            }
            num++;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+"=>"+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void buy() {
        lock.lock();
        try {
            while (num == 0) {
                condition.await();
            }
            num--;
            condition.signalAll();
            System.out.println(Thread.currentThread().getName()+"=>"+num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
