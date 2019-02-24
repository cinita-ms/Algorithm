package stack;

import java.util.Stack;

public class StackAlgorithm {

    public static <E> void insertAtBottom(Stack<E> stack, E item) {
        if (stack == null || item == null) return;

        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }

        E top = stack.pop();
        insertAtBottom(stack, item);
        stack.push(top);
    }

    public static <E> void reverse(Stack<E> stack) {
        if (stack == null || stack.isEmpty()) return;

        E top = stack.pop();
        reverse(stack);
        insertAtBottom(stack, top);
    }
}
