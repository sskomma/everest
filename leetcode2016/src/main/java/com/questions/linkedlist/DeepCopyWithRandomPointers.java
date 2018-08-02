package com.questions.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
 *  Return a deep copy of the list.
 *
 *  #leetcode138
 *  https://leetcode.com/problems/copy-list-with-random-pointer/description/
 *
 */
public class DeepCopyWithRandomPointers {
  Map<RandomListNode, RandomListNode> objectMap;

  public DeepCopyWithRandomPointers() {
    objectMap = new HashMap<>();
  }

  public RandomListNode copyRandomList(RandomListNode node) {
    if( node == null) {
      return null;
    }
    if(objectMap.containsKey(node)) {
      return objectMap.get(node);
    }

    RandomListNode newNode = new RandomListNode(node.label);
    objectMap.put(node, newNode);
    newNode.next = copyRandomList(node.next);
    newNode.random = copyRandomList(node.random);

    return newNode;
  }

  /**
   * Definition for singly-linked list with a random pointer.
   * */
  class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
      this.label = x;
    }
  }
}

