package me.cinita.stack;

import me.cinita.Node;

public class LinkedStack<E> implements Stack<E> {

    private Node<E> top;

    @Override
    public void push(E item) {
        top = Node.create(item, top);
    }

    @Override
    public E pop() {
        if (top == null) {
            return null;
        }

        E date = top.data;
        top = top.next;
        return date;
    }
}
