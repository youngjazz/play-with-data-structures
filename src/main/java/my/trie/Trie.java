package my.trie;

import java.util.TreeMap;

/**
 * 字典树
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
}
