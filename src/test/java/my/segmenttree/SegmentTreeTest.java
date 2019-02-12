package my.segmenttree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 线段树测试类
 *
 * @author leon
 * @since 2019-02-11
 */
class SegmentTreeTest {
    Integer[] nums = {-2, 0, 3, -5, 2, -1};

    @Test
    public void test() {
        //使用匿名内部类
/*        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });*/

        //使用lambda表达式
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> {

            return a + b;

        });

//        System.out.println(segmentTree.size());

        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(2, 5));
        System.out.println(segmentTree.query(0, 5));
    }
}