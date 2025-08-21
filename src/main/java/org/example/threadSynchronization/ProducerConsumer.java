package org.example.threadSynchronization;

import java.util.ArrayList;
import java.util.List;

/*
    1) Thread releases a lock in two scenarios
        a) When it exists synchronized block
        b) When wait is called on the thread

    Code Flow
    1) Produces starts populating container till it reaches top size.
    2) While populating it notifies the other thread to be ready.
    3) Once it reached the top size, it calls wait. This releases the lock and moves the thread to wait status
    4) Then the consumer starts to consume the message, since it can acquire the lock now.
    5) It consumes till it reaches the bottom and calls wait
 */

public class ProducerConsumer {
    public static void main(String[] args) {
        Worker worker = new Worker(5,0);
        Thread producer = new Thread(()-> {
            try{
                worker.produce();
            } catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });

        Thread consumer = new Thread(()->{
            try {
                worker.consumer();
            } catch (InterruptedException ex){
                throw new RuntimeException(ex);
            }
        });

        producer.start();
        consumer.start();

    }
}

class Worker {
    private int sequence = 0;
    private final Integer top;
    private final Integer bottom;
    private final List<Integer> container;
    private final Object lock = new Object();


    public Worker(Integer top, Integer bottom) {
        this.top = top;
        this.bottom = bottom;
        this.container = new ArrayList<>();
    }

    public void produce() throws InterruptedException{
        synchronized (lock){
            while(true){
                if(container.size()==top){
                    System.out.println("container full, waiting for items to be removed...");
                    lock.wait();
                } else {
                    System.out.println(sequence + " added to the container");
                    container.add(sequence++);
                    lock.notify();
                }
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock){
            while(true){
                if(container.size()==bottom){
                    System.out.println("Container empty, waiting for items to be added ...");
                    lock.wait();
                } else{
                    System.out.println(container.remove(0) + " removed from the container");
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
