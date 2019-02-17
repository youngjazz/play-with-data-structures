package my.map;

/**
 * Map 基于BST实现
 *
 * @author leon
 * @since 2019-01-29
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * 向Map中添加元素
     *
     * @param key
     * @param value
     */
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.equals(node.key)) {
            node.value = value;
        } else if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else {
            node.right = add(node.right, key, value);
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node deleteNode = get(root, key);
        if (deleteNode != null) {
            root = remove(root, key);
            return deleteNode.value;
        }

        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return remove(node.right, key);
        } else {
            //待删除节点左子树为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            //待删除节点右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            //待删除节点左右子树都不为空
            Node successor = min(node.right);
            successor.left = node.left;
            successor.right = removeMin(node.right);
            node.left = node.right = null;

            return successor;
        }

    }

    private Node min(Node node) {
        Node currentNode = node;
        while (currentNode.left != null) {
            currentNode = currentNode.left;
        }
        return currentNode;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node ret = node.right;
            node.right = null;
            size--;
            return ret;
        }

        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public boolean contains(K key) {
        return get(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node get(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    @Override
    public void set(K key, V newValue) {
        Node node = get(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist");
        }
        node.value = newValue;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
