package org.example.semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Scraper {
    public static void main(String[] args) {
        try(ExecutorService service = Executors.newCachedThreadPool()){
            for (int i = 0; i < 15; i++) {
                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        ScraperService.INSTANCE.scrape();
                    }
                });
            }
        }
    }
}

enum ScraperService {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3);

    public void scrape(){
        try{
            semaphore.acquire();
            runBot();
        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    private void runBot(){
        try{
            System.out.println("Scraping Data ..");
            Thread.sleep(2000);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
