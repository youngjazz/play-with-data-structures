package code201_300;

import java.util.ArrayList;
import java.util.List;

/**
 * 区间汇总
 *
 * 给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。
 *
 * 示例 1:
 *
 * 输入: [0,1,2,4,5,7]
 * 输出: ["0->2","4->5","7"]
 * 解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
 * 示例 2:
 *
 * 输入: [0,2,3,4,6,8,9]
 * 输出: ["0","2->4","6","8->9"]
 * 解释: 2,3,4 可组成一个连续的区间; 8,9 可组成一个连续的区间。
 * @author leon
 * @date 2019-03-27
 */
public class Code228 {
    class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<>();
            if(nums.length == 0){
                return res;
            }

            int i=0, j = 0;
            while (j < nums.length){
                if(j < nums.length - 1 && nums[j+1] - nums[j] == 1){
                    j++;
                }else{
                    if(i == j){
                        res.add(String.valueOf(nums[i]));
                    }else{
                        res.add(nums[i]+"->"+nums[j]);
                    }
                    i = ++j;
                }
            }

            return res;
        }
    }
}
