package code101_200;

/**
 * leetcode 第203题
 * <p>
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @author leon
 * @since 2019-01-03
 */
public class Code203 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(6);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(6);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

//        ListNode listNode = removeElements(node1, 6);

        ListNode listNode = removeElements2(node1, 6);
//        ListNode listNode = removeElements3(node1, 6);
        System.out.println(listNode);

    }

    public static ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }
        if (head == null) {
            return null;
        }

        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                ListNode delNode = pre.next;
                pre.next = delNode.next;
                delNode.next = null;
            } else {
                pre = pre.next;
            }
        }

        return head;
    }

    //使用虚拟头结点, 就无需考虑head头是val情况, 可简化删除逻辑
    public static ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.val == val) {
//                ListNode delNode = pre.next;
//                pre.next = delNode.next;
//                delNode.next = null;

                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }

        return dummyHead.next;
    }

    //递归实现
    public static ListNode removeElement3(ListNode head, int val){
        if(head == null){
            return null;
        }

        head.next = removeElement3(head.next, val);

        return head.val == val ? head.next : head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            ListNode curr = this;
            while (curr != null){
                stringBuilder.append(curr.val + "->");
                curr = curr.next;
            }
            stringBuilder.append("NULL");
            return stringBuilder.toString();
        }
    }
}
