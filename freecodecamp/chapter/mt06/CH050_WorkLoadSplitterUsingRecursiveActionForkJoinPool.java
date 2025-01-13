package chapter.mt06;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class CH050_WorkLoadSplitterUsingRecursiveActionForkJoinPool {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.printf("\n#Cores: %d", cores);
        try (ForkJoinPool pool = new ForkJoinPool(cores)) {
            final int request = 512 ;
            WorkLoadSplitter splitter = new WorkLoadSplitter(request);
            pool.invoke(splitter);
        }
    }
}

class WorkLoadSplitter extends RecursiveAction {

    private final long workload ;

    WorkLoadSplitter(long workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (workload > 16) {
            System.out.printf("\nWorkLoad too big for thread %s, Splitting: %s", Thread.currentThread().getName(), workload);
            long firstWorkLoad = workload / 2 ;
            long secondWorkLoad = workload - firstWorkLoad ;
            WorkLoadSplitter firstSplit = new WorkLoadSplitter(firstWorkLoad);
            WorkLoadSplitter secondSplit = new WorkLoadSplitter(secondWorkLoad);
            firstSplit.fork();
            secondSplit.fork();
        } else {
            System.out.printf("\nWorkload within limits for thread %s, Processing %s",Thread.currentThread().getName(), workload);
        }
    }
}
