package chapter.mt05;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CH040_ConcurrentCacheUsingCCHashMap {

    private static final Map<String, String> cacheMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            final int threadNum = i;
            new Thread(() -> {
                String key = "Key @ " + threadNum;
                for (int j=0; j<3; j++) {
                    String value = getCachedValue(key);
                    System.out.println(String.format("Thread %s : Key %s",Thread.currentThread().getName(),key));
                }
            }).start();
        }
    }

    private static String getCachedValue(final String key) {
        String value = cacheMap.get(key);
        if (value == null) {
            value = compute(key);
            cacheMap.put(key, value);
        }
        return value;
    }

    private static String compute(String key) {
        System.out.println(String.format("%s not present, computing...", key));
        try {
           Thread.sleep(2000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
        return "Value for " + key ;
    }
}
