import java.util.Stack;

public class Temp {

    public static <E> void reverseStack(Stack<E> stack) {
        if (stack == null || stack.isEmpty()) {
            return;
        }

        E top = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, top);
    }

    private static <E> void insertAtBottom(Stack<E> stack, E e) {
        if (stack == null || e == null) {
            return;
        }

        if (stack.isEmpty()) {
            stack.push(e);
            return;
        }

        E top = stack.pop();
        insertAtBottom(stack, e);
        stack.push(top);
    }
}
