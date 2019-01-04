package me.cinita.stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

    private Object[] data;
    private int top;

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            capacity = 1;
        }
        data = new Object[capacity];
        top = -1;
    }

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
