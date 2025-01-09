package chapter.mt05;


import java.util.concurrent.CountDownLatch;

public class CH020_CountdownLatch {
    public static void main(String[] args) throws InterruptedException {
        int numOfChefs = 3;
        CountDownLatch latch = new CountDownLatch(numOfChefs);
        new Thread(new Chef("Chef A","Tacos", latch)).start();
        new Thread(new Chef("Chef B","Burger", latch)).start();
        new Thread(new Chef("Chef C","Burrito", latch)).start();
        latch.await();
        System.out.println("All dishes are ready");
    }
}

class Chef implements Runnable {

    private final String name;
    private final String dish;
    private final CountDownLatch latch;

    Chef(String name, String dish, CountDownLatch latch) {
        this.name = name;
        this.dish = dish;
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            System.out.println(String.format("%s is preparing %s", name, dish));
            Thread.sleep(500);
            System.out.println(String.format("%s has finished preparing %s", name, dish));
            latch.countDown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}