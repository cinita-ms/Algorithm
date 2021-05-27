package me.cinita.queue;

public class CycledArrayQueue<E> implements Queue<E> {

    private static final int FIRST_POSITION = 0;
    private Object[] items;
    // The head pointer will point the start item.
    private int head;
    // The tail pointer will point the end item's next position.
    private int tail;

    public CycledArrayQueue(int fixedCapacity) {
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

        int nextTail = (tail + 1) % items.length;
        if (nextTail == head) {
            return false;
        }

        items[tail] = item;
        tail = nextTail;
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() {
        if (head == tail) {
            return null;
        }

        E result = (E) items[head];
        items[head] = null;
        head = (head + 1) % items.length;
        return result;
    }
}
