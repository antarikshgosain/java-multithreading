package chapter.mt02;


public class CH050_WaitAndNotify {

    private static final Object LOCK = new Object();

    public static void main(String[] args) {


        Thread one = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread two = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        one.start();
        two.start();

    }

    private static void one() throws InterruptedException {
        synchronized (LOCK) {
            System.out.println("one() says \"hello\"");
            LOCK.wait();
            System.out.println("one() says \"back again\"");
        }
    }
    private static void two() throws InterruptedException {
        synchronized (LOCK) {
            System.out.println("two() says \"hello\"");
            LOCK.notify();
            System.out.println("two() says \"back again (after notifying)\"");
        }
    }
}
