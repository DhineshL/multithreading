package org.example.threadSynchronization;

public class SynchronizationDemo {
    private static int counter =0;
    public static void main(String[] args) {
        Thread one = new Thread(()-> {
            for (int i = 0; i < 500; i++) {
                increment();
            }
        });

        Thread two = new Thread(()-> {
            for (int i = 0; i < 500; i++) {
                increment();
            }
        });

        one.start();
        two.start();

        try{
            one.join();
            two.join();
        }
        catch (InterruptedException ex){
            throw new RuntimeException();
        }

        System.out.println(counter);
    }

    private synchronized static void increment(){
        counter++;
    }
}
