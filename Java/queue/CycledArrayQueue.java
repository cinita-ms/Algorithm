public class CycledArrayQueue<E> implements Queue<E> {

    private Object[] items;
    private int head;
    private int tail;

    public CycledArrayQueue(int fixedCapacity) {
        if (fixedCapacity < 1) {
            throw new IllegalArgumentException("Fixed capacity must > 0.");
        }

        items = new Object[fixedCapacity];
        head = 0;
        tail = 0;
    }

    @Override
    public boolean enqueue(E item) {
        if ((tail + 1) % items.length == head) {
            return false;
        }

        items[tail++] = item;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() {
        if (head == tail) {
            return null;
        }

        return (E) items[head++];
    }

}
