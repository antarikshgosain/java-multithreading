package chapter.mt05;

import java.util.concurrent.Exchanger;

public class CH060_ExchangerDemo {

}

class ThreadOne implements Runnable {
    private final Exchanger<Integer> exchanger;

    ThreadOne(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int dataToSend = 10;
        System.out.println("First thread is sending data: " + dataToSend );
        try {
            Integer receivedData = exchanger.exchange(dataToSend);
            System.out.println("First Thread Received: " + receivedData );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ThreadTwo implements Runnable {
    private final Exchanger<Integer> exchanger;

    ThreadTwo(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000); // additional step
            int dataToSend = 20;
            System.out.println("Second thread is sending data: " + dataToSend );
            Integer receivedData = exchanger.exchange(dataToSend);
            System.out.println("Second Thread Received: " + receivedData );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread one = new Thread(new ThreadOne(exchanger));
        Thread two = new Thread(new ThreadTwo(exchanger));

        one.start();
        two.start();

    }
}
