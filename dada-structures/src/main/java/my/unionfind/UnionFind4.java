package my.unionfind;

/**
 * 并查集实现第四版(第三版的基础上合并时对树做了size优化,改为rank优化 防止出现深度过大的场景,极端情况可能是单链表)
 *
 * @author leon
 * @since 2019-02-13
 */
public class UnionFind4 implements UF{

    //parent[i]表示元素指向的父节点
    private int[] parent;

    //sz[i]表示以i为根的集合中元素的个数
    private int[] rank;

    public UnionFind4(int size) {
        parent = new int[size];
        rank = new int[size];

        //初始化, 每个节点的父节点指向自己, 表示没有合并集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 1;
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

        //根据两元素所在树的高度不同判断合并方向
        //操作:1.将高度小的集合合并到元高度大的集合 2.集合高度变化

        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        } else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else{ // sz[qRoot] == sz[pRoot]
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }
}
