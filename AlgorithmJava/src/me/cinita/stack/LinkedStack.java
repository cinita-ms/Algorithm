package me.cinita.stack;

import me.cinita.base.Node;

public class LinkedStack<E> implements Stack<E> {

    private Node<E> top;

    @Override
    public void push(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item == null.");
        }

        Node<E> temp = new Node<>();
        temp.data = item;
        temp.next = top;
        top = temp;
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
