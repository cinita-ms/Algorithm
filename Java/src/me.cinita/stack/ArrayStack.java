package me.cinita.stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

    private static final int DEFAULT_CAP = 16;
    private Object[] data = new Object[DEFAULT_CAP];
    private int top;

    @Override
    public void push(E item) {
        if (top == data.length - 1) {
            data = Arrays.copyOf(data, data.length * 2);
            push(item);
        } else {
            data[++top] = item;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        E result = (E) data[top];
        data[top--] = null;
        return result;
    }
}
