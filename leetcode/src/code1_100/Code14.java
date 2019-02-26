package code1_100;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 *
 * @author leon
 * @since 2019-02-16
 */
public class Code14 {
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if(strs == null || strs.length == 0){
                return "";
            }

            //假设第一个字符串为共同前缀，后续过程不断缩减至真正前缀
            String prefix = strs[0];
            for (int i = 1; i < strs.length; i++) {
                while (strs[i].indexOf(prefix) != 0){
                    prefix = prefix.substring(0, prefix.length()-1);
                    if(prefix.isEmpty()) return "";
                }
            }

            return prefix;
        }

    }

    public static void main(String[] args) {
        String[] arr = new String[]{"flower"};
        System.out.println(new Solution().longestCommonPrefix(arr));
    }
}
