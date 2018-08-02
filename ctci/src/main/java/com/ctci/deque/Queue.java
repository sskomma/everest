package com.ctci.deque;

public class Queue<T> {
  private QueueNode<T> head;
  private QueueNode<T> tail;

  public void add(T element) {
    QueueNode<T> node = new QueueNode<T>(element);
    if (head == null) {
      head = node;
      tail = node;
    } else {
      tail.next = node;
      tail = tail.next;
    }
  }

  public T remove() {
    if(head == null)
      return null;
    QueueNode<T> node = head;
    head = head.next;
    node.next = null;
    return node.element;
  }

  public T peek() {
    if(head == null)
      return null;
    return head.element;
  }

  public boolean isEmpty() {
    return head == null;
  }

  private class QueueNode<T> {
    T element;

    QueueNode<T> next;

    QueueNode(T element) {
      this.element = element;
    }
  }

  public static void main(String[] args) {
    Queue<Integer> queue = new Queue<Integer>();
    queue.add(5);
    queue.add(4);
    queue.add(3);
    queue.add(2);
    queue.add(1);
    System.out.println(queue.remove());
    System.out.println(queue.isEmpty());
    System.out.println(queue.remove());
    System.out.println(queue.peek());
    System.out.println(queue.remove());
    System.out.println(queue.remove());
    System.out.println(queue.remove());
    System.out.println(queue.peek());

  }
}
