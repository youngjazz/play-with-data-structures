package code301_400;

/**
 * 字符串中的第一个唯一字符
 *
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 * 案例:
 *
 * s = "leetcode"
 * 返回 0.
 *
 * s = "loveleetcode",
 * 返回 2.
 *
 *
 * 注意事项：您可以假定该字符串只包含小写字母。
 * @author leon
 * @since 2019-02-16
 */
public class Code387 {

    class Solution {
        public int firstUniqChar(String s) {
            int[] freq = new int[26];
            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i) - 'a'] ++;
            }

            for (int i = 0; i < s.length(); i++) {
                if(freq[s.charAt(i) - 'a']==1){
                    return i;
                }
            }

            return -1;
        }
    }

}
