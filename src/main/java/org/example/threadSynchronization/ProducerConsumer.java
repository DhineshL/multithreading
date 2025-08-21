package org.example.threadSynchronization;

import java.util.ArrayList;
import java.util.List;

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
