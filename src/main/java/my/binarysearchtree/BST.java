package my.binarysearchtree;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 二分搜索树
 *
 * @author leon
 * @since 2019-01-07
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {

        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }
    public boolean contains(E e){
        return contains(root, e);
    }

    private  boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        }

        return true;
    }

    /**
     * 前序遍历, 递归实现
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 前序遍历, 非递归实现
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        if (root != null) {
            stack.push(root);
        }

        while (!stack.isEmpty()) {
            Node top = stack.pop();
            System.out.println(top.e);
            if (top.right != null) {
                stack.push(top.right);
            }

            if (top.left != null) {
                stack.push(top.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    /**
     * 层序遍历(广度优先)
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedBlockingDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.remove();
            if (front != null) {
                System.out.println(front.e);
                queue.add(front.left);
                queue.add(front.right);
            }
        }
    }

    /**
     * 获取二分查找树的最小值
     *
     * @return
     */
    public E min() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is Empty");
        }

        return min(root).e;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }

    /**
     * 获取二分查找树的最大值
     *
     * @return
     */
    public E max() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is Empty");
        }

        return max(root).e;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    /**
     * 删除二分查找树的最小节点
     *
     * @return
     */
    public E removeMin() {
        E ret = min();
        root = removeMin(root);

        return ret;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除二分搜索树的最大节点
     *
     * @return
     */
    public E removeMax() {
        E ret = max();

        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node nodeLeft = node.left;
            node.left = null;
            size--;
            return nodeLeft;
        }

        node.right = removeMax(node.right);
        return node;
    }

    public void removeElement(E e) {
        root = removeElement(root, e);
    }

    private Node removeElement(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = removeElement(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = removeElement(node.right, e);
            return node;
        } else {

            //待删除节点左子树为空
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            //待删除节点右子树为空
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            //左右子树都不为空
            Node ret = min(node.right);
            ret.left = node.left;
            ret.right = removeMin(node.right);
            node.left = null;
            node.right = null;
            return ret;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        generateBSTString(root, 0, stringBuilder);

        return stringBuilder.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder stringBuilder) {
        if (node == null) {
            stringBuilder.append(generateBSTDepth(depth) + "null\n");
            return;
        }

        stringBuilder.append(generateBSTDepth(depth) + node.e + "\n");

        generateBSTString(node.left, depth + 1, stringBuilder);
        generateBSTString(node.right, depth + 1, stringBuilder);
    }

    private String generateBSTDepth(int depth) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("--");
        }

        return stringBuilder.toString();
    }

    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
        }
    }
}
