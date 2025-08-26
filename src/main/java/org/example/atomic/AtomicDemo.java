package org.example.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    private static int count = 0;

    private static final AtomicInteger counter = new AtomicInteger();

    public static void main(String[] args) {
        Thread one = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });

        Thread two = new Thread(()->{
            for (int i = 0; i < 1000; i++) {
                counter.incrementAndGet();
            }
        });
        one.start();
        two.start();

        try{
            one.join();
            two.join();
        } catch (InterruptedException e){
            throw new RuntimeException();
        }

        System.out.println("Count value :"+counter);
    }
}
