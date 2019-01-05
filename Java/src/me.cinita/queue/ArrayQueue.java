package me.cinita.queue;

public class ArrayQueue<E> implements Queue<E> {

    private Object[] items;
    private int head;
    private int tail;

    public ArrayQueue(int fixedCapacity) {
        if (fixedCapacity < 1) {
            throw new IllegalArgumentException("Fixed capacity must > 1.");
        }

        items = new Object[fixedCapacity];
        head = 0;
        tail = 0;
    }

    @Override
    public boolean enqueue(E item) {
        // The tail pointer has moved to the end position + 1.
        if (tail == items.length) {
            // Have no space to insert item.
            if (head == 0) {
                return false;
            }
            // Migrate the items to first position.
            else {
                System.arraycopy(items, head, items, 0, tail - head);
                head = 0;
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

        return (E) items[head++];
    }
}
