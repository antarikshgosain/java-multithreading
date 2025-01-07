package chapter.mt02;

public class CH03_ThreadPriority {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
        Thread.currentThread().setPriority(8);
        System.out.println(Thread.currentThread().getPriority());
    }
}