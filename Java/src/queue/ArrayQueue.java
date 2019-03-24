package queue;

public class ArrayQueue<E> implements Queue<E> {

    private static final int FIRST_POSITION = 0;
    private Object[] items;
    // The head pointer will point the start item.
    private int head;
    // The tail pointer will point the end item's next position.
    private int tail;

    public ArrayQueue(int fixedCapacity) {
        if (fixedCapacity < 1) {
            throw new IllegalArgumentException("Fixed capacity must > 0.");
        }

        items = new Object[fixedCapacity];
        head = FIRST_POSITION;
        tail = FIRST_POSITION;
    }

    @Override
    public boolean enqueue(E item) {
        if (item == null) {
            throw new IllegalArgumentException("item == null.");
        }

        // The tail pointer has moved to the end position + 1.
        if (tail == items.length) {
            // Have no space to insert item.
            if (head == FIRST_POSITION) {
                return false;
            }
            // Migrate the items to first position.
            else {
                System.arraycopy(items, head, items, FIRST_POSITION, tail - head);
                head = FIRST_POSITION;
                tail -= head;
            }
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

        E result = (E) items[head];
        items[head++] = null; // Help gc.
        return result;
    }
}
