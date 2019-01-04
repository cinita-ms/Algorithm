package me.cinita.stack;

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

    static class Node<E> {
        E data;
        Node<E> next;

        static <E> Node<E> create(E data, Node<E> next) {
            Node<E> n = new Node<>();
            n.data = data;
            n.next = next;
            return n;
        }
    }

}
