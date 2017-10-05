package com.questions.linkedlist;

import com.sun.tools.javac.util.List;

/**
 * https://leetcode.com/problems/partition-list/description/
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
 *
 * You should preserve the original relative order of the nodes in each of the two partitions.
 *
 * For example,
 * Given 1->4->3->2->5->2 and x = 3,
 * return 1->2->2->4->3->5.
 *
 * @author Ram Komma
 */

public class PartitionList {

  public static ListNode partitionList(ListNode node, int x) {
    if(node == null) {
      return null;
    }

    ListNode smallerRunner = null,smallerHead = null;
    ListNode equalRunner = null, equalHead= null;
    ListNode largerRunner=null, largerHead = null;

    while (node != null) {
      if(node.val < x) {
        if( smallerHead == null) {
          smallerHead = node;
        }
        else {
          smallerRunner.next = node;
        }
        smallerRunner = node;
      }
      else if( node.val == x) {
        if( equalHead == null) {
          equalHead = node;
        }
        else {
          equalRunner.next = node;
        }
        equalRunner = node;
      }
      else {
        if( largerHead == null) {
          largerHead = node;
        }
        else {
          largerRunner.next = node;
        }
        largerRunner = node;
      }
      node = node.next;
    }
    ListNode result = null;

    if(largerHead != null) {
      largerRunner.next = null;
      result = largerHead;
    }
   if (equalHead != null) {
      equalRunner.next = result;
     result = equalHead;
    }

    if(smallerHead != null) {
      smallerRunner.next = result;
      result = smallerHead;
    }

    return result;
  }



  public static void main(String[] args)
  {
    LinkedList list = new LinkedList();
/*    list.addNode(5);
    list.addNode(10);
    list.addNode(2);
    list.addNode(2);
    list.addNode(8);
    list.addNode(6);
    list.addNode(1);
    list.addNode(7);
    list.addNode(9);*/
    list.addNode(2);
    list.addNode(1);

    LinkedListUtils.printListFromNode(list.getHead());
    ListNode n = partitionList(list.getHead(),1);
    LinkedListUtils.printListFromNode(n);
  }

}
