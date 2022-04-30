package com.himura.Executor;

import com.himura.Runnable.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        // Executor执行器，Executors类中有许多静态工厂方法，用来构造线程池。会管理Thread对象，简化了并发编程。
        // ExecutorService 是具有服务生命周期的Executor，知道如何构建恰当的上下文来执行Runnable对象
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            executorService.execute(new LiftOff());
        // 防止新任务被提交到Executor中
        executorService.shutdown();
        /**
         *  Executors 中的静态工厂方法
         *  newCachedThreadPool 必要时创建新县城，空闲线程会保留60s 有空间就用，没有空闲就创建一个新线程
         *  newFixedThreadPool 池中包含固定数目的线程；空闲线程会一直保留  如果提交的任务数多于空闲线程数，就把为得到服务的任务放到队列中。当其他任务完成之后在运行这些排队的任务
         *  newWorkStealingPool 一种适合 fork-join 任务的线程池，其中复杂的任务会分解为更简单的任务，空闲线程会密取较简单的任务
         *  newScheduledThreadExecutor 只有一个线程的池，会顺序执行所提交的任务。 大小为1的线程池，由一个线程顺序执行所提交的任务。
         *  newScheduledThreadPool 用于调度执行的固定线程池
         *  newSingleThreadScheduledExecutor 用于调度执行的单线程池
         */
    }
}
