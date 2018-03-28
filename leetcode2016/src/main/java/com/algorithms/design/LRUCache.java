package com.algorithms.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
  ListNode head;
  ListNode tail;
  int capacity;
  Map<Integer, ListNode> map;

  public LRUCache(int capacity) {
    head = new ListNode(0, 0);
    tail = new ListNode(0, 0);
    head.next = tail;
    tail.previous = head;
    map = new HashMap<>(capacity);
    this.capacity = capacity;
  }

  public static void main(String[] args) {
    LRUCache cache = new LRUCache(2);
    cache.put(1, 1);
    cache.put(2, 2);
    System.out.println(cache.get(1));
    cache.put(3, 3);
    System.out.println(cache.get(2));
    cache.put(4, 4);
    System.out.println(cache.get(1));
    System.out.println(cache.get(3));
    System.out.println(cache.get(4));
  }

  public int get(int key) {
    if (map.containsKey(key)) {
      updateKey(key);
      return map.get(key).value;
    }
    return -1;
  }

  public void put(int key, int value) {
    if (map.containsKey(key)) {
      updateKey(key);
      map.get(key).value = value;
    } else if (map.size() == capacity) {
      ListNode nodeToDelete = head.next;
      head.next = nodeToDelete.next;
      nodeToDelete.next.previous = head;
      map.remove(nodeToDelete.key);
    }
    ListNode newNode = new ListNode(key, value);
    newNode.previous = tail.previous;
    newNode.previous.next = newNode;
    tail.previous = newNode;
    newNode.next = tail;
    map.put(key, newNode);
  }

  private void updateKey(int key) {
    ListNode node = map.get(key);
    node.next.previous = node.previous;
    node.previous.next = node.next;

    node.previous = tail.previous;
    tail.previous.next = node;
    tail.previous = node;
    node.next = tail;
  }

  static class ListNode {
    int key;
    int value;
    ListNode previous;
    ListNode next;

    ListNode(int key, int value) {
      this.key = key;
      this.value = value;
    }
  }
}