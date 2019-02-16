package base;

public class Node<E> {
    public E data;
    public Node<E> next;

    public Node() {
        // Empty impl.
    }

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }
}
