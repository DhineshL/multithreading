package org.example.thread;

public class RunnableThreadExample {

    public static void main(String[] args) {
            Thread one = new Thread(new ThreadOne());
            Thread two = new Thread(new ThreadTwo());
            Thread three = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                   System.out.println("Thread 3 "+ i);
                }
            });
            one.start();
            two.start();
            three.start();
    }
}

class ThreadOne implements Runnable{
    public void run (){
        for (int i = 0; i < 5; i++) {
           System.out.println("thread 1 "+ i);
        }
    }
}

class ThreadTwo implements Runnable{
    public void run (){
        for (int i = 0; i < 5; i++) {
            System.out.println("thread 2 "+ i);
        }
    }
}
