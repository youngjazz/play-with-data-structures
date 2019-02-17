import java.util.TreeMap;

/**
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * @author leon
 * @since 2019-02-12
 */
public class Code208 {
    private class Node{
        private boolean isWord;
        TreeMap<Character, Node> next;

        public Node() {
            isWord = false;
            next = new TreeMap<>();
        }
    }
    class Trie {

        private Node root;

        /** Initialize your data structure here. */
        public Trie() {
            root = new Node();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            Node cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(cur.next.get(c) == null){
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }

            if(!cur.isWord){
                cur.isWord = true;
            }
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            Node cur =  root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if(cur.next.get(c) == null){
                    return false;
                }
                cur = cur.next.get(c);
            }

            return cur.isWord;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            Node cur =  root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if(cur.next.get(c) == null){
                    return false;
                }
                cur = cur.next.get(c);
            }

            return true;
        }
    }
}
