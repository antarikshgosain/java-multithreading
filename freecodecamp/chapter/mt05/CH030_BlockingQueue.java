package chapter.mt05;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CH030_BlockingQueue {
    static final int QUEUE_CAPACITY = 10;
    static BlockingQueue<Integer> taskQueue = new ArrayBlockingQueue<>(QUEUE_CAPACITY);

    public static void main(String[] args) {
        Thread producerThread = new Thread(()->{

            try {
                for (int i=0; i<20; i++){
                    taskQueue.put(i);
                    System.out.println(String.format("Task Produced: %d",i));
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumerThreadOne = new Thread(() -> {
           try {
               while (true) {
                   int task = taskQueue.take();
                   processTask(task, "ConsumerThread-1");
               }
           } catch (InterruptedException e){
               throw new RuntimeException();
           }
        });
        Thread consumerThreadTwo = new Thread(() -> {
            try {
                while (true) {
                    int task = taskQueue.take();
                    processTask(task, "ConsumerThread-2");
                }
            } catch (InterruptedException e){
                throw new RuntimeException();
            }
        });

        producerThread.start();
        consumerThreadOne.start();
        consumerThreadTwo.start();

    }

    private static void processTask(int task, String threadName) throws InterruptedException {
        System.out.println(String.format("Task %d being processed by %s", task, threadName));
        Thread.sleep(1000);
        System.out.println(String.format("Task %d consumed by %s", task, threadName));
    }
}
