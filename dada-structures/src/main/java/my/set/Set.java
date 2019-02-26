package my.set;

/**
 * 集合
 *
 * @author leon
 * @since 2019-01-28
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
