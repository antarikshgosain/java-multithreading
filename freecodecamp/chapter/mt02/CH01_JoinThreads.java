package chapter.mt02;

public class CH01_JoinThreads {
    public static void main(String[] args) throws InterruptedException {
        final int COUNT = 5;
        Thread one = new Thread(() -> {
            for(int i=1; i<=COUNT; i++){
                System.out.println("Thread A: " + i);
            }
        });
        Thread two = new Thread(() -> {
            for(int i=1; i<=COUNT; i++){
                System.out.println("Thread B: " + i);
            }
        });
        one.start();
        two.start();
        System.out.println("Main thread started");
        //this is done by main thread
        //so there are total 3 threads
        one.join(); //informs JVM to work on other threads
        System.out.println("T1 is complete (joined with main thread)");
        two.join(); //informs JVM to work on other threads
        System.out.println("T2 is complete (joined with main thread)");
        System.out.println("Done executing ALL threads");
    }
}
