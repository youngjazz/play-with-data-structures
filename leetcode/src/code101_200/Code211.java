package code101_200;

import java.util.HashMap;

/**
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 * <p>
 * 示例:
 * <p>
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 说明:
 * <p>
 * 你可以假设所有单词都是由小写字母 a-z 组成的。
 *
 * @author leon
 * @since 2019-02-12
 */
public class Code211 {
    private class Node {
        boolean isWord;
        HashMap<Character, Node> next;

        public Node() {
            this.isWord = false;
            this.next = new HashMap<>();
        }
    }

    class WordDictionary {

        Node root;

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            root = new Node();
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
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

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         *
         * 匹配"."即匹配任意字符, 实际就是需要遍历操作, 这里通过递归实现
         */
        public boolean search(String word) {
            return match(root, word, 0);
        }

        private boolean match(Node node, String word, int index) {

            //递归终止条件
            if(index == word.length()){
                return node.isWord;
            }

            char c = word.charAt(index);
            if(c != '.'){
                if(node.next.get(c) == null){
                    return false;
                }
                return match(node.next.get(c), word, index+1);
            }else{
                for (char nextchar : node.next.keySet()) {
                    if(match(node.next.get(nextchar), word, index+1)){
                        return true;
                    }
                }

                return false;
            }

        }
    }
}
