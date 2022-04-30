package com.himura.Runnable;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LiftOff implements Runnable{
    protected int countDown = 10;
    private static int taskCount = 0;
    // id用来区分线程的多个实例
    private final int id = taskCount++;
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    public String status() {
        return "#" + id + "(" +(countDown > 0 ? countDown : "LiftOff") + "),";
    }
    @Override
    public void run() {
        while(countDown-- >0) {
            System.out.println(status());
            // yield() 线程调度器，将CPU从一个线程转移给另一个线程。
            Thread.yield();
        }
    }
}
