public class Node<E> {
    public E data;
    public Node<E> next;

    public Node(E data, Node<E> next) {
        this.data = data;
        this.next = next;
    }

    public static <E> Node<E> create(E data, Node<E> next) {
        Node<E> n = new Node<>();
        n.data = data;
        n.next = next;
        return n;
    }
}
