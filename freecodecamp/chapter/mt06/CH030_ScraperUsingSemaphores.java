package chapter.mt06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class CH030_ScraperUsingSemaphores {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i=0; i<15; i++) {
                System.out.println(String.format("Running Task: %d",i));

                service.execute(new Runnable() {
                    @Override
                    public void run() {
                        ScrapeService.INSTANCE.scrape();
                    }
                });
            }
        }
    }
}

enum ScrapeService {
    INSTANCE;
    private int numPermits = 3; // maximum #permit(s) available for thread(s)
    private Semaphore semaphore = new Semaphore(numPermits);
    void scrape() {
        try {
            semaphore.acquire();
            invokeScrapeBot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }


    private void invokeScrapeBot() { // to mock the behaviour of 3rd party scrapper
        String threadName = Thread.currentThread().getName();
        long threadId = Thread.currentThread().threadId();
        System.out.println(String.format("%s (Thread Id: %d) is Scraping Data", threadName,threadId));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}