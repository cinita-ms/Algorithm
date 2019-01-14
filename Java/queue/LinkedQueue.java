public class LinkedQueue<E> implements Queue<E> {

    private Node<E> head;
    private Node<E> tail;

    @Override
    public boolean enqueue(E item) {
        Node<E> node = new Node<>();
        if (head == null) {
            head = node;
        }
        node.data = E;
        tail.next = node;
        tail = node;
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
