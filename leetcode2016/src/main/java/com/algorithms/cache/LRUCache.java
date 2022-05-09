package com.algorithms.cache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    static class Node {
        int key;
        int value;
        Node prev;
        Node next;
    }

    private Map<Integer, Node> cache;
    private Node head;
    private Node tail;
    private int capacity;
    private int occupancy;

    public LRUCache(int capacity) {
        this.cache = new HashMap<>();
        this.capacity = capacity;
        this.occupancy = 0;
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        pushAhead(cache.get(key));
        return head.next.value;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node n = cache.get(key);
            n.value = value;
            pushAhead(n);
            return;
        }

        //if Over capacity evict.
        if (capacity == occupancy) {
            // evict
            Node toRemove = removeTail();
            cache.remove(toRemove.key);
            occupancy--;
        }
        // Add new K, V to map
        Node newNode = new Node();
        newNode.key = key;
        newNode.value = value;

        cache.put(key, newNode);
        pushAhead(newNode);
        occupancy++;
    }

    private void pushAhead(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        if (prev != null && next != null) {
            prev.next = next;
            next.prev = prev;
        }

        next = head.next;

        head.next = node;
        node.prev = head;

        node.next = next;
        next.prev = node;
    }

    private Node removeTail() {
        Node toRemove = tail.prev;
        Node prev = toRemove.prev;
        Node next = toRemove.next;
        prev.next = next;
        next.prev = prev;

        toRemove.next = null;
        toRemove.prev = null;
        return toRemove;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.put(2, 6);
        System.out.println(cache.get(1));
        cache.put(1, 5);
        cache.put(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }

    /**
     * ["LRUCache","get","put","get","put","put","get","get"]
     * [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
     */
}