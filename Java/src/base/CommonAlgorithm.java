package base;

import java.util.*;

public class CommonAlgorithm {

    // 求三数之和
    public static List<List<Integer>> threeSum(int[] a) {
        List<List<Integer>> results = new ArrayList<>();

        if (a == null || a.length < 3) {
            return results;
        }

        Arrays.sort(a);

        for (int i = 0; i < a.length - 2; ++i) {
            if (i > 0 && a[i] == a[i - 1]) continue;

            int left = i + 1;
            int right = a.length - 1;
            int target = -a[i];

            twoSum(a, left, right, target, results);
        }

        return results;
    }

    private static void twoSum(int[] a, int left, int right, int target, List<List<Integer>> results) {
        while (left < right) {
            if (a[left] + a[right] == target) {
                ArrayList<Integer> triple = new ArrayList<>();
                triple.add(-target);
                triple.add(left);
                triple.add(right);
                results.add(triple);

                left++;
                right--;

                while (left < right && a[left] == a[left - 1]) {
                    left++;
                }

                while (left < right && a[right] == a[right + 1]) {
                    right--;
                }
            } else if (a[left] + a[right] < target) {
                left++;
            } else {
                right--;
            }
        }
    }

    // 求众数
    public static int majorityElement(int[] a) {
        if (a == null || a.length < 1) {
            return -1;
        }

        if (a.length == 1) {
            return 0;
        }

        int count = 1;
        int maj = a[0];

        for (int i = 1; i < a.length; ++i) {
            if (a[i] == maj) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    maj = a[i + 1];
                }
            }
        }

        return maj;
    }

    // 求缺失的第一个正数 ***
    public static int firstMissingPositive(int[] a) {
        if (a == null) {
            return 1;
        }

        for (int i = 0; i < a.length; i++) {
            while (a[i] > 0 && a[i] <= a.length && a[i] != (i + 1)) {
                int tmp = a[a[i] - 1];
                if (tmp == a[i]) {
                    break;
                }
                a[a[i] - 1] = a[i];
                a[i] = tmp;
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != i + 1) {
                return i + 1;
            }
        }

        return a.length + 1;
    }

    // 有效的括号
    private static HashMap<Character, Character> mappings = new HashMap<>(3);

    static {
        mappings.put(')', '(');
        mappings.put(']', '[');
        mappings.put('}', '{');
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (mappings.containsKey(c)) {
                if (stack.empty()) {
                    return false;
                }

                char top = stack.pop();
                if (top != mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    // 最长有效括号 ***
    public static int longestValidParentheses(String s) {
        if (s == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        int maxLen = 0;
        int accumulatedLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    accumulatedLen = 0;
                } else {
                    int matchedPos = stack.pop();
                    int matchedLen = i - matchedPos + 1;

                    if (stack.isEmpty()) {
                        accumulatedLen += matchedLen;
                        matchedLen = accumulatedLen;
                    } else {
                        matchedLen = i - stack.peek();
                    }

                    maxLen = Math.max(maxLen, matchedLen);
                }
            }
        }

        return maxLen;
    }

    // 逆波兰表达式求值
    public int evalRPN(String[] source) {
        Stack<Integer> stack = new Stack<>();
        String operators = "+-*/";
        for (String s : source) {
            if (!operators.contains(s)) {
                stack.push(Integer.valueOf(s));
                continue;
            }

            int a = stack.pop();
            int b = stack.pop();
            switch (s) {
                case "+":
                    stack.push(b + a);
                    break;
                case "-":
                    stack.push(b - a);
                    break;
                case "*":
                    stack.push(b * a);
                    break;
                default:
                    stack.push(b / a);
                    break;
            }
        }
        return stack.pop();
    }

    // 开根号
    public static int sqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException();
        } else if (x <= 1) {
            return x;
        }

        int start = 1;
        int end = x;
        while (start + 1 < end) {
            int mid = start + (end - start) >>> 2;
            if (mid == x / mid) {
                return mid;
            } else if (mid < x / mid) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (end > x / end) {
            return start;
        }

        return end;
    }

    // 翻转字符串
    public static String reverse(String src) {
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
        if (words.length - 1 > 0) {
            for (int i = words.length - 1; i >= 0; ++i) {
                target.append(" ").append(words[i]);
            }
        }

        return target.toString();
    }

    // 10进制 -> 8进制

    public void decimal2Octal(int source) {
        Stack<Integer> stack = new Stack<>();

        while (source > 0) {
            stack.push(source % 8);
            source /= 8;
        }

        StringBuilder s = new StringBuilder();
        while (stack.size() > 0) {
            s.append(stack.pop());
        }

        Utils.println(s);
    }
}
