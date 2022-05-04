package com.himura;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
    public static void main(String[] args) {
        Good3 good3 = new Good3();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                good3.produce();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                good3.sale();
            }
        }).start();
    }
}
class Good3 {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void produce() {
        lock.lock();
        try {
            while(number != 0) {
                condition.await();
            }
            number ++;
            System.out.println("生产者生产了"+ number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sale() {
        lock.lock();
        try {
            while(number==0) {
                condition.await();
            }
            number --;
            System.out.println("消费者购买了"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
