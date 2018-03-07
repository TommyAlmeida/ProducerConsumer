package org.academiadecodigo.bootcamp.concurrency.bqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Blocking Queue
 * @param <T> the type of elements stored by this queue
 */
public class BQueue<T> {

    private int limit;
    private Queue<T> list;

    /**
     * Constructs a new queue with a maximum size
     * @param limit the queue size
     */
    public BQueue(int limit) {
        this.limit = limit;
        this.list = new LinkedList<>();
    }

    /**
     * Inserts the specified element into the queue
     * Blocking operation if the queue is full
     * @param data the data to add to the queue
     */
    public void offer(T data) {
        synchronized (this){
            while (list.size() == limit){
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            list.add(data);

            System.out.println("IN OFFER: Size " + list.size());
            notifyAll();
        }
    }

    /**
     * Retrieves and removes data from the head of the queue
     * Blocking operation if the queue is empty
     * @return the data from the head of the queue
     */
    public T poll() {
        synchronized (this){
            while (list.size() == 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            System.out.println("IN POLL: Size " + list.size());

            notifyAll();
            return list.poll();
        }
    }

    /**
     * Gets the number of elements in the queue
     * @return the number of elements
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Gets the maximum number of elements that can be present in the queue
     * @return the maximum number of elements
     */
    public int getLimit() {
        return limit;
    }

}
