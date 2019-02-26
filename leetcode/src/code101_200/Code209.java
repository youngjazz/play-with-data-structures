package code101_200;

/**
 * 长度最小子数组
 * <p>
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 * <p>
 * 示例:
 * <p>
 * 输入: s = 7, nums = [2,3,1,2,4,3]
 * 输出: 2
 * 解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
 * 进阶:
 * <p>
 * 如果你已经完成了O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
 *
 * @author leon
 * @since 2019-02-20
 */
public class Code209 {
    static class Solution {
        public int minSubArrayLen(int s, int[] nums) {
            if(s <= 0 || nums == null)
                throw new IllegalArgumentException("Illigal Arguments");
            int l = 0 , r = -1; // nums[l...r]为我们的滑动窗口
            int sum = 0;
            int res = nums.length + 1;

            //只要左边界还没到最右边就可以继续
            while(l<nums.length){
                if(sum < s && r+1<nums.length){
                    sum += nums[++r];
                }else sum -= nums[l++];

                if(sum >= s){
                    res = Math.min(res, r-l+1);
                }
            }

            if(res == nums.length + 1)
                return 0;
            return res;
        }

    }

    public static void main(String[] args) {
        int s = 7;
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int i = new Solution().minSubArrayLen(s, nums);
        System.out.println(i);
    }
}
