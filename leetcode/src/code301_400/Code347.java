package code301_400;

import java.util.*;

/**
 * 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * <p>
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 * <p>
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * @author leon
 * @since 2019-02-01
 */
public class Code347 {
    class Entity implements Comparable<Entity> {
        public int num;
        public int count;

        public Entity(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Entity other) {

            if (this.count < other.count) {
                return -1;
            } else if (this.count == other.count) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    private class EntityComparator implements Comparator<Entity>{

        @Override
        public int compare(Entity a, Entity b) {
            return a.count - b.count;
        }
    }
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            if (treeMap.containsKey(num)) {
                treeMap.put(num, treeMap.get(num) + 1);
            } else {
                treeMap.put(num, 1);
            }
        }

        //这里使用我们java提供的优先队列
        // 这里有一个比较好的设计:就是优先队列构造器可以接受一个比较器, 实现我们自定义的比较规则
        // 比如String默认是字典排序,我们改成长度排序就可以使用自定义的比较器class

//        PriorityQueue<Entity> queue = new PriorityQueue<>(new EntityComparator());
        PriorityQueue<Entity> queue = new PriorityQueue<>();

        for (Integer num : treeMap.keySet()) {
            if (queue.size() < k) {
                queue.add(new Entity(num, treeMap.get(num)));
            } else if (treeMap.get(num) > queue.peek().count) {
                queue.remove();
                queue.add(new Entity(num, treeMap.get(num)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!queue.isEmpty()) {
            res.add(queue.remove().num);
        }

        return res;
    }

    public static void main(String[] args) {
        new Code347().topKFrequent(new int[]{3,0,0,1}, 1);
    }
}
