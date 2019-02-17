package my.hashtable;

import java.util.TreeMap;

/**
 * 哈希表
 *
 * 冲突解决办法：
 * 链地址法
 * 开发地址法（索引对任意键开放，冲突了向后探索（线性探测，平方探测等）性能较低）
 * 再哈希 Rehashing
 *
 * @author leon
 * @since 2019-02-17
 */
public class HashTable<K, V> {

    private static final int initCapacity = 7;
    private static final int lowerTol = 2;
    private static final int upperTol = 10;

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable() {
        this(initCapacity);
    }

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            //初始化
            hashtable[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            map.put(key, value);
            size++;
            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            if (size <= lowerTol * M && M > initCapacity) {
                resize(M / 2);
            }
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        //初始化
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        for (int i = 0; i < M; i++) {
            for (K key : hashtable[i].keySet()) {
                newHashTable[hash(key)].put(key, hashtable[i].get(key));
            }
        }

        this.M = newM;
        this.hashtable = newHashTable;

    }
}
