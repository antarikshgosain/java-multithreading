package chapter.mt02;

public class CH042_With_Synchronization {
    private static int counter = 0 ;
    public static void main(String[] args) {
        Thread one = new Thread(() -> {
            for(int i=0; i<10_000; i++){
                increment1();
            }
        });
        Thread two = new Thread(() -> {
            for(int i=0; i<10_000; i++){
                increment2();
            }
        });
        one.start();
        two.start();
        try {
            one.join();
            two.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(String.format("Counter is %d", counter));
        // UNLESS SYNC BLOCK IS USED doesn't print 20_000 because of race condition
        // due to multiple threads working on shared resource
    }

    public static void increment1(){
        synchronized (CH042_With_Synchronization.class){
            counter++;
        }
    }
    public static synchronized void increment2(){
        synchronized (CH042_With_Synchronization.class){
            counter++;
        }
    }

}
