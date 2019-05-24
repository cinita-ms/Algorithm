package common;

import util.Utils;

import java.util.*;

public class CommonAlgorithm {

    // 求众数，多党投票
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

    // 对于m = n % 2^k, 等价于取n的低k位
    public static int f0(int n, int k) {
        if (n < 0 || k < 0) {
            return -1;
        }

        return n | (int) (Math.pow(2, k) - 1);
    }

    // 两个整数m, n，其中 m < n，输出是0 ~ n-1范围内的m个随机整数的有序序列，不允许重复
    public static void f1(int m, int n) {
        if (m <= 0 || m > n) return;

        int[] result = new int[m];
        int index = 0;
        Random r = new Random();
        for (int i = 0; i < n; ++i) {
            if (r.nextInt(n - i) < m) {
                result[index++] = i;
                --m;
            }
        }

        Utils.println(Arrays.toString(result));
    }

    // 最大连续子向量和
    public static int f2(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int max = 0;
        for (int i = 0; i < a.length; ++i) {
            int sum = 0;
            for (int j = i; j < a.length; ++j) {
                sum += a[j];
                max = Math.max(sum, max);
            }
        }

        return max;
    }

    public static int f3(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int maxEnd = 0, maxSoFar = 0;
        for (int i = 0, len = a.length; i < len; ++i) {
            maxEnd = Math.max(i + maxEnd, 0);
            maxSoFar = Math.max(maxEnd, maxSoFar);
        }

        return maxSoFar;
    }

    // 求含n个元素的集合的幂集
    public static void f4(int[] a) {
        if (a == null || a.length == 0) return;

        for (int i = 0; i < Math.pow(2, a.length); ++i) {
            Queue<Integer> queue = new LinkedList<>();
            int m = i;
            while (m > 0) {
                queue.offer(m % 2);
                m = m >> 1;
            }

            List<Integer> results = new ArrayList<>(i);
            int n = 0;
            while (!queue.isEmpty()) {
                int d = queue.remove();
                if (d == 1) {
                    results.add(a[n++]);
                }
            }

            Utils.println(results);
        }
    }

    // A的B次方
    public static int f5(int a, int b) {
        if (b == 1) {
            return a;
        }

        return a * f5(a, b - 1);
    }

    public static int f6(int a, int b) {
        int result = 1;
        while (b > 0) {
            result *= a;
            --b;
        }

        return result;
    }

    // 10进制 -> 8进制
    public void f7(int source) {
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
