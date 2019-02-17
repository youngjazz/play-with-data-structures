package my.queue;

import my.heap.MaxHeap;

/**
 * 优先队列, 基于最大堆实现
 *
 * @author leon
 * @since 2019-02-01
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap<>();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.removeMax();
    }

    @Override
    public E getFront() {
        return maxHeap.top();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}
