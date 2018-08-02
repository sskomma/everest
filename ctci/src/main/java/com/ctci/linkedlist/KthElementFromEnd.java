package com.ctci.linkedlist;

public class KthElementFromEnd {
  public static int kthElementFromEnd(Node node, int k) {
    if (node == null) {
      return Integer.MIN_VALUE;
    }
    Node p1 = node, p2 = node;
    int i = 1;
    for (; i < k && p1 != null; i++) {
      p1 = p1.next;
    }
    if (i != k) {
      return Integer.MIN_VALUE;
    }
    while (p1.next != null) {
      p1 = p1.next;
      p2 = p2.next;
    }
    return p2.data;
  }

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4};
    Node head = Node.add(numbers);
    System.out.println(kthElementFromEnd(head, 3));
  }
}
