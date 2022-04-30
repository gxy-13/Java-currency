package com.himura.Thread;

import com.himura.Runnable.LiftOff;

public class BasicThread {
    public static void main(String[] args) {
        // 用Thread类创建线程需要将一个runnable对象传递给他，调用start()为线程执行必须的初始化操作
        LiftOff liftOff = new LiftOff();
        // 从输出结果中可以看出，start()迅速地返回了，在倒计时出现之前就输出了 waiting for liftoff
        // 因为调用start() 实际上产生的是对Runnable对象的run()调用，并且这个方法还没有完成。
        // 还因为listOff.run()是新建的线程去执行的，因此仍旧可以执行main()线程中的其它操作
        new Thread(liftOff).start();
        System.out.println("waiting  for liftoff");
    }
}
