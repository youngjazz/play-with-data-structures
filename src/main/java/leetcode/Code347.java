package leetcode;

import my.queue.PriorityQueue;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 *
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 *
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 说明：
 *
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * @author leon
 * @since 2019-02-01
 */
public class Code347 {
    class Entity implements Comparable<Entity>{
        public int num;
        public int count;

        public Entity(int num, int count) {
            this.num = num;
            this.count = count;
        }

        @Override
        public int compareTo(Entity other) {
//            return this.count - other.count;

            //这样返回的目的是, 频次越小的元素, 优先级越高, 让最小的元素在大顶堆的最上面, 便于比较
            if(this.count < other.count){
                return 1;
            }else if(this.count == other.count){
                return 0;
            }else {
                return -1;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int num : nums) {
            if(treeMap.containsKey(num)){
                treeMap.put(num, treeMap.get(num)+1);
            }else{
                treeMap.put(num, 1);
            }
        }

        //这里使用我们自己写的优先队列, 如果使用java的优先队列逻辑刚好相反, 因为Java提供的优先队列内部实现是最小堆
        PriorityQueue<Entity> queue = new PriorityQueue<>();

        for (Integer num : treeMap.keySet()) {
            if(queue.getSize()<k){
                queue.enqueue(new Entity(num,treeMap.get(num)));
            }else if(treeMap.get(num) > queue.getFront().num){
                queue.dequeue();
                queue.enqueue(new Entity(num, treeMap.get(num)));
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while(queue.isEmpty()){
            res.add(queue.dequeue().num);
        }

        return res;
    }
}
