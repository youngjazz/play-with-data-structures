package code301_400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 计算两数组之间的交集
 * 区别Code349
 * <p>
 * 示例 1:
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2,2]
 * <p>
 * 示例 2:
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [4,9]
 *
 * @author leon
 * @since 2019-01-30
 */
public class Code350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null){
            return null;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums1) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }


        ArrayList<Integer> list = new ArrayList();

        for (int num : nums2) {
            if(map.containsKey(num)){
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0){
                    map.remove(num);
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
//        int[] intersect = new Code350().intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4});
        int[] intersect = new Code350().intersect(null, null);
        System.out.println(intersect);
    }
}
