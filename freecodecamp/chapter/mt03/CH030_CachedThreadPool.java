package chapter.mt03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CH030_CachedThreadPool {
    public static void main(String[] args) {
        try (ExecutorService executorService = Executors.newCachedThreadPool()){
            for(int i=0; i<50_000; i++){
                executorService.execute(new Work(i));
            }
        }
    }
}

class TaskOne implements Runnable {

    private final int taskId;

    TaskOne(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(String.format("task Id %d being executed by Thread %s", taskId, Thread.currentThread().getName()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
