package com.himura.Executor;

import java.util.concurrent.Callable;

// Runnable是执行工作的独立任务，但是没有返回值，如果希望任务在完成时有返回值，实现Callable接口
// 执行Callable 的一种方法是使用FutureTask 它实现了Future 和Runnable 接口，更常见的是将Callable传递给一个执行器
public class TaskWithResult implements Callable {
    private int id;
    public TaskWithResult(int id) {
        this.id = id;
    }
    @Override
    public Object call() throws Exception {
        return "result of TaskWithResult" + id;
    }
}
