package leetcode;

import java.util.Stack;

/**
 * leetcode 第20题
 *
 * @author leon
 * @since 2018-12-25
 */
public class code20 {

    public boolean isValid(String s) {

        Stack left = new Stack();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                left.push(c);
            } else {
                if(left.isEmpty()){
                    return false;
                }
                char c1 = (char) left.pop();
                if (c1 == '(' && c != ')') {
                    return false;
                } else if (c1 == '[' && c != ']') {
                    return false;
                } else if (c1 == '{' && c != '}') {
                    return false;
                }
            }
        }

        return left.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new code20().isValid("{}[]()"));
        System.out.println(new code20().isValid("{}]()"));
    }
}
