package chapter.mt02;

public class CH02_DaemonUserThread {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DaemonHelper());
        Thread userThread = new Thread(new UserHelper());

        bgThread.setDaemon(true);
        //marks bgThread as daemon thread

        bgThread.start();
        userThread.start();
    }
}

class DaemonHelper implements Runnable {

    @Override
    public void run() {
        int count = 0 ;
        while(count<100){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
            System.out.println("Daemon helper running");
        }
    }
}

class UserHelper implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread is done with execution");
    }
}