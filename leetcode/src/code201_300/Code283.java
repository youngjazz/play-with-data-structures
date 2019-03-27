package code201_300;

/**
 * 移动0
 * <p>
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 * 说明:
 * <p>
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author leon
 * @since 2019-02-17
 */
public class Code283 {
    //swap版
    class Solution {
        public void moveZeroes(int[] nums) {

            int index = 0;//记录将要被交换的未知
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] != 0){
                    swap(nums, index++, i);
                }
            }

        }

        private void swap(int[] nums, int i, int j){
            int temp = nums[j];
            nums[j]= nums[i];
            nums[i] = temp;
        }
    }

    //覆盖版
    class Solution2 {
        //遇到非0元素直接往前移动即可
        public void moveZeroes(int[] nums) {

            int index = 0; //该位置表示需要被覆盖的位置
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] != 0){
                    nums[index++] = nums[i];
                }
            }

            //未被覆盖的元素最终给0
            for(int i = index; i<nums.length; i++){
                nums[i] = 0;
            }
        }

    }
}
