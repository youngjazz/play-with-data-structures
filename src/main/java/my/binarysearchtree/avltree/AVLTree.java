package my.binarysearchtree.avltree;

import java.util.ArrayList;

/**
 * 平衡二分搜索树
 * <p>
 * AVL是第一个自平衡二分查找树
 * <p>
 * 平衡: 任意节点左右子树高度差不超过1
 * 可通过标注节点高度,计算平衡因子
 * 叶子节点标1, 非叶子节点使用左右子树最大值+1作为高度
 * 平衡因子 = 左右子树高度差
 *
 * @author leon
 * @since 2019-02-13
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public int height;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //获取节点高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    //是否平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    //是否二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    private boolean isBalanced(Node node) {
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    //获取节点平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * LL
     * <p>
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     * y                              x
     * / \                           /   \
     * x   T4     向右旋转 (y)        z     y
     * / \       - - - - - - - ->    / \   / \
     * z   T3                       T1  T2 T3 T4
     * / \
     * T1   T2
     *
     * @return
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;

        //右旋过程
        y.left = T3;
        x.right = y;

        //更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * RR
     * <p>
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     * y                             x
     * /  \                          /   \
     * T1   x      向左旋转 (y)       y     z
     * / \   - - - - - - - ->   / \   / \
     * T2  z                     T1 T2 T3 T4
     * / \
     * T3 T4
     *
     * @param y
     * @return
     */
    private Node leftRoate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        //左旋过程
        x.left = y;
        y.right = T2;

        //height处理
        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(y.right));

        return x;
    }


    /**
     * 向AVL树中添加元素
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        //递归终止条件
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        //更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balanceFactor = getBalanceFactor(node);

//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("unbalanced:" + balanceFactor);
//        }

        //平衡维护
        //LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        //RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRoate(node);
        }

        //LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRoate(node.left);
            return rightRotate(node);
        }

        //RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRoate(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public V get(K key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }

        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                //使用右子树最小值最为该点的后继
                Node successor = min(node.right);
                successor.left = node.left;
                successor.right = remove(node.right, successor.key);
                node.left = node.right = null;

                retNode = successor;
                if (retNode == null) {
                    return null;
                }

                //计算height
                retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

                //计算平衡因子
                int balanceFactor = getBalanceFactor(retNode);

                //平衡维护
                //LL
                if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
                    return rightRotate(retNode);
                }

                //RR
                if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
                    return leftRoate(retNode);
                }

                //LR
                if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
                    retNode.left = leftRoate(node.left);
                    return rightRotate(retNode);
                }

                //RL
                if (balanceFactor < -1 && getBalanceFactor(retNode.left) > 0) {
                    retNode.right = rightRotate(retNode.right);
                    return leftRoate(retNode);
                }
            }
        }
        return retNode;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }

        return min(node.left);
    }
}
