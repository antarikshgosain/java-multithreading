package chapter.mt04;

import java.util.concurrent.*;

public class CH020_CallableReturnsValue {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            Future<Integer> result = executorService.submit(new ReturnValueTask());
            System.out.println(result.get());
        }
    }
}

class ReturnValueTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 12;
    }
}