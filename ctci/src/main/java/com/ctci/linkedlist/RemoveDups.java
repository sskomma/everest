package com.ctci.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class RemoveDups {
  public static Node removeDups(Node node) {
    if (node == null) {
      return null;
    }
    Node head = node;
    Node prev = node;

    Set<Integer> buffer = new HashSet<>();
    buffer.add(node.data);

    node = node.next;
    while (node != null) {
      if (buffer.add(node.data)) {
        prev = prev.next;
      }
      else {
        prev.next = node.next;
      }
      node = node.next;
    }
    return head;
  }

  public static void main(String[] args) {
    int[] numbers = {1, 2, 2, 4};
    Node head = Node.add(numbers);
    Node.print(head);
    head = removeDups(head);
    System.out.println();
    Node.print(head);
  }
}
