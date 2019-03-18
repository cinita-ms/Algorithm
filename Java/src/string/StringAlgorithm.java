package string;

import base.Utils;

import java.util.Stack;

public class StringAlgorithm {

    // 数组判断是否是回文.
    public static boolean isPalindromeByArray(String src) {
        if (src == null || src.length() < 2) return false;

        for (int i = 0; i < src.length(); ++i) {
            int endPosition = src.length() - 1 - i;
            if (i >= endPosition) break;

            if (src.charAt(i) != src.charAt(endPosition)) {
                return false;
            }
        }

        return true;
    }

    // 栈判断是否是回文.
    public static boolean isPalindromeByStack(String src) {
        if (src == null || src.length() < 2) return false;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < src.length(); ++i) {
            stack.push(src.charAt(i));
        }

        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty()) {
            builder.append(stack.pop());
        }

        return builder.toString().equals(src);
    }

    // 翻转字符串
    public static String reverseChars(String src) {
        if (src == null || src.length() < 2) {
            return src;
        }

        char[] chars = src.toCharArray();
        int i = 0, j = src.length() - 1;
        while (i < j) {
            Utils.swap(chars, i++, j--);
        }

        return new String(chars);
    }

    // 翻转字符串里面的单词
    public static String reverseWords(String src) {
        if (src == null) {
            return null;
        }

        String[] words = src.split(" ");
        if (words.length < 1) {
            return null;
        }

        StringBuilder target = new StringBuilder(words.length - 1);
        if (words.length - 2 > 0) {
            for (int i = words.length - 2; i >= 0; ++i) {
                target.append(" ").append(words[i]);
            }
        }

        return target.toString();
    }
}
