package org.example.basics;

public class SequentialExecution {

    public static void demo1(){
        for (int i = 0; i < 5; i++) {
            System.out.println("demo 1 " + i);
        }
    }

    public static void demo2(){
        for (int i = 0; i < 5; i++) {
            System.out.println("demo 2 " + i);
        }
    }
    public static void main(String[] args) {
        demo1();
        demo2();
    }
}
