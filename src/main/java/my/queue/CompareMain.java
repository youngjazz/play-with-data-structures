package my.queue;

import java.util.Random;

/**
 * ArrayQueue 与 LoopQueue性能对比
 *
 * @author leon
 * @since 2018-12-25
 */
public class CompareMain {

    private static Random random = new Random();

    private static double testQueue(Queue<Integer> queue, int opCount){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < opCount; i++) {
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < opCount; i++) {
            queue.dequeue();
        }

        long endTime = System.currentTimeMillis();
        return (endTime - startTime)/1000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue, time:"+time1+" s");

        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);

        System.out.println("LoopQueue, time:"+time2+" s");

        LinkedListQueue linkedListQueue = new LinkedListQueue();
        double time3 = testQueue(linkedListQueue, opCount);
        System.out.println("LinkedListQueue, time:"+time3 +" s");
    }

}
