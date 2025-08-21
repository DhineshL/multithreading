package org.example.callable;

import java.util.concurrent.*;

public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        try(ExecutorService service = Executors.newFixedThreadPool(2)){
            Future<Integer> result = service.submit(new ReturnValueTask());

            result.cancel(true);

            System.out.println("Is Done: "+result.isDone());

            System.out.println("Is Cancelled: "+result.isCancelled());

            System.out.println(result.get(8,TimeUnit.SECONDS));
            System.out.println("Main thread execution completed");
        }

    }
}

class ReturnValueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        Thread.sleep(2000);
        return 42;
    }
}
