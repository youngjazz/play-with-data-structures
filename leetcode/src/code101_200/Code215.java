package code101_200;

/**
 * 数组中第K大元素
 * <p>
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * <p>
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * <p>
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author leon
 * @since 2019-02-19
 */
public class Code215 {
    class Solution {
        public int findKthLargest(int[] nums, int k) {
            int pivot = partition(nums, 0, nums.length - 1);
            return nums[nums.length - k -1];
        }

        //三路快排，左边小于基准V,中间等于基准，右边大于基准
        private int  partition(int[] arr, int low, int high) {
            int start = low;
            int end = high;
            //每次排序使用第一个元素作为基准
            int pivot = arr[start];
            while (end > start) {
                //从后往前找到第一个比基准小的位置
                while (end > start && arr[end] >= pivot) {
                    end--;
                }

                //从前往后找到第一个比基准大的位置
                while (end > start && arr[start] < pivot) {
                    start++;
                }
                if(end < start){
                    break;
                }

                //交换
                swap(arr, start, end);
                end --;
                start ++;
            }


            //todo
            return 0;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
    }
}
