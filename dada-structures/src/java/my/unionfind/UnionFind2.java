package my.unionfind;

/**
 * 并查集实现第二版(quick union)
 * 仍然是基于数组存储关系, 但构建的是一种比较奇怪的树结构(森林结构), 由孩子节点指向父亲节点
 *
 * @author leon
 * @since 2019-02-13
 */
public class UnionFind2 implements UF{

    //parent[i]表示元素指向的父节点
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];
        //初始化, 每个节点的父节点指向自己, 表示没有合并集合
        for (int i = 0; i < size; i++) {
            parent[i] = i;
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

        parent[pRoot] = qRoot;
    }
}
