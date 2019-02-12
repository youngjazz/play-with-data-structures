package my.recurrence;

/**
 * 递归求和
 *
 * @author leon
 * @since 2019-01-04
 */
public class Sum {

    public static void main(String[] args) {
        System.out.println(sum(new int[]{1, 2, 3, 4, 5}));
    }

    public static int sum(int[] arr) {
        return sum(arr, 0);
    }

    //arr[startIndex,length)求和
    public static int sum(int[] arr, int startIndex) {
        if (startIndex == arr.length) {
            return 0;
        }

        return arr[startIndex] + sum(arr, startIndex + 1);
    }
}
