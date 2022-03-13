package order;

/**
 * 快排实现
 *
 * @author leon
 * @since 2019-02-19
 */
public class QuickOrder {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int low, int high) {
        int pivot;
        if (low < high) {
            pivot = partition(arr, low, high);
            sort(arr, low, pivot - 1);
            sort(arr, pivot + 1, high);
        }
    }

    //选出一个支点，把它放在某一个人位置，使左边都比他小，右边都比他大
    private static int partition(int[] arr, int low, int high) {

        int pivot = arr[low];

        while (low < high) {
            //顺序很重要，从右边开始，等号比较关键
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            //这一步就开始swap比较关键
            swap(arr, low, high);

            while (low < high && arr[low] <= pivot) {
                low++;
            }
            swap(arr, low, high);
        }

        return low;
    }


    private static void swap(int[] L, int i, int j) {

        int temp = L[i];
        L[i] = L[j];
        L[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {12, 20, 5, 16, 15, 1, 9, 45, 23, 9, 9, 9};
        QuickOrder.sort(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
