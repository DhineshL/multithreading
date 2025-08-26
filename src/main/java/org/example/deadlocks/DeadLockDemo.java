package org.example.deadlocks;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo {
    private final Lock lockA = new ReentrantLock(true);
    private final Lock lockB = new ReentrantLock(true);

    public void threadOne(){
        lockA.lock();
        System.out.println("Acquired LockA");
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            throw new RuntimeException();
        }
        lockB.lock();
        System.out.println("Acquired LockB");
        lockA.unlock();
        lockB.unlock();
    }

    public void threadTwo(){
        lockB.lock();
        System.out.println("Acquired LockB");
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            throw new RuntimeException();
        }
        lockA.lock();
        System.out.println("Acquired LockA");
        lockB.unlock();
        lockA.unlock();
    }

    public static void main(String[] args) {
        DeadLockDemo demo = new DeadLockDemo();
        new Thread(demo::threadOne, "worker A").start();
        new Thread(demo::threadTwo, "worker B").start();

        new Thread(()->{
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while (true){
                long[] threads = mxBean.findDeadlockedThreads();
                if(threads!=null) {
                    System.out.println("DeadLocks are found");
                    for (long t : threads) {
                        System.out.println("Dead lock thread " + t);
                    }
                    break;
                }
                try{
                    Thread.sleep(2000);
                } catch (Exception e){
                    throw new RuntimeException();
                }
            }
        }).start();
    }

}
