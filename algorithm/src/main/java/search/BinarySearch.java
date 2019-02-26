package search;

/**
 * 二分查找
 *
 * @author leon
 * @since 2019-02-17
 */
public class BinarySearch<T extends Comparable> {
    public int binarySearch(T[] arr, int n, T target) {
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] == target){
                return mid;
            }else if(target.compareTo(arr[mid]) > 0){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //创建有序数组
        int n = 1000000;
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        long start = System.nanoTime();
        int i = binarySearch.binarySearch(arr, n, 25);
        long ent = System.nanoTime();
        System.out.println(i == 25);
        System.out.println("costs:" + (ent-start));
    }
}
