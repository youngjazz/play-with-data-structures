package code501_600;

/**
 * 字符串的排列
 * <p>
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * <p>
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * <p>
 * 示例1:
 * <p>
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * <p>
 * <p>
 * 示例2:
 * <p>
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * <p>
 * <p>
 * 注意：
 * <p>
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author leon
 * @since 2019-02-22
 */
public class Code567 {
    static class Solution {
        public boolean checkInclusion(String s1, String s2) {
            if (s1 == null || s2 == null || s2.length() < s1.length()) {
                return false;
            }

            int[] pre = new int[26];
            int[] cur = new int[26];

            //先对s1统计词频, s2部分统计
            for (int i = 0; i < s1.length(); i++) {
                pre[s1.charAt(i) - 'a']++;
                cur[s2.charAt(i) - 'a']++;
            }

            //i,j 表示窗口滑动的左右边界，窗口长度等于s1的长度
            int i = 0;
            int j = i + s1.length()-1;
            while (j < s2.length()-1) {
                if (isSame(pre, cur)) {
                    return true;
                }
                //窗口向右滑动，删除左边元素, 增加右边元素
                cur[s2.charAt(i++) - 'a']--;
                cur[s2.charAt(++j) - 'a']++;
            }

            return isSame(pre, cur);
        }

        private boolean isSame(int[] pre, int[] cur) {
            for (int i = 0; i < cur.length; i++) {
                if (pre[i] != cur[i]) {
                    return false;
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        boolean b = new Solution().checkInclusion("adc", "dcda");
        System.out.println(b);
    }
}
