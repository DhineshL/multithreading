package org.example.thread;

public class ExtendsThreadExample {
    public static void main(String[] args) {
        Thread one = new Thread1();
        Thread two = new Thread2();
        Thread three = new Thread3();

        one.start();
        two.start();
        three.start();
    }
}

class Thread1 extends Thread{
   public void run(){
       for (int i = 0; i < 5; i++) {
          System.out.println("Thread 1 "+ i);
       }
   }
}

class Thread2 extends Thread{
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 2 "+ i);
        }
    }
}
class Thread3 extends Thread{
    public void run(){
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread 3 "+ i);
        }
    }
}
