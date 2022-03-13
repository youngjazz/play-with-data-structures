package code201_300;

import java.util.ArrayList;

/**
 * 反转一个单链表。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author leon
 * @since 2019-02-25
 */
public class Code206 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode reverseList(ListNode head) {
            ArrayList<ListNode> nodeList = new ArrayList<>();
            ListNode current = head;
            while (current != null) {
                nodeList.add(current);
                current = current.next;
            }
            if (!nodeList.isEmpty()) {
                nodeList.get(0).next = null;
            }
            for (int i = nodeList.size() - 1; i > 0; i--) {
                nodeList.get(i).next = nodeList.get(i - 1);
            }

            return nodeList.isEmpty() ? null : nodeList.get(nodeList.size() - 1);
        }

        public ListNode reverseList2(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            ListNode next = null;

            while (cur != null){
                next = cur.next;
                cur.next = pre;

                pre = cur;
                cur = next;
            }
            //注意这里return的是pre
            return pre;
        }

        public ListNode reverseList3(ListNode head) {
            //递归终止条件
            if(head == null || head.next==null) return head;

            ListNode newHead = reverseList3(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
        }
    }
}
