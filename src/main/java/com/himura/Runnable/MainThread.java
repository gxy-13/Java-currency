package com.himura.Runnable;

public class MainThread {
    public static void main(String[] args) {
        // 实现runnable接口并且重写run()方法，使得这个任务可以执行命令
        // 要想实现线程行为，必须将任务显式的附着到一个线程上
        // 这个例子中使用了实在main线程中直接调用的。
        // Java有两个线程，main线程和gc线程
        LiftOff liftOff = new LiftOff();
        liftOff.run();
    }
}
