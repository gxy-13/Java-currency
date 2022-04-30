package com.himura;

public class ProducerCustomer {

    public static void main(String[] args) {
        Good good = new Good();
        new Thread(()->{
             for (int i = 0; i < 10; i++) {
                 try {
                     good.produce();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
         }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    good.buy();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class Good {
    private int num = 0;

    public synchronized void produce() throws InterruptedException {
        /**
            if(num != 0) this.wait();
            System.out.println("生产者生产了"+(++num)+"件物品");
            // 唤醒其它所有线程
            this.notifyAll();
            使用if会导致虚假唤醒，在大于两个线程时，this.notify会唤醒多余的进程，使得商品数目大于1.所以使用while代替if更好
         */
        while (num != 0 ) this.wait();
        System.out.println("生产者生产了"+(++num)+"件物品");
        this.notifyAll();
    }

    public synchronized  void buy() throws InterruptedException {
        /**
         *  if(num == 0) this.wait();
         *  System.out.println("消费者购买了"+num+"件物品");
         *  num--;
         *  this.notifyAll();
         */
        while(num == 0) this.wait();
        System.out.println("消费者购买了"+num+"件物品");
        num--;
        this.notifyAll();
    }
}
