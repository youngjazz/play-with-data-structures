package my.recurrence;

/**
 * todo
 *
 * @author leon
 * @since 2019-01-07
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr){
        if(arr == null || arr.length == 0){
            throw new IllegalArgumentException("arr cannot be empty");
        }

        this.val = arr[0];
        ListNode current = this;
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        ListNode curr = this;
        while (curr != null) {
            stringBuilder.append(curr.val + "->");
            curr = curr.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();
    }
}
