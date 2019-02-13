package my.unionfind;

/**
 * 并查集(解决连接问题)
 *
 * @author leon
 * @since 2019-02-12
 */
public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
