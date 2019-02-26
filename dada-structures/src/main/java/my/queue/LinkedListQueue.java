package my.queue;

/**
 * Queue的LinkedList实现
 *
 * @author leon
 * @since 2019-01-02
 */
public class LinkedListQueue<E> implements Queue<E> {

    private Node head;
    private Node tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E e) {
        if(tail == null){
            tail = new Node(e);
            head = tail;
        }
        tail.next = new Node(e);
        tail = tail.next;
        size ++;
    }

    @Override
    public E dequeue() {
        if(isEmpty()){
            return null;
        }

        Node retNode = head;
        head = head.next;
        retNode.next = null;

        if(head == null){
            tail = null;
        }
        size --;
        return retNode.data;
    }

    @Override
    public E getFront() {
        return head.data;
    }

    public E getLast(){
        return tail.data;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node cur = head;
        stringBuilder.append("Queue: Front");
        while (cur != null){
            stringBuilder.append(cur.data+"->");
            cur = cur.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }

    private class Node {
        public E data;
        public Node next;

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
}
