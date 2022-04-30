package com.himura;

public class TicketTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 建立三个线程模拟三个乘客，每个乘客购买30次
        new Thread(()->{for (int i=0;i<30;i++)  ticket.sale();},"A").start();
        new Thread(()->{for (int i=0;i<30;i++)  ticket.sale();},"B").start();
        new Thread(()->{for (int i=0;i<30;i++)  ticket.sale();},"C").start();


    }
}

// 临界资源类
class  Ticket {
    private  int num = 50;

    public synchronized void sale(){
        if(num > 0) {
            System.out.println(Thread.currentThread().getName() + "购买了第" + num-- + "张票，还剩" + num);
        }
    }
}
