package my.queue;

import my.array.MyArray;

/**
 * 队列 - 数组实现
 * @author leon
 * @since 2018-12-25
 */
public class ArrayQueue<E> implements Queue<E> {

    private MyArray<E> myArray;

    public ArrayQueue() {
        myArray = new MyArray<>();
    }

    public ArrayQueue(int capicity){
        myArray = new MyArray<>(capicity);
    }

    /**
     * O(1)时间复杂度
     * @return
     */
    @Override
    public void enqueue(E e) {
        myArray.addLast(e);
    }

    /**
     * O(n)时间复杂度
     * @return
     */
    @Override
    public E dequeue() {
        return myArray.removeFirst();
    }

    @Override
    public E getFront() {
        return myArray.getFirst();
    }

    @Override
    public int getSize() {
        return myArray.getSize();
    }

    @Override
    public boolean isEmpty() {
        return myArray.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front[");
        for (int i = 0; i < myArray.getSize(); i++) {
            res.append(myArray.get(i));
            if(i != myArray.getSize() - 1){
                res.append(", ");
            }
        }
        res.append("]tail");

        return res.toString();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if(i%3 == 0){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
