package com.algorithms.design;

public class MinHeap {

  private int capacity = 100;
  private int size = 0;
  private int[] data = new int[capacity];

  public int poll() {
    if(isEmpty()) {
      throw new ArrayIndexOutOfBoundsException("Heap is empty");
    }
    int val = data[1];
    data[1] = data[size];
    size = size -1;
    percolateDown();
    return val;
  }

  public void offer(int val) {
    if(size+1 == capacity) {
      throw new ArrayIndexOutOfBoundsException("Heap capacity reached");
    }
    size = size + 1;
    data[size] = val;
    percolateUp();
  }

  public int peek() {
    if(isEmpty())
      throw new ArrayIndexOutOfBoundsException("Heap is empty");
    return data[1];
  }

  private void percolateUp() {
    if (isEmpty()) {
      return;
    }
    int curPosition = size;
    int root = curPosition / 2;

    while (root >= 1 && data[root] > data[curPosition]) {
      swap(root, curPosition);
      curPosition = root;
      root = root / 2;
    }
  }

  private void percolateDown() {
    if (isEmpty()) {
      return;
    }
    int curPosition = 1;
    int left = 2 * curPosition;
    int right = (2 * curPosition) + 1;

    while (left <= size && right <= size && data[curPosition] > Math.min(data[left], data[right])) {

      if (data[left] < data[right] ) {
        swap(curPosition, left);
        curPosition = left;
      } else if (data[right] < data[left]) {
        swap(curPosition, right);
        curPosition = right;
      }
      left = curPosition * 2;
      right = (curPosition * 2) + 1;
    }

    if(left <= size && data[curPosition] > data[left]) {
      swap(curPosition, left);
    } else if(right <= size && data[curPosition] > data[right]) {
      swap(curPosition, right);
    }
  }

  public boolean isEmpty() {
    return size < 1;
  }

  private void swap(int a, int b) {
    int temp = data[a];
    data[a] = data[b];
    data[b] = temp;
  }

  public static void main(String[] args) {
    MinHeap minHeap = new MinHeap();
    minHeap.offer(9);
    minHeap.offer(11);
    minHeap.offer(2);
    minHeap.offer(50);
    minHeap.offer(99);
    minHeap.offer(45);
    minHeap.offer(65);
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
    System.out.println(minHeap.poll());
  }
}
