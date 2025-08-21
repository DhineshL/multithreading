package org.example.threadSynchronization;

public class LockWithCustomObjects {
    private static int counter1 = 0;
    private static int counter2 = 0;
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();
    public static void main(String[] args) {
        Thread one = new Thread(()-> {
            for (int i = 0; i < 500; i++) {
                increment1();
            }
        });

        Thread two = new Thread(()-> {
            for (int i = 0; i < 500; i++) {
                increment2();
            }
        });

        one.start();
        two.start();

        try{
            one.join();
            two.join();
        } catch (Exception ex){
            throw new RuntimeException();
        }
        System.out.println("Counter 1 "+ counter1 + " Counter 2 "+counter2);
    }
    private synchronized static void increment1(){
        synchronized (lock1){
            counter1++;
        }
    }
    private synchronized static void increment2(){
        synchronized (lock2){
            counter2++;
        }
    }
}
