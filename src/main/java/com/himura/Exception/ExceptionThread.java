package com.himura.Exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 下例中，run方法总是抛出一个错误，并会传播到run方法的外部，main()展现了运行它时的结果
 *  实际上，在线程死亡之前，异常会传递到一个用于处理未捕获异常的处理器
 *  这个处理器必须属于一个实现了Thread.UncaughtExceptionHandler接口的类
 *  可以用setUncaughtExceptionHandler()为任何线程安装一个处理器。
 *  也可以用Thread类中的静态方法setDefaultUncaughtExceptionHandler为所有线程安装一个默认的处理器
 */
public class ExceptionThread implements Runnable{
    @Override
    public void run() {
        throw new RuntimeException();
    }
    public static void main(String[] args) {
        // 即使将main线程主体用try catch finally 包裹住也是没有用处的
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ExceptionThread());
        executorService.shutdown();
    }
}
