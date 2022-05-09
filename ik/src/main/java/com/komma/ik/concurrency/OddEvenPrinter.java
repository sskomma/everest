package com.komma.ik.concurrency;

public class OddEvenPrinter {

    private static class Counter {

        private int value;
        private int maxValue;

        public Counter(int maxValue) {
            this.maxValue = maxValue;
            this.value = 1;
        }

        synchronized void printEven(String threadName) {
            while (value % 2 != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s: %d\n", threadName, value++);
            notify();
        }

        boolean isValid() {
            return value < maxValue;
        }

        synchronized void printOdd(String threadName) {
            while (value % 2 == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf("%s: %d\n", threadName, value++);
            notify();
        }
    }

    public static void main(String[] args) {
        Counter c = new Counter(100);

        Runnable evenPrinter = () -> {
            while (c.isValid()) {
                c.printEven("Thread 2");
            }
        };

        Runnable oddPrinter = () -> {
            while (c.isValid()) {
                c.printOdd("Thread 1");
            }
        };

        Thread b = new Thread(evenPrinter);
        Thread a = new Thread(oddPrinter);

        a.start();
        b.start();

    }
}
