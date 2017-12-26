package com.ctci.linkedlist;

public class PartitionLinkedList {
  public static Node partitionLinkedList(Node node, int n) {

    Node smallHead = new Node(0), bigHead = new Node(0), equalHead = new Node(0);
    Node smallTraverser = smallHead, bigTraverser = bigHead, equalTraverser = equalHead;
    while(node != null) {
      if(node.data > n) {
        bigTraverser.next = node;
        bigTraverser = bigTraverser.next;
      }
      else if(node.data == n) {
        equalTraverser.next = node;
        equalTraverser = equalTraverser.next;
      }
      else {
        smallTraverser.next = node;
        smallTraverser = smallTraverser.next;
      }
      node = node.next;
    }
    bigTraverser.next = null;
    equalTraverser.next = null;
    smallTraverser.next = null;

    bigTraverser.next = equalHead.next;
    equalTraverser.next =  smallHead.next;
    return bigHead.next;
  }

  public static void main(String[] args) {
    int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8};
    Node head = Node.add(numbers);
    Node.print(partitionLinkedList(head, 5));
  }
}
