package org.example.collections;

import java.util.concurrent.CountDownLatch;

/*
    CountDownLatch awaits till all the other threads finishes the execution or
    latch.countDown()
 */

public class Restaurant {
    public static void main(String[] args) throws InterruptedException {
        int numberOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numberOfChefs);
        new Thread(new Chef("Chef A", "Pizza", latch)).start();
        new Thread(new Chef("Chef B", "Pasta", latch)).start();
        new Thread(new Chef("Chef C", "Salad", latch)).start();

        latch.await();

        System.out.println("All dishes are ready! Let's serve the dishes");
    }
}

class Chef implements Runnable{

    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    public Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
       try {
          System.out.println(name + " is preparing "+ dish);
          Thread.sleep(2000);
          System.out.println(name + " has finished preparing "+ dish);
          latch.countDown();
       } catch(InterruptedException ex) {
           throw new RuntimeException(ex);
       }
    }
}
