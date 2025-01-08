package chapter.mt03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CH010_SingleExecutorThreadPool {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i=0; i<5; i++){
            executorService.execute(new Task(i));
        }
        Task t1= new Task(-1);
        t1.run(); //this will be run by main
    }
}

class Task implements Runnable{
    private final int taskId;
    Task(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(String.format("Task Id %d being executed by Thread %s", taskId, Thread.currentThread().getName()));
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}