package org.example.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SearchOccurrenceTask extends RecursiveTask<Integer> {
    private int start;
    private int end;
    private int searchElement;
    private int [] arr;

    public SearchOccurrenceTask(int start, int end, int searchElement, int[] arr) {
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
        this.arr = arr;
    }

    @Override
    protected Integer compute() {
        if(this.end-this.start+1 > 50){
            int mid = (start+end)/2;
            SearchOccurrenceTask task1 = new SearchOccurrenceTask(start, mid, searchElement, arr);
            SearchOccurrenceTask task2 = new SearchOccurrenceTask(mid,end, searchElement, arr);

            task1.fork();
            task2.fork();

            return task1.join() + task2.join();
        } else{
            return search();
        }
    }

    private Integer search(){
        int count = 0;
        try{
            for (int i = 0; i < end; i++) {
                Thread.sleep(10);
                if(arr[i] == searchElement) {
                    count++;
                }
            }
        } catch (InterruptedException ex){
            throw new RuntimeException(ex);
        }
        return count;
    }
}

class FJPDemo {
    public static void main(String[] args) {
        int [] arr = new int [1000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10) +1;
        }

        int searchElement = random.nextInt(10) + 1;

        try(ForkJoinPool pool = new ForkJoinPool()) {
            SearchOccurrenceTask task = new SearchOccurrenceTask(0, arr.length,searchElement,arr);
            Integer occurrence = pool.invoke(task);
            System.out.println("Array is : "+ Arrays.toString(arr));
            System.out.printf("%d found %d times", searchElement,occurrence);
        }
    }
}
