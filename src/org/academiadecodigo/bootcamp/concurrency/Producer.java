package org.academiadecodigo.bootcamp.concurrency;

import org.academiadecodigo.bootcamp.concurrency.bqueue.BQueue;

/**
 * Produces and stores integers into a blocking queue
 */
public class Producer implements Runnable {

    private final BQueue<Integer> queue;
    private int elementNum;

    /**
     * @param queue the blocking queue to add elements to
     * @param elementNum the number of elements to produce
     */
    public Producer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.elementNum = elementNum;
    }

    @Override
    public void run() {
        if(queue.getSize() == queue.getLimit()){
            System.out.println("Queue is full");
            return;
        }

        while (elementNum > 0){
            synchronized (queue){
                queue.offer(elementNum);
                elementNum--;
                System.out.println("Element produced: " + elementNum);
            }
        }
    }

}
