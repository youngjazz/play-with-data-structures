import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * 两个数组的交集
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出: [2]
 * 示例 2:
 * <p>
 * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出: [9,4]
 *
 * @author leon
 * @since 2019-01-30
 */
public class Code349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new TreeSet<>();

        for (int a : nums1) {
            set.add(a);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 1, 1};
        int[] nums2 = {2, 2};

        int[] intersection = new Code349().intersection(nums1, nums2);
        System.out.println(intersection);
    }
}
