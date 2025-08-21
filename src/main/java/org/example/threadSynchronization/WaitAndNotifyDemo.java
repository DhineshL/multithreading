package org.example.threadSynchronization;

public class WaitAndNotifyDemo {
    private static final Object LOCK = new Object();
    public static void main(String[] args) {
        Thread one = new Thread(()->{
            try {
                one();
            } catch (InterruptedException ex){
                throw new RuntimeException();
            }
        });

        Thread two = new Thread(()->{
            try {
                two();
            } catch (InterruptedException ex){
                throw new RuntimeException();
            }
        }
        );

        one.start();
        two.start();
    }


    private static void one() throws InterruptedException{
        synchronized (LOCK){
            System.out.println("Hello from Method one ...");
            LOCK.wait();
            System.out.println("Back again in the method one");
        }
    }

    private static void two() throws InterruptedException{
        synchronized (LOCK){
            System.out.println("Hello from method two ...");
            LOCK.notify();
            System.out.println("Back again in method two ");
        }
    }
}
