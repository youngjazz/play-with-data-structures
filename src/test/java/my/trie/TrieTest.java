package my.trie;

import my.set.BSTSet;
import my.set.FileOperation;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Trie测试
 * 和BSTSet比较性能会好,尤其是数据量很大的时候会更加明显
 * @author leon
 * @since 2019-02-12
 */
class TrieTest {
    @Test
    void testTrie() {
        System.out.println("----------------------------");
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }

            for (String word : words) {
                trie.contains(word);
            }

            System.out.println("Trie words size:" + trie.getSize());
        }
    }


    @Test
    void testBSETSet() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTSet<String> set = new BSTSet<>();
            for(String word: words)
                set.add(word);

            for(String word: words)
                set.contains(word);

            System.out.println("Trie words size:" + set.getSize());
        }
    }
}