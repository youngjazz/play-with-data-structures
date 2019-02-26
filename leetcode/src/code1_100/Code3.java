package code1_100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author leon
 * @since 2019-02-15
 */
public class Code3 {
    static class Solution {

        //暴力法
        public int lengthOfLongestSubstring(String s) {

            int maxSubStringLen = 0;
            HashSet<Character> characterSet = new HashSet<>();
            for (int left = 0; left < s.length(); left++) {
                for (int right = left; right < s.length(); right++) {
                    char c = s.charAt(right);
                    if (characterSet.contains(c)) {
                        maxSubStringLen = Math.max(maxSubStringLen, characterSet.size());
                        characterSet.clear();
                        break;
                    } else {
                        characterSet.add(c);
                    }
                }

            }

            return Math.max(maxSubStringLen, characterSet.size());
        }

        //滑动窗口法, 滑动窗口是数组/字符串问题中常用的抽象概念
        //这里使用HashSet来存储该窗口
        public int lengthOfLongestSubstring2(String s) {

            int n = s.length();
            Set<Character> set = new HashSet<>();
            int maxSublength = 0, i = 0, j = 0;
            while (i < n && j < n) {
                if (!set.contains(s.charAt(j))) {
                    set.add(s.charAt(j++));
                    maxSublength = Math.max(maxSublength, j - i);
                } else {
                    set.remove(s.charAt(i++));
                }
            }

            return maxSublength;
        }

        //抽口滑动优化
        //窗口滑动过程中,在最糟糕的情况下, 每个元素被i,j访问了两次
        //我们建立字符到索引的映射而不是使用集合判断一个字符是否存在,当找到重复字符时我们可以跳过该窗口
        //也就是说，在s[j]在[i,j)范围内存在相同的字符，索引为j'，我们不用一点点增加i，而是直接从j'+1开始遍历
        public int lengthOfLongestSubstring3(String s) {
            int n = s.length();
            Map<Character, Integer> map = new HashMap<>();
            int maxSublength = 0;
            for (int i = 0, j = 0; j < n; j++) {
                if (map.containsKey(s.charAt(j))) {
                    i = Math.max(map.get(s.charAt(j)), i);
                }
                maxSublength = Math.max(maxSublength, j - i + 1);
                map.put(s.charAt(j), j + 1);

            }

            return maxSublength;

        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(new Solution().lengthOfLongestSubstring2("aa"));
        System.out.println(new Solution().lengthOfLongestSubstring2("aab"));
    }
}
