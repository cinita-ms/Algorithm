import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

    private Object[] items;
    private int top;

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            capacity = 1;
        }
        items = new Object[capacity];
        top = -1;
    }

    @Override
    public void push(E item) {
        if (top == items.length - 1) {
            items = Arrays.copyOf(items, items.length * 2);
            push(item);
        } else {
            items[++top] = item;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        if (top == -1) {
            return null;
        }
        E result = (E) items[top];
        items[top--] = null;
        return result;
    }
}
