package my.stack;

/**
 * @author leon
 * @date 2018/10/8
 */
public interface MyStack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
