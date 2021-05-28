package me.cinita.queue;

import me.cinita.base.Node;

public class LinkedQueue<E> implements Queue<E> {

    // The head pointer will point the start item.
    private Node<E> head;
    // The tail pointer will point the end item.
    private Node<E> tail;

    @Override
    public boolean enqueue(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item == null.");
        }

        Node<E> node = new Node<>();
        node.data = item;

        // For empty queue.
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }

        return true;
    }

    @Override
    public E dequeue() {
        if (head == null) {
            return null;
        }

        E item = head.data;
        head = head.next;
        return item;
    }
}
