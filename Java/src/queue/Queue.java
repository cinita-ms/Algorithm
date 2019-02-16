package queue;

public interface Queue<E> {

    boolean enqueue(E item);

    E dequeue();
}
