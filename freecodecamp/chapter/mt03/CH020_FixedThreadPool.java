package chapter.mt03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CH020_FixedThreadPool {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)){
            for(int i=0; i<5; i++){
                executorService.execute(new Work(i));
            }
        }
    }
}

class Work implements Runnable {

    private final int workId;

    Work(int workId) {
        this.workId = workId;
    }

    @Override
    public void run() {
        System.out.println(String.format("task Id %d being executed by Thread %s", workId, Thread.currentThread().getName()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
