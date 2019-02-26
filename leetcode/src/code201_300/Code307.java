package code201_300;

/**
 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

 update(i, val) 函数可以通过将下标为 i 的数值更新为 val，从而对数列进行修改。

 示例:

 Given nums = [1, 3, 5]

 sumRange(0, 2) -> 9
 update(1, 2)
 sumRange(0, 2) -> 8
 说明:

 数组仅可以在 update 函数下进行修改。
 你可以假设 update 函数与 sumRange 函数的调用次数是均匀分布的。
 *
 * 使用[线段树]实现
 *
 * @author leon
 * @since 2019-02-11
 */
public class Code307 {
    public interface Merger<E> {
        E merge(E a, E b);
    }

    public class SegmentTree<E> {
        private E[] data;
        private E[] tree;
        private Merger<E> merger;

        public SegmentTree(E[] arr, Merger<E> merger) {
            this.merger = merger;
            data = (E[]) new Object[arr.length];
            for (int i = 0; i < arr.length; i++) {
                data[i] = arr[i];
            }

            //思考下为何是四倍数组长度?
            tree = (E[]) new Object[4 * arr.length];
            buildSegmentTree(0, 0, arr.length - 1);
        }

        /**
         * 在treeIndex位置处构建表示区间[left, right]的线段树
         *
         * @param treeIndex
         * @param left
         * @param right
         */
        private void buildSegmentTree(int treeIndex, int left, int right) {
            //递归结束条件
            if (left == right) {
                tree[treeIndex] = data[left];
                return;
            }

            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);

//        int mid = (left + right) / 2;
            int mid = left + (right - left) / 2;
            //递归构建[left, mid], [mid + 1, right]范围的线段树
            buildSegmentTree(leftChildIndex, left, mid);
            buildSegmentTree(rightChildIndex, mid + 1, right);

            tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
        }

        /**
         * 返回完全二叉树, 某个元素的左孩子节点
         *
         * @param index
         * @return
         */
        private int leftChild(int index) {
            return 2 * index + 1;
        }

        private int rightChild(int index) {
            return 2 * index + 2;
        }

        public int size() {
            return data.length;
        }

        public E get(int index) {
            if (index < 0 || index > data.length) {
                throw new IllegalArgumentException("Index is illegal.");
            }

            return data[index];
        }

        /**
         * 将index位置的元素更新为e
         *
         * @param index
         * @param e
         */
        public void set(int index, E e) {
            if (index < 0 || index >= data.length) {
                throw new IllegalArgumentException("Index is illegal.");
            }
            //修改数组中的值
            data[index] = e;
            //修改线段树中的值
            set(0, 0, data.length - 1, index, e);
        }

        /**
         * 将以treeIndex为根的线段树中更新index的值为e
         */
        private void set(int treeIndex, int l, int r, int index, E e) {
            //递归终止条件
            if (l == r) {
                tree[treeIndex] = e;
                return;
            }

            int mid = l + (r - l) / 2;
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);

            if (index >= mid+1) {
                set(rightChildIndex, mid + 1, r, index, e);
            } else {
                set(leftChildIndex, l, mid, index, e);
            }

            tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
        }

        /**
         * 返回区间[left, right]的值
         *
         * @param left
         * @param right
         * @return
         */
        public E query(int left, int right) {
            if (left < 0 || left >= data.length || right < 0 || right >= data.length || left > right) {
                throw new IllegalArgumentException("Index is illegal.");
            }

            return query(0, 0, data.length - 1, left, right);
        }

        /**
         * 在以treeIndex为根的线段树中[l,r]范围内查询区间[queryL, queryR]的值
         *
         * @param treeIndex
         * @param l
         * @param r
         * @param queryL
         * @param queryR
         * @return
         */
        private E query(int treeIndex, int l, int r, int queryL, int queryR) {
            //递归终止条件
            if (l == queryL && r == queryR) {
                return tree[treeIndex];
            }

            int mid = l + (r - l) / 2;
            int leftChildIndex = leftChild(treeIndex);
            int rightChildIndex = rightChild(treeIndex);

            if (queryL >= mid + 1) {
                return query(rightChildIndex, mid + 1, r, queryL, queryR);
            } else if (queryR <= mid) {
                return query(leftChildIndex, l, mid, queryL, queryR);
            }

            E leftPartResult = query(leftChildIndex, l, mid, queryL, mid);
            E rightPartResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);
            return merger.merge(leftPartResult, rightPartResult);

        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('[');
            for (int i = 0; i < tree.length; i++) {
                if (tree[i] != null) {
                    res.append(tree[i]);
                } else res.append("null");

                if (i != tree.length - 1) {
                    res.append(", ");
                }
            }
            res.append(']');
            return res.toString();
        }
    }

    class NumArray {

        private SegmentTree<Integer> segmentTree;

        public NumArray(int[] nums) {
            if (nums.length > 0) {
                Integer[] data = new Integer[nums.length];
                for (int i = 0; i < nums.length; i++) {
                    data[i] = nums[i];
                }

                segmentTree = new SegmentTree<>(data, (a, b) -> a + b);

            }
        }

        public void update(int i, int val) {
            if(segmentTree == null){
                throw new IllegalArgumentException("Error");
            }
            segmentTree.set(i, val);
        }


        public int sumRange(int i, int j) {
            if (segmentTree == null) {
                throw new IllegalArgumentException("Segment Tree is null.");
            }

            return segmentTree.query(i, j);
        }
    }
}
