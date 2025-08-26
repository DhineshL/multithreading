package org.example.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class WorkLoadSplitterDemo {
    public static void main(String[] args) {
        try(ForkJoinPool pool = new ForkJoinPool()){
            WorkLoadSplitter demo = new WorkLoadSplitter(128);
            pool.invoke(demo);
        }
    }
}

class WorkLoadSplitter extends RecursiveAction{

    private final long workLoad;

    public WorkLoadSplitter(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
       if(workLoad>16) {
           System.out.println("Work Load too big! Thus splitting : "+ workLoad);
           long firstWorkload = workLoad/2;
           long secondWorkload = workLoad - firstWorkload;

           WorkLoadSplitter workload1 = new WorkLoadSplitter(firstWorkload);
           WorkLoadSplitter workload2 = new WorkLoadSplitter(secondWorkload);

           workload1.fork();
           workload2.fork();
       } else {
           try {
               Thread.sleep(2000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
           System.out.println("Work Load within limits! Task being executed for workload : "+ workLoad);
       }
    }
}