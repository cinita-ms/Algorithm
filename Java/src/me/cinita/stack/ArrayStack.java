package me.cinita.stack;

import java.util.Arrays;

public class ArrayStack<E> implements Stack<E> {

    private static final int INVALID_POSITION = -1;
    private Object[] items;
    private int top;

    public ArrayStack(int capacity) {
        if (capacity < 1) {
            capacity = 1;
        }
        items = new Object[capacity];
        top = INVALID_POSITION;
    }

    @Override
    public void push(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item == null.");
        }

        if (top == items.length - 1) {
            int half = (items.length + 1) >>> 1;
            items = Arrays.copyOf(items, items.length + half);
            push(item);
        } else {
            items[++top] = item;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        if (top == INVALID_POSITION) {
            return null;
        }

        E result = (E) items[top];
        items[top--] = null; // Help gc.
        return result;
    }
}
