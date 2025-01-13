package chapter.mt06;

import java.util.concurrent.atomic.AtomicInteger;

public class CH020_AtomicVariables {
    private static int count = 0;
    private static AtomicInteger atomicCount = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread threadOne = new Thread(() -> {
            for(int i=0; i<10_000; i++){
                count++;
                atomicCount.incrementAndGet();
            }
        });
        Thread threadTwo = new Thread(() -> {
            for(int i=0; i<10_000; i++){
                count++;
                atomicCount.incrementAndGet();
            }
        });
        threadOne.start();
        threadTwo.start();

        try {
            threadOne.join();
            threadTwo.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(String.format("Count is %d",count));
        System.out.println(String.format("Atomic Count is %d",atomicCount.get()));
    }
}
