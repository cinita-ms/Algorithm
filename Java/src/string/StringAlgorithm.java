package string;

import java.util.Stack;

public class StringAlgorithm {

    // Whether the string is a palindrome.
    public static boolean isPalindromeByArray(String source) {
        if (source == null || source.length() < 2) return false;

        for (int i = 0; i < source.length(); ++i) {
            int endPosition = source.length() - 1 - i;
            if (i >= endPosition) {
                if (source.charAt(i) != source.charAt(endPosition)) {
                    return false;
                }
            } else {
                break;
            }
        }

        return true;
    }

    public static boolean isPalindromeByStack(String source) {
        if (source == null || source.length() < 2) return false;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < source.length(); ++i) {
            stack.push(source.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.toString().equals(source);
    }
}
