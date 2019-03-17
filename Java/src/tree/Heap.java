package tree;

import base.Utils;

@SuppressWarnings("unchecked")
public class Heap<E extends Comparable<E>> {

    private Object[] items;
    private int size;

    public Heap(int fixedCapacity) {
        if (fixedCapacity < 2) {
            throw new IllegalArgumentException("Fixed capacity must > 1.");
        }

        items = new Comparable[fixedCapacity];
        size = 0;
    }

    public void insert(E data) {
        if (size >= items.length) return;

        items[++size] = data;
        int i = size;
        int parent;
        while ((parent = i / 2) > 0
                && ((E) items[i]).compareTo((E) items[parent]) > 0) {
            Utils.swap(items, i, parent);
            i = parent;
        }
    }


    public E removeTop() {
        if (size == 0) {
            return null;
        }

        E top = (E) items[1];
        items[1] = items[size];
        items[size--] = null;
        heapify(1);
        return top;
    }

    // From top to bottom.
    private void heapify(int index) {
        while (true) {
            int top = index;
            if (index * 2 <= size
                    && ((E) items[index]).compareTo((E) items[index * 2]) < 0) {
                top = index * 2;
            }

            if (index * 2 + 1 <= size
                    && ((E) items[top]).compareTo((E) items[index * 2 + 1]) < 0) {
                top = index * 2 + 1;
            }

            if (top == index) break;

            Utils.swap(items, top, index);
            index = top;
        }
    }
}
