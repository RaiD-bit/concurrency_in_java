package org.raidbit;


import java.util.ArrayDeque;
import java.util.Queue;

public class DevaQueue<T> {
    private final int maxSize;

    private final Queue<T> queue;


    public DevaQueue(int maxSize) {
        this.maxSize = maxSize;
        this.queue = new ArrayDeque<>(maxSize);
    }

    public synchronized  void enqueue(T element) {
        while (queue.size() == maxSize) {
            // wait till q.size is reduced
            System.out.println("queue is full cannot add more so waiting for consumer to consume");
            try {
                wait();
            } catch (InterruptedException e) {
                // handle exception
            }
        }
        System.out.println("adding element " + element);
        queue.offer(element);
        notifyAll();
    }

    public synchronized T dequeue() {
        while (queue.size() == 0) {
            // wait till q.size is reduced
            System.out.println("queue is empty cannot remove element so waiting for producer to produce");
            try {
                wait();
            } catch (InterruptedException e) {
                // handle exception
            }
        }
        T res = queue.poll();
        notifyAll();
        return res;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
