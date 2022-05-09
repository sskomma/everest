package com.komma.ik.concurrency;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {

    private final Deque<Runnable> work;
    private final List<PoolWorker> workers;

    public ThreadPool(int n) {
        work = new LinkedList<>();
        workers = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            workers.add(new PoolWorker());
            workers.get(i).start();
        }
    }

    public void submitJob(Runnable task) {
        synchronized (work) {
            work.addLast(task);
            work.notify();
        }

    }

    private class PoolWorker extends Thread {

        Runnable task = null;

        @Override
        public void run() {
            while (true) {
                synchronized (work) {
                    while (work.isEmpty()) {
                        try {
                            work.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = work.removeFirst();
                }
                task.run();
            }
        }
    }
}
