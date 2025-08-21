package org.example.thread;

public class ThreadPriorityExample {
    public static void main2(String[] args) {
        System.out.println(Thread.currentThread().getName() + "  says hi");

        Thread one = new Thread(()-> {
            System.out.println("Thread one says hi as well !");
        });

        one.setPriority(Thread.MAX_PRIORITY);
        one.start();
        System.out.println(Thread.currentThread().getName() + "  says hi");
    }

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();

        Thread t = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Child Thread");
            }
        });
        t.setPriority(Thread.MAX_PRIORITY);
        t.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("Main Thread");
        }
    }

}
