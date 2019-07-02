package leetcode;

import base.BTNode;
import base.Node;
import base.NodeInt;

import java.util.*;

public class Solution {

    // 1. 两数之和
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, len = nums.length; i < len; ++i) {
            map.put(nums[i], i);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int anotherKey = target - entry.getKey();
            if (map.containsKey(anotherKey)) {
                result[0] = entry.getValue();
                result[1] = map.get(anotherKey);
                return result;
            }
        }

        return result;
    }

    public static int[] twoSum_1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0, len = nums.length; i < len; ++i) {
            for (int j = i + 1; j < len; ++j) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        return result;
    }

    // 2. 两数相加
    public static Node<Integer> addTwoNumbers(Node<Integer> l1, Node<Integer> l2) {
        Node<Integer> dummy = new Node<>();
        Node<Integer> tail = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int l1Data = l1 == null ? 0 : l1.data;
            int l2Data = l2 == null ? 0 : l2.data;
            int sum = l1Data + l2Data + carry;
            carry = sum / 10;
            Node<Integer> next = new Node<>();
            next.data = sum % 10;
            tail.next = next;
            tail = tail.next;
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }

        return dummy.next;
    }

    // 3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        for (int i = 0, len = s.length(); i < len; ++i) {
            char[] tempChars = s.substring(i).toCharArray();
            Set<Character> set = new HashSet<>();
            int j = 0;
            for (int tLen = tempChars.length; j < tLen; ++j) {
                if (set.contains(tempChars[j])) {
                    break;
                }

                set.add(tempChars[j]);
            }

            if (j > maxLen) {
                maxLen = j;
            }
        }

        return maxLen;
    }

    // 5. 最长回文子串
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return null;
        }

        int begin = 0, end = 0;
        for (int i = 0, len = s.length(); i < len; ++i) {
            int len1 = getAroundLen(s, i, i);
            int len2 = getAroundLen(s, i, i + 1);
            int finalLen = Math.max(len1, len2);
            if (finalLen > (end - begin + 1)) {
                begin = i - (finalLen - 1) / 2;
                end = i + finalLen / 2;
            }
        }

        if (end == begin) {
            return null;
        }

        return s.substring(begin, end + 1);
    }

    private static int getAroundLen(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }

        return right - left + 1;
    }

    // 19. 删除链表的倒数第N个节点
    public static <E> Node<E> removeNthFromEnd(Node<E> head, int n) {
        Node<E> first = head;
        Node<E> second = head;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }

        while (first != null) {
            first = first.next;
            second = second.next;
        }

        second.next = second.next.next;
        return head;
    }

    // 20. 有效的括号
    public boolean isValid(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (int i = 0, len = s.length(); i < len; ++i) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    char mapping = stack.pop();
                    if (mapping != map.get(c)) {
                        return false;
                    }
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    // 21. 合并两个有序链表
    public static NodeInt mergeTwoLists(NodeInt l1, NodeInt l2) {
        NodeInt dummy = new NodeInt();
        NodeInt head = dummy;
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }

            head = head.next;
        }

        head.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    // 53. 最大子序和
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return Integer.MIN_VALUE;
        }

        int result = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            } else {
                sum = num;
            }

            result = Math.max(result, sum);
        }

        return result;
    }

    // 70. 爬楼梯
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        if (n == 2) {
            return 2;
        }

        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    public static int climbStairs_1(int n) {
        int[] status = new int[n + 1];
        status[1] = 1;
        status[2] = 2;

        for (int i = 3; i <= n; ++i) {
            status[n] = status[n - 1] + status[n - 2];
        }

        return status[n];
    }

    // 101. 对称二叉树
    public static <E> boolean isSymmetric(BTNode<E> root) {
        return isMirror(root, root);
    }

    private static <E> boolean isMirror(BTNode<E> root1, BTNode<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return root1.data == root2.data
                && isMirror(root1.left, root2.right)
                && isMirror(root1.right, root2.left);
    }

    // 104. 二叉树的最大深度
    public static <E> int maxDepth(BTNode<E> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 121. 买卖股票的最佳时机
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    // 136. 只出现一次的数字
    public static int singleNumber(int[] nums) {
        // a ^ 0 = a
        // a ^ a = 0
        // a ^ b ^ a = a ^ a ^ b = 0 ^ b = b

        if (nums == null || nums.length < 1) {
            throw new IllegalArgumentException("Unsupported param.");
        }

        int r = nums[0];
        for (int i = 1, len = nums.length; i < len; ++i) {
            r ^= nums[i];
        }

        return r;
    }

    public static int singleNumber_1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }

        Iterator<Integer> iterator = set.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            throw new IllegalArgumentException("Unsupported param.");
        }
    }

    public static int singleNumber_2(int[] nums) {
        // 排序和遍历
        return 0;
    }
}
