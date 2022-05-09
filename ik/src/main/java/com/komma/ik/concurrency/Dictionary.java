package com.komma.ik.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dictionary {

    private final Map<String, Meaning> words;

    public Dictionary() {
        this.words = new HashMap<>();
    }

    public String lookUp(String word) {
        Meaning meaning = words.get(word);
        return meaning.getMeaning();
    }

    public void editor(String word, String definition) {
        Meaning meaning = words.get(word);
        if (meaning != null) {
            meaning.setMeaning(definition);
        } else {
            meaning = new Meaning(definition);
            words.put(word, meaning);
        }
    }

    static class Meaning {

        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);
        private String meaning;

        public Meaning(String meaning) {
            this.meaning = meaning;
        }

        public String getMeaning() {
            lock.readLock().lock();
            try {
                return meaning;
            } finally {
                lock.readLock().unlock();
            }
        }

        public void setMeaning(String definition) {
            lock.writeLock().lock();
            try {
                this.meaning = definition;
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

}
