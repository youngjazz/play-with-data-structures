package my.binarysearchtree.redblacktree;

/**
 * 红黑树
 *
 * 也是自平衡的二分查找树
 *
 * 红黑树最重要的5条性质:
 * 1.每个节点要么是黑的，要么是红的
 * 2.根节点是黑的(nil)
 * 3.叶节点是黑的
 * 4.如果一个节点是红的，他的两个儿子节点都是黑的
 * 5.任一节点到叶节点经过的黑节点是一样多的
 *
 *
 * 理解红黑树与2-3树的关系
 * 实际上二者是完全等价的， 黑色就对应2-3树中的2节点；
 * 红色节点表示与父节点融合， 红节点加上其父节点就对应2-3树中的3节点
 * 特征：所有红色的节点一定都是向左倾斜的，这是定义出来的，而不是推导出来的
 * 有几个三节点就对应几个红节点
 *
 * 黑色节点有孩子一定是黑色的，类比2-3树分析
 * 红黑树是严格意义讲不是决定平衡，但是黑平衡的二叉树
 * 牺牲了平衡性，最大高度2logN，但是红黑树的统计性能比AVL更优
 *
 * @author leon
 * @since 2019-02-15
 */
public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            //初始化的节点是红色， 类比2-3树添加新节点永远是和叶子节点进行融合
            color = RED;
        }
    }

    private Node root;
    private int size;

    public int getSize(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    //判断节点颜色
    private boolean isRed(Node node){
        return node == null ? BLACK : node.color;
    }

    /**
     *    node                  x
     *   /   \     左旋转      /   \
     *  T1   x    --------> node  T3
     *      / \            /   \
     *     T2 T3          T1   T2
     */
    private Node leftRotate(Node node){
        Node x = node.right;

        //左旋
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    /**
     *      node                   x
     *     /   \     右旋转       /  \
     *    x    T2   ------->   y   node
     *   / \                       /  \
     *  y  T1                     T1  T2
     */
    private Node rightRotate(Node node){
        Node x = node.left;
        //右旋
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    //颜色翻转
    private void flipColors(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    //添加元素
    public void add(K key, V value){
        root = add(root , key , value);
        root.color = BLACK;
    }

    private Node add(Node node, K key, V value) {
        if(node == null){
            size++;
            return new Node(key, value); //默认红色节点
        }

        if(key.compareTo(node.key) < 0){
            node.left = add(node.left, key,value);
        }else if(key.compareTo(node.key)>0){
            node.right = add(node.right, key, value);
        }else{
            node.value = value;
        }

        if(isRed(node.right) && !isRed(node.left)){
            node = leftRotate(node);
        }

        if(isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }

        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }

        return node;
    }

}
