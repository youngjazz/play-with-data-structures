package main.my.heap;

import my.heap.MaxHeap;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * 最大堆测试
 *
 * @author leon
 * @since 2019-02-01
 */
class MaxHeapTest {
    static int n = 1000000;
    static Integer[] arr ;
    static Random random = new Random();


    @BeforeAll
    public static void init(){
        arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
    }

    @Test
    public void testAddHeap(){

        MaxHeap<Integer> maxHeap = new MaxHeap();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.removeMax();
        }

        for (int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]){
                throw new RuntimeException("MaxHeap error");
            }
        }

        System.out.println("Test MaxHeapCompleted.");
    }

    @Test
    public void testHeapify(){
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(arr);
        maxHeap.heapify();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.removeMax();
        }

        for (int i = 1; i < n; i++) {
            if(arr[i - 1] < arr[i]){
                throw new RuntimeException("MaxHeap error");
            }
        }

        System.out.println("Test MaxHeapCompleted.");
    }

}