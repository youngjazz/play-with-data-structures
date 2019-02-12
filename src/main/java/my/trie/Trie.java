package my.trie;

import java.util.TreeMap;

/**
 * Trie，又称字典树、前缀树、单词查找树或键树，是一种树形结构，是一种哈希树的变种。
 * 典型应用是用于统计，排序和保存大量的字符串（但不仅限于字符串），所以经常被搜索引擎系统用于文本词频统计。
 * 它的优点是：利用字符串的公共前缀来减少查询时间，最大限度地减少无谓的字符串比较，查询效率比哈希树高。
 * 缺点: 最大的问题就是空间, 每个node实际只承载了一个字符, 而下一个字符需要映射来维护(可使用压缩字典树Compressed Trie解决, 但压缩字典树维护成本高,具体参见https://dsqiu.iteye.com/blog/1705697)
 *
 * 扩展: 三分搜索树
 *
 * @author leon
 * @since 2019-02-12
 */
public class Trie {
    private class Node{
        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    /**
     * 获取Trie中单词的数量
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 向Trie中添加单词
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        //历史数据中含有相同, 但未标记为word才添加
        if(!cur.isWord){
            cur.isWord = true;
            size++;
        }
    }

    /**
     * Trie中是否包含指定单词
     * @param word
     * @return
     */
    public boolean contains(String word){
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }

        //注意这里不能直接返回true
        return cur.isWord;
    }

    /**
     * 查看Trie中是否有以{prefix}为前缀的单词
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix){
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null){
                return false;
            }
            cur = cur.next.get(c);
        }
        /**区别于contains()*/
        return true;
    }

    public void delete(String word){

    }
}
