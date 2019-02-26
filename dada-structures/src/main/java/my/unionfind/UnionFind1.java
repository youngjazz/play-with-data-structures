package my.unionfind;

/**
 * 并查集第一版(Quick Find), 基于数组实现
 * 时间复杂度: isConnected O(1); Union O(n)
 * @author leon
 * @since 2019-02-12
 */
public class UnionFind1 implements UF {
    private int[] id;

    public UnionFind1(int size) {
        id = new int[size];
        //初始化, 每个id[i]指向自己, 表示没有合并的元素
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    /** 查询元素p所在的集合编号*/
    private int find(int p){
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 合并元素p, q所属的集合
     * @param p
     * @param q
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = find(p);
        int qId = find(q);

        if(pId == qId){
            return;
        }

        //合并过程需要遍历一遍所有元素, 将两个元素所属集合编号置为相同
        for (int i = 0; i < id.length; i++) {
            if(id[i] == pId){
                id[i] = qId;
            }
        }
    }
}
