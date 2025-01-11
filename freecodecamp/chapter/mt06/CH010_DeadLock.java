package chapter.mt06;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CH010_DeadLock {
    private final Lock lockA = new ReentrantLock(true);
    private final Lock lockB = new ReentrantLock(true);

    public void workerOne() {
        lockA.lock();
        System.out.println("Worker-One acquired lock-A");
        try {
            Thread.sleep(2000);
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
        System.out.println("Worker-Two acquired lock-B");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        lockA.lock();
        System.out.println("Worker-Two acquired lock-A");
        lockB.unlock();
        lockA.unlock();
    }

    public static void main(String[] args) {
        CH010_DeadLock demo = new CH010_DeadLock();
        boolean customDebug = true;

        new Thread(demo::workerOne,"Worker-One").start();
        new Thread(demo::workerTwo,"Worker-Two").start();

        new Thread(() -> {
            ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
            while (true) {
                long[] deadLockedThreadIds  = mxBean.findDeadlockedThreads();
                long[] allThreadIds         = mxBean.getAllThreadIds();
                if (customDebug && null != allThreadIds && allThreadIds.length > 0 ) {
                    for (long tid : allThreadIds) {
                        System.out.println(String.format("Details about %d \n%s",tid,mxBean.getThreadInfo(tid)));
                    }
                }
                if (null != deadLockedThreadIds && deadLockedThreadIds.length > 0 ) {
                    System.out.println("Dead Locks Detected!");
                    for(long tid : deadLockedThreadIds) {
                        System.out.println(String.format("DeadLock! Details about %d : %s",tid,mxBean.getThreadInfo(tid)));
                    }
                    break; // remove this break for continuing infinite while loop
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


    }
}
