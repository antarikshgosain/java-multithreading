package chapter.mt05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CH050_CyclicBarrier_MultiStageTour {
    // group of tourists
    // tour has stages
    // tourists will regroup at the end of stage and move together to next stage with the tour guide

    private final static int NUM_TOURISTS = 5;
    private final static int NUM_STAGES = 3;
    private final static CyclicBarrier barrier = new CyclicBarrier(NUM_TOURISTS, () -> {
        System.out.println("Tour guide starts speaking...");
    });
    // NUM_TOURISTS determine how many threads are to be returned

    static class Tourists implements Runnable {
        private final int touristId ;

        Tourists(int touristId) {
            this.touristId = touristId;
        }

        @Override
        public void run() {
            for (int i=0; i<NUM_STAGES; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(String.format("Tourist: %d arrives at Stage: %d", touristId, i+1));
                // next stage can NOT commence unless all tourists have arrived

                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        public static void main(String[] args) {
            for (int i=0; i<NUM_TOURISTS; i++) {
                Thread touristThread = new Thread(new Tourists(i));
                touristThread.start();
            }
        }
    }





}
