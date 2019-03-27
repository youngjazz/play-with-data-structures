package code1_100;

import org.omg.CORBA.RepositoryIdHelper;

/**
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author leon
 * @date 2019-03-27
 */
public class Code5 {
    class Solution {
        int maxLen = 0;
        String maxString = "";

        public String longestPalindrome(String s) {
            for (int i = 0; i < s.length(); i++) {
                find(s, i, 0);
                find(s, i, 1);
            }

            return maxString;
        }

        public void find(String s,int i, int flag) {
            int left = i;
            int right = i + flag;

            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
                left --;
                right ++;
            }

            if(right - left + 1 > maxLen){
                maxLen = right - left + 1;
                maxString = s.substring(left + 1, right);
            }
        }
    }
}
