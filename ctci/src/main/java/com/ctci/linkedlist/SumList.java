package com.ctci.linkedlist;

public class SumList {
  public static Node sum(Node a1, Node a2) {
    Node sumHead = new Node(0);
    Node sumTraverser = sumHead;
    int carryFwd = 0, sum = 0;
    while (a1 != null && a2 != null) {
      sum = a1.data + a2.data + carryFwd;
      carryFwd = sum / 10;
      sumTraverser.next = new Node(sum % 10);
      sumTraverser = sumTraverser.next;
      a1 = a1.next;
      a2 = a2.next;
    }
    while (a1 != null) {
      sum = a1.data + carryFwd;
      carryFwd = sum / 10;
      sumTraverser.next = new Node(sum % 10);
      sumTraverser = sumTraverser.next;
      a1 = a1.next;
    }
    while (a2 != null) {
      sum = a2.data + carryFwd;
      carryFwd = sum / 10;
      sumTraverser.next = new Node(sum % 10);
      sumTraverser = sumTraverser.next;
      a2 = a2.next;
    }
    if (carryFwd != 0) {
      sumTraverser.next = new Node(carryFwd);
    }

    return sumHead.next;
  }

  public static Node sumRecursive(Node a1, Node a2, int carryFwd) {
    int sum = 0;
    if (a1 == null && a2 == null && carryFwd == 0) {
      return null;
    } else if (a1 != null && a2 != null) {
      sum = a1.data + a2.data + carryFwd;
    } else if (a1 != null) {
      sum = a1.data + carryFwd;
    } else if (a2 != null) {
      sum = a2.data + carryFwd;
    } else {
      sum = carryFwd;
    }
    if (sum == 0) {
      return null;
    }
    Node node = new Node(sum % 10);
    carryFwd = sum / 10;
    node.next = sumRecursive(a1 != null ? a1.next : null, a2 != null ? a2.next : null, carryFwd);
    return node;
  }

  public static void main(String[] args) {
    int[] n1 = {7, 1, 6};
    int[] n2 = {5, 9, 6};
    Node a1 = Node.add(n1);
    Node a2 = Node.add(n2);
    Node.print(sumRecursive(a1, a2, 0));
  }
}
