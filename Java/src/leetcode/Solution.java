package leetcode;

import java.util.HashMap;
import java.util.Map;

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

    // 3. 无重复字符的最长子串
    public static int lengthOfLongestSubstring(String s) {
        return -1;
    }
}
