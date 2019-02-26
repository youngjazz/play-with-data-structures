package my.segmenttree;

/**
 *
 * @author leon
 * @since 2019-02-11
 */
public interface Merger<E> {
    E merge(E a, E b);
}
