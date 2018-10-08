package array;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] arr = new int[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] scores = new int[]{100, 99, 68};
        Arrays.stream(scores).forEach(System.out::println);
    }
}
