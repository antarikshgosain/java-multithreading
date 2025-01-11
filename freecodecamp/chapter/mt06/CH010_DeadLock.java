package chapter.mt06;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CH010_DeadLock {
    private final Lock lockA = new ReentrantLock(true);
    private final Lock lockB = new ReentrantLock(true);

    public void workerOne() {
        lockA.lock();
        System.out.println("Worker-One acquired lock-A");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        lockB.lock();
        System.out.println("Worker-One acquired lock-B");
        lockA.unlock();
        lockB.unlock();
    }


    public void workerTwo() {
        lockB.lock();
        System.out.println("Worker-Two acquired lock-A");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        lockA.lock();
        System.out.println("Worker-Two acquired lock-B");
        lockB.unlock();
        lockA.unlock();
    }

    public static void main(String[] args) {
        CH010_DeadLock demo = new CH010_DeadLock();

        new Thread(demo::workerOne,"Worker-One").start();
        new Thread(demo::workerTwo,"Worker-Two").start();

    }
}
