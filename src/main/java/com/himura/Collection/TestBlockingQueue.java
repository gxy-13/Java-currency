package com.himura.Collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class TestBlockingQueue {
    // blockingQueue 接口的几个实现类 LinkedBlockingQueue 容量没有上界，是一个双端队列 ArrayBlockingQueue 需要指定容量，
    // 物品阻塞队列
    private static ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<String>(5);
    public static void main(String[] args) {
        new Thread(()->{
            System.out.println("生产者来了");
        },"生产者").start();
        new Thread(()->{
            try {
                // take 返回阻塞队列队头元素，如果队列为空，则阻塞
//                String s = (String) blockingQueue.take();
                // poll(时间，单位) 等待一定时间，如果没有获得就返回null，所以需要对返回值进行判断
                // poll() 不等待
                // =====================================
                // 相对应的三个添加元素的方法
                //  put(E)   会一直等，等到有空位
                // offer(E,时间,单位)  等一段时间,返回Boolean
                // offer(E) 不等，直接返回Boolean
                String s = (String) blockingQueue.poll(1,TimeUnit.SECONDS);
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("消费者来消费了");
        },"消费者").start();
    }
}
