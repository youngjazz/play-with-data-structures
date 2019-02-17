package my.heap;

import my.array.MyArray;

/**
 * 最大堆
 *
 * @author leon
 * @since 2019-01-30
 */
public class MaxHeap<E extends Comparable<E>> {
    private MyArray<E> array;

    public MaxHeap() {
        this.array = new MyArray<>();
    }

    public MaxHeap(E[] arr) {
        this.array = new MyArray<>(arr);
        heapify();
    }

    public MaxHeap(int size) {
        this.array = new MyArray<>(size);
    }

    public int getSize() {
        return array.getSize();
    }

    public boolean isEmpty() {
        return array.getSize() == 0;
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("no parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 最后一个非叶子节点索引
    private int lastNotLeaf(){
        //最后一个叶子节点的父节点既是
        return parent(array.getSize()-1);
    }

    /**
     * 堆化
     *
     * 如果将n的数据, 逐个插入的堆的过程, 复杂度O(n*lgn)
     * 而使用数组直接堆化, 复杂度是O(n)
     */
    public void heapify(){
        int fromIndex = lastNotLeaf();
        //几乎抛弃了一半节点, 逐个下沉
        for (int i = fromIndex; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 向堆中添加元素
     *
     * @param e
     */
    public void add(E e) {
        array.addLast(e);
        siftUp(array.getSize() - 1);
    }

    public E top() {
        if (array.isEmpty()) {
            return null;
        }
        return array.getFirst();
    }

    /**
     * 取出最大值, (即取出堆顶元素)
     */
    public E removeMax() {
        E max = top();
        array.set(0, array.getLast());
        array.removeLast();
        siftDown(0);
        return max;
    }

    /**
     * 去除最大元素, 并替换
     * @param e
     * @return
     */
    public E replace(E e){
        E ret = top();
        array.set(0, e);
        siftDown(0);

        return ret;
    }

    /**
     * 索引为k的节点下移
     *
     * @param k
     */
    private void siftDown(int k) {

        //只看左子树(思考为何可以只看左子树就够了)
        while (leftChild(k) < array.getSize()) {
            // 此次循环中, index和k需要交换位置
            int index = leftChild(k);
            //存在右子树, 且右子树比左子树大
            if (index + 1 < array.getSize() && array.get(index + 1).compareTo(array.get(index)) > 0) {
                index++;
            } else if (array.get(k).compareTo(array.get(index)) >= 0) {
                //不存在右子树, 且大于左子树
                break;
            }

            if(array.get(k).compareTo(array.get(index))>=0){
                break;
            }

            array.swap(k, index);
            k = index;
        }

    }

    /**
     * 索引为k位置的元素进行上浮(和父节点进行比较)
     *
     * @param k
     */
    private void siftUp(int k) {

        while (k > 0 && array.get(k).compareTo(array.get(parent(k))) > 0) {
            array.swap(k, parent(k));
            k = parent(k);
        }
    }


}
