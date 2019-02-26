package my.map;

/**
 * Map 基于LinkedList实现
 *
 * @author leon
 * @since 2019-01-28
 */
public class LinkedListMap<K, V> implements Map<K, V>{

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if(node == null){
            dummyHead.next = new Node(key, value, dummyHead.next);
            size ++;
        }else{
            node.value = value;
        }
    }

    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while (pre.next != null){
            if (pre.next.key.equals(key)) {
                Node delNode = pre.next;
                pre.next = delNode.next;
                delNode.next = null;
                size--;
                return delNode.value;
            }

            pre = pre.next;
        }

        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if(node == null){
            throw new IllegalArgumentException(key +" doesn't exist");
        }else{
            node.value = newValue;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(Object key){
        Node currentNode = dummyHead.next;
        while (currentNode != null){
            if(currentNode.key.equals(key)){
                return currentNode;
            }
            currentNode = currentNode.next;
        }

        return null;
    }

    private class Node{
        private K key;
        private V value;
        private Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String toString() {
            return key.toString()+":"+value.toString();
        }
    }
}
