package com.algorithms.cache;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TTLCache {

    public class ADT {
        String key;
        String value;
        Calendar expiry;

        public ADT(String key, String value, Calendar expiry) {
            this.key = key;
            this.value = value;
            this.expiry = expiry;
        }
    }

    private final Map<String, ADT> cache;
    private final Map<String, Set<String>> timeMap;

    public TTLCache() {
        this.cache = new HashMap<>();
        this.timeMap = new HashMap<>();
    }

    public void put(String key, String value, Calendar expiry) {
        // Remove old entry, if exists
        if(cache.containsKey(key)) {
            cleanUpKey(key);
        }
        // add new entry
        cache.put(key, new ADT(key, value, expiry));
        String timeKey = computeKey(expiry);
        timeMap.computeIfAbsent(timeKey, x -> new HashSet<>()).add(key);
    }

    public String get(String key) {
        if(!cache.containsKey(key)) {
            return null;
        }
        ADT value = cache.get(key);
        if(isInPast(value) ){
            cleanUpKey(key);
            return null;
        }
        return value.value;
    }

    private String computeKey(Calendar expiry) {
        return String.join("-", String.valueOf(expiry.get(Calendar.DATE)),
                                 String.valueOf(expiry.get(Calendar.HOUR_OF_DAY)), String.valueOf(expiry.get(Calendar.MINUTE)));
    }

    private void cleanUpKey(String key) {
        if(cache.containsKey(key)) {
            String timeKey = computeKey(cache.get(key).expiry);
            timeMap.get(timeKey).remove(key);
            cache.remove(key);
        }
    }

    private boolean isInPast(ADT val) {
         return (new Date().getTime() > val.expiry.getTimeInMillis());
    }

}
