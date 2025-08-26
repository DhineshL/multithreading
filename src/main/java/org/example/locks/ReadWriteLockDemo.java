package org.example.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class SharedResource {
    private int counter= 0;
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    public void write(){
        lock.writeLock().lock();
        try{
            counter++;
            System.out.println("Writer Lock wrote: "+counter);
        } finally {
        lock.writeLock().unlock();
        }
    }

    public void read(){
        lock.readLock().lock();
        try{
            System.out.println("Read Lock read: "+counter);
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        for (int i = 0; i < 2; i++) {
           new Thread(()->{
               for (int j = 0; j < 2; j++) {
                   resource.read();
               }
           }).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    resource.write();
                }
            }).start();
        }

    }
}
