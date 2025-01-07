package chapter.mt02;

public class JoinThreads {
    public static void main(String[] args) {
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
        System.out.println("Done executing");
        //this is done by main thread
        //so there are total 3 threads
    }
}
