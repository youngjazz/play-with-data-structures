package leetcode;

import java.util.Stack;

/**
 * 字符串解码
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 * @author leon
 * @since 2019-01-29
 */
public class Code394 {
    static class Solution {
        public String decodeString(String s) {

            Stack<Integer> nums = new Stack<>();
            Stack<String> expression = new Stack<>();

            int index = 0;
            while (index < s.length()) {
                char c = s.charAt(index);
                if (Character.isDigit(c)) {
                    StringBuilder num = new StringBuilder();
                    while(Character.isDigit(s.charAt(index))){
                        num.append(s.charAt(index));
                        index++;
                    }
                    nums.push(Integer.parseInt(num.toString()));
                } else if (c == '['){
                    index++;
                    StringBuilder tempStr = new StringBuilder();
                    while (s.charAt(index) != '[' && s.charAt(index) != ']' && !Character.isDigit(s.charAt(index))){
                        tempStr.append(s.charAt(index));
                        index++;
                    }
                    expression.push(tempStr.toString());
                }else if (c == ']'){
                    index++;
                }else{
                    StringBuilder str = new StringBuilder();
                    while (index < s.length() && s.charAt(index) != '[' && s.charAt(index) != ']'){
                        str.append(s.charAt(index));
                        index++;
                    }
                    nums.push(1);
                    expression.push(str.toString());
                }
            }
            String result = "";
            while (!nums.isEmpty()){
                Integer times = nums.pop();
                String str = expression.pop();
                StringBuilder temp = new StringBuilder();
                for (Integer i = 0; i < times; i++) {
                    temp.append(str);
                }
                result = temp.toString()+result;
            }

            return result;
        }

    }


    public static void main(String[] args) {
//        String s2 = new Solution().decodeString("2[abc]3[cd]ef");
//        System.out.println(s2);
//        String s = new Solution().decodeString("3[a]2[bc]");
//        System.out.println(s);
        String s1 = new Solution().decodeString("3[a2[c]]");
        System.out.println(s1);
    }
}
