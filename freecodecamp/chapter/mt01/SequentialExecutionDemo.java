package chapter.mt01;

public class SequentialExecutionDemo {
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        for(int i=0; i<5; i++){
            System.out.println(String.format("Demo 1 says: %d", i));
        }
    }

    private static void demo2() {
        for(int i=0; i<5; i++){
            System.out.println(String.format("Demo 2 says: %d", i));
        }
    }
}
