package chapter.mt06;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class CH040_SearchOccurrenceUsingRecursiveTaskForkJoinPool {
    public static void main(String[] args) {

        int arrSize = 1000;
        int[] arr = new int[arrSize];
        Random random = new Random();
        for (int i=0; i<arr.length; i++) {
            arr[i] = random.nextInt(10) + 1;
        }
        int searchElement = random.nextInt(10) + 1;
        try (ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            SearchOccurrenceTask task = new SearchOccurrenceTask(arr, 0, arr.length-1, searchElement);
            Integer occurrence = pool.invoke(task);
            System.out.printf("\nArray %s: ",Arrays.toString(arr));
            System.out.printf("\n%d is found: %d times", searchElement, occurrence);
        }

    }
}

class SearchOccurrenceTask extends RecursiveTask<Integer> {

    int arr[] ;
    int start ;
    int end;
    int searchElement;

    @Override
    public String toString() {
        return "\nSearchOccurrenceTask Created" +
                "\narr= " + Arrays.toString(arr) +
                "\nstart= " + start +
                "\nend= " + end +
                "\nsearchElement= " + searchElement +
                '\n';
    }

    public SearchOccurrenceTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;

    }

    @Override
    protected Integer compute() {
        int size = end - start - 1 ;
        final int cutoffLimit = 50;
        if(size > cutoffLimit) {
            int mid = ( start + end ) / 2 ;
            SearchOccurrenceTask task1 = new SearchOccurrenceTask(arr, start, mid, searchElement);
            SearchOccurrenceTask task2 = new SearchOccurrenceTask(arr, (mid+1), end, searchElement);
            task1.fork();
            task2.fork();
            // Print the task names (here we use Thread.currentThread().getName() to track which thread is executing)
            String threadName = Thread.currentThread().getName();
            System.out.printf("\nTask 1 (Thread: %s) is processing the range [%d, %d]", threadName, start, mid);
            System.out.printf("\nTask 2 (Thread: %s) is processing the range [%d, %d]", threadName, mid + 1, end);
            System.out.printf("\nThread %s Result is %d + %d = %d", threadName, task1.join(), task2.join(), (task1.join()+task2.join()));
            return task1.join() + task2.join();
        } else {
            return search();
            // just do normal search if arr.length < 50 i.e. `final int cutoffLimit`
        }
    }

    private Integer search() {
        int count = 0;
        for (int i=start; i<=end; i++) {
            if (arr[i]==searchElement) {
                count++;
            }
        }
        return count;
    }
}
