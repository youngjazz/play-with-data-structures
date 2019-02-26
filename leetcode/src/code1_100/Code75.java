package code1_100;

/**
 * 颜色分类
 * <p>
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * <p>
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * <p>
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * <p>
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * 其实这就是一个'荷兰国旗问题'
 *
 * @author leon
 * @since 2019-02-19
 */
public class Code75 {
    static class Solution {
        public void sortColors(int[] nums) {
            //设置两个边界指针
            int zero = -1;
            int two = nums.length;
            //最终满足，[0,zero]之间是0，[two,nums.length-1]是2 中间是1
            int index = 0;
            while (index < two) {
                if (nums[index] == 1) {
                    index++;
                }else if(nums[index] == 2){
                    swap(nums, index, --two);
                }else {
                    //0
                    swap(nums, index++, ++zero);
                }
            }

        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 0};
        new Solution().sortColors(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
