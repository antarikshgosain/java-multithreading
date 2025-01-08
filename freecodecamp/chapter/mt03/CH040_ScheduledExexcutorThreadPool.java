package chapter.mt03;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class CH040_ScheduledExexcutorThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, MILLISECONDS);

        // Shutdown logic for ScheduledExecutorService
        try {
            if(!executorService.awaitTermination(5000, MILLISECONDS)){
                executorService.shutdownNow(); // less graceful
            }
        } catch (InterruptedException e) {
            executorService.shutdown(); // more graceful
            throw new RuntimeException(e);
        }
    }
}


class ProbeTask implements Runnable {
    @Override
    public void run() {
        System.out.println(String.format("Probing for updates by %s", Thread.currentThread().getName()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}