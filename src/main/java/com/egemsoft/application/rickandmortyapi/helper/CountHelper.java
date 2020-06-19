package com.egemsoft.application.rickandmortyapi.helper;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Helper for counting Character name length count by multiple threads
 */
@Component
public class CountHelper {

    private ConcurrentMap<String, Integer> countsByThreads = new ConcurrentHashMap<>();

    /**
     * @param count Character name characters count
     */
    public void increment(int count) {
        String threadName = Thread.currentThread().getName();
        countsByThreads.put(threadName, countsByThreads.getOrDefault(threadName, 0) + count);
    }

    /**
     * @return characters count by multiple threads
     */
    public Map<String, Integer> getCountsByThreads() {
        return Collections.unmodifiableMap(countsByThreads);
    }

    /**
     * clear map before every counting process
     */
    public void clear() {
        countsByThreads.clear();
    }
}
