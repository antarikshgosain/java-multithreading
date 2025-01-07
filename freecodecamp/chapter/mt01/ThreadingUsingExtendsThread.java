package chapter.mt01;

public class ThreadingUsingExtendsThread {
    public static void main(String[] args) {
        Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        t1.start();
        t2.start();
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println(String.format("Thread A says: %d", i));
        }
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for(int i=0; i<100; i++){
            System.out.println(String.format("Thread B says: %d", i));
        }
    }
}