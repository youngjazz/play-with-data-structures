package my.stack;


import my.array.MyArray;

/**
 * @author leon
 * @date 2018/10/8
 */
public class ArrayStack<E> implements MyStack{

    MyArray<E> array;

    public ArrayStack() {
        this.array = new MyArray<>();
    }

    public ArrayStack(int capacity) {
        this.array = new MyArray<>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public void push(Object o) {
        array.addLast((E) o);
    }

    @Override
    public Object pop() {
        return array.removeLast();
    }

    @Override
    public Object peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: [");
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if(i != array.getSize() - 1){
                res.append(", ");
            }
        }
        res.append("] top");

        return res.toString();
    }
}
