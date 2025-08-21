package org.example.executorService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/*
    ScheduledThreadPool awaitTermination method waits for 10000 milliseconds
    and checks if the service is complete. Here it is still running after 10s, it shutdowns manually.
    Also the awaitTermination blocks the thread

    Shutdown stops the processing of threads but previously queued tasks will be executed

 */
public class ScheduledExecutorDemo {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);

        try{
           if(!service.awaitTermination(10000, TimeUnit.MILLISECONDS)){
               service.shutdownNow();
           }
           System.out.println("I'm not blocked, i should run at the end");
        }catch (InterruptedException ex){
            service.shutdownNow();
        }
    }
}

class ProbeTask implements Runnable{
    @Override
    public void run() {
        System.out.println("Probing end points for updates...");
    }
}