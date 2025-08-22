package org.example.threadSynchronization;

import java.util.concurrent.Exchanger;

public class ExchangerDemo {
    public static final Exchanger<Integer> exchanger = new Exchanger<>();
    public static void main(String[] args) {
        Thread one = new Thread(new FirstThread(exchanger));
        Thread two = new Thread(new SecondThread(exchanger));
//        Thread three = new Thread(new SecondThread(exchanger));

        one.start();
        two.start();
//        three.start();
    }
}

class FirstThread implements Runnable{
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int dataToSend = 69;
        try {
            int receivedData = exchanger.exchange(dataToSend);
            System.out.println("Received data from thread one "+receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class SecondThread implements Runnable{
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int dataToSend = 42;
        try {
            Thread.sleep(1000);
            int receivedData = exchanger.exchange(dataToSend);
            System.out.println("Received data from thread two "+receivedData);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
