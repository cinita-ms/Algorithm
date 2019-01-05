package me.cinita.queue;

public class LinkedQueue<E> implements Queue<E> {

    @Override
    public boolean enqueue(E item) {
        return false;
    }

    @Override
    public E dequeue() {
        return null;
    }
}
