package my.stack;

import java.util.Random;

/**
 * ArrayStack 与 LinkedListStack 测试对比
 *
 * @author leon
 * @since 2019-01-02
 */
public class CompareMain {

    private static Random random = new Random();

    private static double test(MyStack<Integer> stack, int opsCount){
        long start = System.currentTimeMillis();
        for (int i = 0; i < opsCount; i++) {
            stack.push(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opsCount; i++) {
            stack.pop();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    public static void main(String[] args) {
        int opsCount = 10000000;
        ArrayStack arrayStack = new ArrayStack();
        double time = test(arrayStack, opsCount);
        System.out.println("ArrayStack测试用时:"+time/1000.00);

        LinkedListStack linkedListStack = new LinkedListStack();
        double time2= test(linkedListStack, opsCount);
        System.out.println("LinkedListStack测试用时:"+time2/1000.00);
    }
}
