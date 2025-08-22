package org.example.collections;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayDemo {
    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0));

        Thread reader1 = new Thread(new Reader(list, 500));
        Thread reader2 = new Thread(new Reader(list, 500));
        Thread reader3 = new Thread(new Reader(list,400));
        Thread reader4 = new Thread(new Reader(list,200));

        Thread writer1 = new Thread(new Writer(list, 1000));
        Thread writer2 = new Thread(new Writer(list,500));

        reader1.start();
        reader2.start();
        reader3.start();
        reader4.start();

        writer1.start();
        writer2.start();
    }
}


class Reader implements Runnable{
    private final List<Integer> list;
    private final int sleepTimer;

    public Reader(List<Integer> list, int sleepTimer) {
        this.list = list;
        this.sleepTimer = sleepTimer;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(sleepTimer);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            System.out.println(list);
        }
    }
}

class Writer implements Runnable{
    private final List<Integer> list;
    private final Random random;
    private final int sleepTimer;

    public Writer(List<Integer> list, int sleepTimer) {
        this.list = list;
        this.random = new Random();
        this.sleepTimer = sleepTimer;
    }

    @Override
    public void run() {
        while(true){
            try{
                Thread.sleep(sleepTimer);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
            list.set(random.nextInt(list.size()), random.nextInt(10));
        }
    }
}
