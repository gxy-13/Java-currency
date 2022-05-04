package com.himura.daemon;

import java.util.concurrent.TimeUnit;

/**
 * Daemon 守护线程，在程序运行中在后台提供一种通用服务的线程，并且这种线程并不属于程序中不可或缺的部分
 * 因此，在所有的非后台线程结束时，程序也就终止了，同时会杀死进程中的所有后台线程
 *
 * 下例中，main线程就是非后台线程，当main线程结束之后会杀死所有的守护进程。
 */
public class SimpleDaemons implements  Runnable{
    @Override
    public void run() {
        try {
            while(true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            // 在调用start()之前必须要设置
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("All daemons started");
        TimeUnit.MILLISECONDS.sleep(1);
    }
}
