package com.questions.linkedlist;

/**A Utility class with Static methods, to perform various jobs on LinkedList and Nodes.
 *
 * @author Ram Komma
 *
 */
public class LinkedListUtils {

  /**Description: Give a node, delete it from the linked list.
   * https://leetcode.com/problems/delete-node-in-a-linked-list/
   *
   * @param node, Node to be deleted from the linkedlist.
   */
  public static void deleteNode(ListNode node) {
    if (node == null) {
      return;
    } else if (node.getNext() == null) {
      node = null;
    } else {
      ListNode next = node.getNext();
      node.val = next.val;
      node.next = next.next;
    }
  }

  /**Description: A method, to put all nodes in odd positions in list before all even nodes.
   * where, the sequence of odd nodes and even nodes is kept intact.
   * Input   : 1->2->3->4->5->6
   * Output  : 1->3->5->2->4->6
   * Note: The nodes in odd positions are put upfront and not the elements with odd value.
   * https://leetcode.com/problems/odd-even-linked-list/
   *
   */
  public static ListNode oddEventList(ListNode head) {
    if (head != null && head.next != null) {
      //refers to the last odd-node in the list.
      ListNode oddListPointer = head;
      //refers to the last even-node in the list.
      ListNode evenListPointer = head.next;
      //refers to a navigator, that moves in hunt of odd positioned nodes in list.
      ListNode navigator = head;
      while (evenListPointer.hasNext()) {
        navigator = evenListPointer.next;
        ListNode evenListStart = oddListPointer.next;
        oddListPointer.next = navigator;
        oddListPointer = oddListPointer.next;
        ListNode nextEvenNode = navigator.next;
        navigator.next = evenListStart;
        evenListPointer.next = nextEvenNode;
        if (evenListPointer.hasNext()) {
          evenListPointer = evenListPointer.next;
        }
      }
    }
    return head;
  }

  /**Given a linked list, swap every two adjacent nodes and return its head.
   * For example,
   * Given 1->2->3->4, you should return the list as 2->1->4->3.
   * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
   *
   * https://leetcode.com/problems/swap-nodes-in-pairs/
   *
   * @param head of the linked list, of which nodes are to be swapped.
   * @return head
   */
  public static ListNode swapPairs(ListNode head) {
    if (head == null) {
      return null;
    }
    ListNode firstNode = head;
    ListNode secondNode = firstNode.next;
    ListNode previousNode = null;
    while (secondNode != null) {
      if (previousNode != null) {
        previousNode.next = secondNode;
      }
      firstNode.next = secondNode.next;
      secondNode.next = firstNode;

      if (previousNode == null) {
        head = secondNode;
      }

      previousNode = firstNode;

      if (firstNode.next == null) {
        break;
      }
      firstNode = firstNode.next;
      secondNode = firstNode.next;
    }
    return head;
  }

  /**Given a linked list, determine if it has a cycle in it.
   *
   * Description: https://leetcode.com/problems/linked-list-cycle/
   *
   * @param head of the linked list
   * @return true, if it has cycles; false otherwise.
   */
  public static boolean hasCycle(ListNode head) {
    if (head == null) {
      return false;
    }
    ListNode slowPtr = head;
    ListNode fastPtr = head;

    while (fastPtr.next != null && fastPtr.next.next != null) {
      slowPtr = slowPtr.next;
      fastPtr = fastPtr.next.next;
      if (fastPtr.equals(slowPtr)) {
        return true;
      }
    }
    return false;
  }

  /**Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
   *
   * Description: https://leetcode.com/problems/merge-two-sorted-lists/
   * @param l1, head of list1.
   * @param l2, head of list2.
   * @return head of merged list1 and list2.
   */
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    if (l1 == null && l2 == null) {
      return null;
    } else if (l1 == null) {
      return l2;
    } else if (l2 == null) {
      return l1;
    } else {
      ListNode l3 = new ListNode(0);
      ListNode nav = l3;
      while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
          nav.next = l1;
          l1 = l1.next;
        } else if (l1.val > l2.val) {
          nav.next = l2;
          l2 = l2.next;
        } else {
          nav.next = l1;
          l1 = l1.next;
          nav = nav.next;
          nav.next = l2;
          l2 = l2.next;
        }
        nav = nav.next;
      }

      if (l1 != null && l2 == null) {
        nav.next = l1;
      } else if (l1 == null && l2 != null) {
        nav.next = l2;
      }

      return l3.next;
    }
  }

  /**
   * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
   *
   * Given a sorted linked list, delete all duplicates such that each element appear only once.
   * For example,
   * Given 1->1->2, return 1->2.
   * Given 1->1->2->3->3, return 1->2->3.
   *
   * @param node, head of the linked list with duplicates.
   * @return head of the linked list, whose duplicates are removed.
   */

  public static ListNode removeDuplicatesFromSortedLinkedList(ListNode node) {
    if (node == null) {
      return null;
    }
    ListNode slow = node, fast = node.getNext();
    while (fast != null) {
      if (slow.val != fast.val) {
        slow.next = fast;
        slow = fast;
      }
      fast = fast.next;
    }
    slow.next = null;
    return node;
  }

  /**
   * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
   * For example,
   * Given 1->2->3->3->4->4->5, return 1->2->5.
   * Given 1->1->1->2->3, return 2->3.
   * @param node
   * @return
   */
  public static ListNode removeElementAndItsDuplicatesFromSortedLinkedList(ListNode node) {
    if (node == null) {
      return null;
    }
    ListNode head = new ListNode(0);
    head.next = node;
    ListNode slow = head;
    ListNode fast = node.next;
    while (fast != null) {
      if (slow.next.val == fast.val) {
        while (fast != null && slow.next.val == fast.val) {
          fast = fast.next;
        }
        slow.next = fast;
        if (fast == null) {
          break;
        }
        fast = fast.next;
      } else {
        fast = fast.next;
        slow = slow.next;
      }
    }
    return head.next;
  }

  public static void printListFromNode(ListNode node) {
    if (node != null) {
      do {
        System.out.print(node.val);
        if (node.hasNext()) {
          System.out.print("->");
        }
        node = node.next;
      } while (node != null);
    } else {
      System.out.println("Empty List");
    }
    System.out.println("");
  }

  public static void main(String[] args) {
    LinkedList list = new LinkedList();
    list.addNode(1);
    list.addNode(2);
    list.addNode(2);
    list.addNode(2);
    list.addNode(5);
    list.addNode(6);
    list.addNode(7);
    list.addNode(8);
    list.addNode(9);
    list.addNode(10);

    list.printList();
    ListNode n = removeElementAndItsDuplicatesFromSortedLinkedList(list.getHead());
    printListFromNode(n);
  }
}
