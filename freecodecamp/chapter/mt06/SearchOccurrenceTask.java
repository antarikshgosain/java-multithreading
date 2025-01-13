package chapter.mt06;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class CH040_RecursiveTaskUsingForkJoi2 {
    public static void main(String[] args) {
        int[] arr = new int[100];
        Random random = new Random();
        for (int i=0; i<arr.length; i++) {
            arr[i] = random.nextInt(10) + 1;
        }
        int searchElement = random.nextInt(10) + 1;
        try (ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            SearchOccurrenceTask task = new SearchOccurrenceTask(arr, 0, arr.length-1, searchElement);
            Integer occurrence = pool.invoke(task);
            System.out.printf("Array %s: ",Arrays.toString(arr));
            System.out.printf("\n%d is found: %d times", searchElement, occurrence);
        }

    }
}

public class SearchOccurrenceTask extends RecursiveTask<Integer> {

    int arr[] ;
    int start ;
    int end;
    int searchElement;

    public SearchOccurrenceTask(int[] arr, int start, int end, int searchElement) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchElement = searchElement;
    }

    @Override
    protected Integer compute() {
        return search();
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
