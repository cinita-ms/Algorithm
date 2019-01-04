package me.cinita;

public class Node<E> {
    public E data;
    public Node<E> next;

    public static <E> Node<E> create(E data, Node<E> next) {
        Node<E> n = new Node<>();
        n.data = data;
        n.next = next;
        return n;
    }
}
