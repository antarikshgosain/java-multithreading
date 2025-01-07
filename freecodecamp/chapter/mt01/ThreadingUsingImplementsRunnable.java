package chapter.mt01;

public class ThreadingUsingImplementsRunnable {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadOne());
        Thread two = new Thread(new ThreadTwo());
        Thread three = new Thread(() -> {
            for(int i=0; i<100; i++){
                System.out.println("Thread C: " + i);
            }
        });

        one.start();
        two.start();
        three.start();

    }
}

class ThreadOne implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println("Thread A: " + i);
        }
    }
}
class ThreadTwo implements Runnable{

    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println("Thread B: " + i);
        }
    }
}