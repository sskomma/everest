package com.ctci.linkedlist;

public class Node {
  public Node next;
  public int data;

  public Node(int data) {
    this.data = data;
  }
  public boolean hasNext() {
    return (next == null);
  }

  public static void print(Node n) {
    while(n != null) {
      System.out.print(n.data + "->");
      n = n.next;
    }
  }

  public static Node add(int[] numbers) {
    if(numbers == null) {
      return null;
    }
    Node head = new Node(numbers[0]);
    Node node = head;
    for(int i = 1; i < numbers.length; i++ ) {
      Node temp = new Node(numbers[i]);
      node.next = temp;
      node = temp;
    }
    return head;
  }
}
