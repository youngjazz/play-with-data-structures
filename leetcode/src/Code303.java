/**
 * 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
 * <p>
 * 示例：
 * <p>
 * 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()
 * <p>
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 说明:
 * <p>
 * 你可以假设数组不可变。
 * 会多次调用 sumRange 方法。
 * <p>
 * 动态规划实现(数组如果会变化就不能这么使用了, 见{@link Code307})
 *
 * @author leon
 * @since 2019-02-11
 */
public class Code303 {

    class NumArray {
        // 存储前i个元素的和
        // sum[0] = 0
        // sum(i, j) = sum[j+1] -sum[i];
        private int[] sum;

        public NumArray(int[] nums) {

            sum = new int[nums.length+1];
            sum[0] = 0;
            for (int i = 0; i < nums.length; i++) {
                sum[i+1] = sum[i] + nums[i];
            }
        }

        public int sumRange(int i, int j) {
            if (i < 0 || i >= sum.length || j < 0 || j >= sum.length || i > j) {
                throw new IllegalArgumentException("Index is illegal.");
            }

            return sum[j+1] - sum[i];
        }
    }

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */
}
