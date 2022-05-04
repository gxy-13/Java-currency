package com.himura.Exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool(new HandlerThreadFactory());
        executorService.execute(new ExceptionThread2());
    }
}
class ExceptionThread2 implements  Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by" + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught" + e);
    }
}
class HandlerThreadFactory implements ThreadFactory {
    // 在每一个新建的Thread对象上附着一个Thread.UncaughtExceptionHandler
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + "create new Thread");
        Thread thread = new Thread(r);
        System.out.println("create "+ thread);
        thread.setUncaughtExceptionHandler( new MyUncaughtExceptionHandler());
        System.out.println("eh = " + thread.getUncaughtExceptionHandler());
        return  thread;
    }
}
