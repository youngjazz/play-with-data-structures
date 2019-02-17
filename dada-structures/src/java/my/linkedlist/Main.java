package my.linkedlist;

/**
 *
 * @author leon
 * @since 2019-01-02
 */
public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(2, 666);
        System.out.println(linkedList);

        System.out.println(linkedList.contains(3));

        linkedList.addLast(999);
        System.out.println(linkedList);

        linkedList.remove(666);
        System.out.println("remove 666:"+linkedList);

        linkedList.removeFirst();
        System.out.println("removeFirst:"+linkedList);

        linkedList.removeLast();
        System.out.println("removeLast:"+linkedList);

    }
}
