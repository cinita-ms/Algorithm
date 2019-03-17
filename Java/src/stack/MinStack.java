package stack;

import java.util.Stack;

public class MinStack<E extends Comparable<E>> implements stack.Stack<E> {

    private Stack<E> stack;
    private Stack<E> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    @Override
    public void push(E item) {
        stack.push(item);

        if (minStack.isEmpty()) {
            minStack.push(item);
        } else if (minStack.peek().compareTo(item) >= 0) {
            minStack.push(item);
        }
    }

    @Override
    public E pop() {
        if (stack.peek().compareTo(minStack.peek()) == 0) {
            minStack.pop();
        }
        return stack.pop();
    }
}
