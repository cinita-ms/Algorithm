package string;

import com.sun.tools.javac.util.Pair;
import util.Utils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class StringAlgorithm {

    // TODO: 2019-03-31 BM KMP Horspool Boyer-Moore

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

    // 指定字符串T在字符串S中的重复次数
    public static int repeatCount(String src, String pattern) {
        if (src == null || pattern == null || pattern.length() > src.length()) {
            return 0;
        }

        int result = 0;

        for (int i = 0, srcLen = src.length(); i < srcLen; ++i) {
            boolean isSame = true;
            for (int j = 0, ptLen = pattern.length(); j < ptLen; ++j) {
                if (src.charAt(i + j) != pattern.charAt(j)) {
                    isSame = false;
                    break;
                }
            }

            if (isSame) {
                result++;
            }
        }

        return result;
    }

    // 最长重复子串LRS(Largest Repeating Substring), 获取位置和长度。
    public static Pair<Integer, Integer> largetRepeatingSubString(String src) {
        if (src == null || src.length() <= 1) return null;

        int len = src.length();
        Suffix[] suffixes = new Suffix[len];
        for (int i = 0; i < len; ++i) {
            Suffix temp = new Suffix();
            temp.s = src.substring(i);
            temp.p = i;
            suffixes[i] = temp;
        }

        Arrays.sort(suffixes, Comparator.comparing(o -> o.s));

        int maxLen = 0;
        int startPosition = 0;
        int i = 1;
        while (i < len) {
            String s1 = suffixes[i - 1].s;
            String s2 = suffixes[i].s;

            int j = 0;
            for (; j < Math.min(s1.length(), s2.length()); ++j) {
                if (s1.charAt(j) != s2.charAt(j)) {
                    break;
                }
            }

            if (maxLen < j) {
                maxLen = j;
                startPosition = i;
            }

            ++i;
        }

        return Pair.of(startPosition, maxLen);
    }

    private static class Suffix {
        String s;
        int p; // Keep old position.
    }

    // 替换空格， "hello world" -> "hello%2bworld"
    public static String replace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] chars = s.toCharArray();
        int count = 0;
        for (char c : chars) {
            if (c == ' ') {
                count++;
            }
        }

        if (count == 0) {
            return s;
        }

        int old_len = chars.length;
        int new_len = old_len + count * 2;
        chars = Arrays.copyOf(chars, new_len);
        for (int old_p = old_len - 1, new_p = new_len - 1; old_p >= 0; ) {
            if (chars[old_p] != ' ') {
                chars[new_p--] = chars[old_p--];
            } else {
                chars[new_p--] = 'b';
                chars[new_p--] = '2';
                chars[new_p--] = '%';
            }
        }

        return new String(chars);
    }
}
