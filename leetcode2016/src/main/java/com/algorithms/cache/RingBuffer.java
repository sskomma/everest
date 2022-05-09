package com.algorithms.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RingBuffer<K> {

    private static int MILLIS_IN_MINUTE = 5000;
    private final int capacity;
    // 4 x 60 = 240
    private final Map<Long, List<K>> m;

    public RingBuffer(int capacity) {
        m = new ConcurrentHashMap<>();
        this.capacity = capacity;
    }

    public Map<Long, List<K>> getM() {
        return m;
    }

    public List<K> getList(Date expiry) {
        long mill = (expiry.getTime()/MILLIS_IN_MINUTE) % capacity;
        return m.computeIfAbsent(mill, x -> new ArrayList<>());
    }

    private void remove(Long key) {
        m.remove(key);
    }

    public static void main(String[] args) {
        RingBuffer<String> rb = new RingBuffer<>(10);
        Runnable runnable = () -> {
            for(int i = 0; i < 10; i++) {
                try {

                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> results = rb.getList(new Date());
                results.add(String.valueOf(i));
            }
            rb.getM().values().forEach(System.out::println);

        };

        new Thread(runnable).start();
    }
}
