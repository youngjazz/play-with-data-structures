package my.queue;

/**
 * 循环队列实现
 *
 * @author leon
 * @since 2018-12-25
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capicity) {
        //因为循环队列会浪费一个空间
        this.data = (E[]) new Object[capicity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public int getCapacity() {
        return this.data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        if (isFull()) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from empty queue.");
        }

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size <= getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }

        return ret;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public boolean isFull() {
        return (tail + 1) % data.length == front;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue:size=%d , capacity=%d\n", size, getCapacity()));
        res.append(" front[");
        for (int i = 0; i < size; i++) {
            res.append(data[(front + i) % data.length]);
            if (i != size - 1) {
                res.append(", ");
            }
        }

        res.append("]tail");

        return res.toString();
    }

    public static void main(String[] args) {
        LoopQueue queue = new LoopQueue();
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 2 == 0){
                queue.dequeue();
            }
            System.out.println(queue);
        }
    }
}
