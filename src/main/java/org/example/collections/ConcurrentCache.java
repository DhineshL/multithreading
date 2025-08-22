package org.example.collections;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ConcurrentCache {
    private static final Map<String, String> cache = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int threadNum = i;
            new Thread(()->{
                String key = "Key @ " + threadNum;
                for (int j = 0; j < 3; j++) {
                   String value = getCachedValue(key);
                   System.out.println("Thread "+ Thread.currentThread().getName()+": Key = "+key+ " value = "+value);
                }
            }).start();
        }
    }

    private static String getCachedValue(String key){
        String value = cache.get(key);
        if(value==null){
            value = compute(key);
            cache.put(key,value);
        }
        return value;
    }

    private static String compute(String key){
        try {
            Thread.sleep(500);
            return "Value for Key " + key;
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
