package chapter.mt04;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CH010_CpuIntensiveTask {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println(String.format("#Core(s): %d", cores));
        ExecutorService service = Executors.newFixedThreadPool(cores);
        for (int i=0; i<20_000; i++){
            service.execute(new CpuTask(i));
        }
    }
}

class CpuTask implements Runnable {
    private int taskId ;
    CpuTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(String.format("CPU task %d being done by %s", taskId, Thread.currentThread().getName()));
    }
}