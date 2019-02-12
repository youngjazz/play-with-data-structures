package my.array;

import my.stack.ArrayStack;

public class Main {

    public static void main(String[] args) {

        MyArray<Integer> arr = new MyArray();

        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }

        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        int i = arr.get(2);
        System.out.println(i);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.remove(3);
        System.out.println(arr);

        arr.remove(3);
        System.out.println(arr);

        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i1 = 0; i1 < 5; i1++) {
            stack.push(i1);
        }

        stack.pop();
        System.out.println(stack);
    }
}
