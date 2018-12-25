package my.queue;

/**
 * todo
 *
 * @author leon
 * @since 2018-12-25
 */
public interface Queue<E> {
    void enqueue(E e);

    E dequeue();

    E getFront();

    int getSize();

    boolean isEmpty();
}
