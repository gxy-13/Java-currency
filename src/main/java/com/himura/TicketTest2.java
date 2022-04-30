package com.himura;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock接口的实现类，reentrantLock 可重入锁非公平，可以在创建实例时添加一个bool类型的参数变成公平锁
 * 公平锁，先来后到 ，有时效率会低
 * 非公平锁，可以插队，
 */
public class TicketTest2 {
    public static void main(String[] args) {
        Tick tick = new Tick();
        new Thread(()->{ for(int i=0;i<30;i++) tick.sale();},"A").start();
        new Thread(()->{ for(int i=0;i<30;i++) tick.sale();},"B").start();
        new Thread(()->{ for(int i=0;i<30;i++) tick.sale();},"C").start();
    }

}
class Tick {
    private int num = 50;
    /**
     * 使用lock 的三步骤
     * 1.创建lock实例
     * 2.给临界资源上锁
     * 3.解锁
     */
    Lock lock = new ReentrantLock();

    public void sale() {

        try {
            // 上锁
            lock.lock();
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "购买了第" + num-- + "张票，还剩" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 开锁
            lock.unlock();
        }

    }
}
/**
 * synchronized 和 lock 的区别
 * 1. synchronized 是一个关键字， lock 是一个接口
 * 2. synchronized 自动上锁，lock 手动上锁
 * 3. synchronized 自动解锁，lock 手动解锁，如果不解锁会引发死锁
 * 4. synchronized 可重入锁，非公平，lock 可重入锁，可以自己选择公平还是非公平
 * 5. synchronized 遇到阻塞，会一直等，lock 遇到阻塞可以通过tryLock()去获取锁，不会一直等
 * 6. synchronized 适合用于少量代码的同步问题，lock适用于大量代码的同步问题
 */
