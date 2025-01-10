package chapter.mt05;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CH100_SharedResourceUsingReadWriteLock {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // Reader Threads Impl
        for(int i=0; i<5; i++) {
            Thread readerThread = new Thread(() -> {
               for (int j=0; j<5; j++) {
                   sharedResource.getValue();
               }
            });
            readerThread.setName("reader-thread-"+(i+1));
            readerThread.start();
        }

        // Writer Thread Impl
        Thread writerThread = new Thread(() -> {
            for (int i=0; i<5; i++){
                sharedResource.increment();
            }
        });
        writerThread.setName("writer-thread");
        writerThread.start();

    }
}
class SharedResource {
    private int counter = 0;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void increment() {
        readWriteLock.writeLock().lock();
        try {
            counter++;
            String threadName = Thread.currentThread().getName();
            System.out.println(String.format("%s writes %d", threadName, counter));
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void getValue() {
        readWriteLock.readLock().lock();
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(String.format("%s reads %d", threadName, counter));
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
