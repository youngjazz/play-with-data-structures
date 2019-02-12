package my.linkedlist;

/**
 * 链表
 *
 * @author leon
 * @since 2019-01-02
 */
public class LinkedList<E> {

    private Node dummyHead;
    private Node tail;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E data) {
//        Node newNode = new Node(data);
//        newNode.next = head;
//        head = newNode;
        //上面三行等价于
//        head = new Node(data, head);
        add(0, data);
    }

    /**
     * 指定位置添加参数(不是常有操作, 仅做联系使用)
     *
     * @param index
     * @param data
     */
    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add Failed. Illegal index");
        }

        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
//            Node newNode = new Node(data);
//            newNode.next = pre.next;
//            pre.next = newNode;
        pre.next = new Node(data, pre.next);
        size++;

    }

    public void addLast(E data) {
        add(size, data);
    }

    public boolean contains(E data) {
        Node curr = dummyHead.next;
        while (curr != null) {
            if (curr.data.equals(data)) {
                return true;
            } else {
                curr = curr.next;
            }
        }

        return false;
    }

    public E getFirst(){
        return dummyHead.next.data;
    }

    public void remove(E data) {
        Node pre = dummyHead;
        while (pre.next != null) {
            Node cur = pre.next;
            if (cur.data.equals(data)) {
                pre.next = cur.next;
                cur.next = null;
                size--;
            } else {
                pre = pre.next;
            }
        }
    }

    public E removeFirst() {
        Node first = dummyHead.next;
        E data = first.data;
        if(first != null){
            dummyHead.next = first.next;
            first = null;
            size --;
        }

        return data;
    }

    public E removeLast() {
        Node pre = dummyHead;
        for (int i = 0; i < size - 1; i++) {
            pre = pre.next;
        }
        Node last = pre.next;
        E data = last.data;
        last = null;
        size--;

        return data;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        Node curr = dummyHead.next;
        while (curr != null) {
            stringBuilder.append(curr.data + "->");
            curr = curr.next;
        }
        stringBuilder.append("NULL");

        return stringBuilder.toString();
    }

    private class Node {
        public E data;
        public Node next;
        public Node pre;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(E data) {
            this(data, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }
}
