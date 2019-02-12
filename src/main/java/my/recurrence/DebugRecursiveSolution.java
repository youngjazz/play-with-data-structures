package my.recurrence;

/**
 * 递归的debug方法
 *
 * @author leon
 * @since 2019-01-07
 */
public class DebugRecursiveSolution {

    public ListNode removeElements(ListNode head, int val, int depth){
        String depthString = getDepthString(depth);

        System.out.println(depthString + "Call remove "+val + " in "+head);

        if(head == null){
            System.out.println( depthString + "Return:"+head);
            return head;
        }

        ListNode current = removeElements(head.next, val, depth+1);
        System.out.println(depthString+"After remove "+val+":"+current);

        ListNode ret;
        if(head.val == val){
            ret = current;
        }else{
            head.next = current;
            ret = head;
        }
        System.out.println(depthString+"Return:"+head);
        return ret;
    }


    private String getDepthString(int depth){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            stringBuilder.append("--");
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        int[] num = {1,2,3,4,6,3,6};
        ListNode head = new ListNode(num);
        System.out.println(head);

        ListNode result = new DebugRecursiveSolution().removeElements(head, 6, 0);
        System.out.println(result);
    }
}
