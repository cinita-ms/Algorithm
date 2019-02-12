public class LinkedStack<E> implements Stack<E> {
    private Node<E> top;

    @Override
    public void push(E item) {
        top = new Node(item, top);
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
