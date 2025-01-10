package chapter.mt05;

import java.util.concurrent.locks.ReentrantLock;

public class CH090_ReentrantLock {
    private final ReentrantLock lock = new ReentrantLock();
    private int sharedData = 0;

    // methodA() will acquire lock to work on shared data
    public void methodA() {
        lock.lock();
        lock.isFair();
        try {
            // Critical Section
            sharedData++;
            System.out.println("methodA() updated shared data to: "+sharedData);
            methodB(); // methodB() also requires the lock
        } finally {
            lock.unlock();
        }
    }

    private void methodB() {
        lock.lock();
        try {
            // Critical Section
            sharedData--;
            System.out.println("methodB() updated shared data to: "+sharedData);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        CH090_ReentrantLock demo = new CH090_ReentrantLock();

        for (int i=0; i<5; i++) {
            new Thread(demo::methodA).start();
        }
    }
}
