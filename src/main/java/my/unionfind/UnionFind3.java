package my.unionfind;

/**
 * 并查集实现第三版(第二版的基础上合并时对树做了size优化)
 *
 * @author leon
 * @since 2019-02-13
 */
public class UnionFind3 implements UF{

    //parent[i]表示元素指向的父节点
    private int[] parent;

    //sz[i]表示以i为根的集合中元素的个数
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        //初始化, 每个节点的父节点指向自己, 表示没有合并集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 查找元素p对应的集合
     * O(h)时间复杂度, h为树的高度
     */
    private int find(int p){
        if(p<0||p>parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        //不断查询自己的父节点, 直至根节点(特点parent[p]==p)
        while(p != parent[p]){
            p = parent[p];
        }

        return p;
    }

    /**
     * 合并元素p,q所属的集合
     * O(h)时间复杂度, h为树的高度
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot){
            return;
        }

        //根据两元素所在树的元素个数不同判断合并方向
        //操作:1.将元素个数少的集合合并到元素个数多的集合 2.集合个数增加
        //为何这样处理? 有没有问题?

        if(sz[pRoot] < sz[qRoot]){
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else{ // sz[qRoot] <= sz[pRoot]
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }

    }
}
