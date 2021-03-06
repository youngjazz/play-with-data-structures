package code601_700;

import java.util.TreeMap;

/**
 * 实现一个 MapSum 类里的两个方法，insert 和 sum。
 *
 * 对于方法 insert，你将得到一对（字符串，整数）的键值对。字符串表示键，整数表示值。如果键已经存在，那么原来的键值对将被替代成新的键值对。
 *
 * 对于方法 sum，你将得到一个表示前缀的字符串，你需要返回所有以该前缀开头的键的值的总和。
 *
 * 示例 1:
 *
 * 输入: insert("apple", 3), 输出: Null
 * 输入: sum("ap"), 输出: 3
 * 输入: insert("app", 2), 输出: Null
 * 输入: sum("ap"), 输出: 5
 *
 * @author leon
 * @since 2019-02-12
 */
public class Code677 {
    class MapSum {

        private class Node{
            public int val;
            public TreeMap<Character, Node> next;

            public Node(int val) {
                this.val = val;
                this.next = new TreeMap<>();
            }

            public Node() {
                this(0);
            }
        }

        private Node root;

        /** Initialize your data structure here. */
        public MapSum() {
            root = new Node();
        }

        public void insert(String key, int val) {
            Node cur = root;
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if(cur.next.get(c) == null){
                    cur.next.put(c, new Node());
                }
                cur = cur.next.get(c);
            }
            cur.val = val;
        }

        public int sum(String prefix) {
            Node cur = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                if(cur.next.get(c) == null){
                    return 0;
                }
                cur = cur.next.get(c);
            }
            return sum(cur);

        }

        private int sum(Node node) {
            int res = node.val;
            for (char c : node.next.keySet()) {
                res += sum(node.next.get(c));
            }

            return res;
        }
    }
}
