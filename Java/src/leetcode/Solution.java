package leetcode;

import base.Node;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    public static int[] twoSum1(int[] nums, int target) {
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
}
