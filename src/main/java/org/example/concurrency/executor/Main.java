package org.example.concurrency.executor;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
/*
        ExecutorService service = Executors.newFixedThreadPool(3);
        Message message = new Message("");

        service.execute(new Waiter(message, "waiter"));
        service.execute(new Waiter(message, "waiter1"));

        service.execute(new Notifier(message,"notifier"));
        System.out.println("Стартовали все потоки");
        service.shutdown();
*/
        //service.execute(new Waiter(message, "waiter3"));

/*        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(2);
        scheduledService.scheduleAtFixedRate(() -> {
            System.out.println("now " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")));
        }, 0, 1, TimeUnit.SECONDS);*/

        ExecutorService service = Executors.newFixedThreadPool(3);
        Future<Integer> future = service.submit(new Calc(2, 10));
        System.out.println("before future get");
        System.out.println(future.get());
        System.out.println("after future get");
    }
}
