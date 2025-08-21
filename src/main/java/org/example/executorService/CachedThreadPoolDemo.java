package org.example.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
    CachedThreadPool auto-scales thread.
    The queue contains only one task at a given time.
    Idle threads of 60s are removed.
    Good for many short and bursty threads

 */
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newCachedThreadPool()){
            for (int i = 0; i < 1000; i++) {
               service.execute(new TaskOne(i));
            }
        }

        System.out.println("Execution Completed");
    }
}

class TaskOne implements Runnable{
    private final int taskId;

    public TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task "+ taskId+" being executed by "+ Thread.currentThread().getName());
    }
}
