package me.cinita.leetcode;

import me.cinita.base.BTNode;
import me.cinita.base.BTNoteInt;
import me.cinita.util.Utils;

import java.util.*;

public class EasyAlgorithm {

    // link: https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/
    // 从排序数组中删除重复项
    public static int removeDuplicates(int[] nums) {
        if (nums == null) {
            return 0;
        }

        if (nums.length <= 1) {
            return nums.length;
        }

        int i = 0;
        for (int j = 1; j < nums.length; ++j) {
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }

        return i + 1;
    }

    // 买卖股票的最佳时机 II
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1])
                maxProfit += prices[i] - prices[i - 1];
        }
        return maxProfit;
    }

    // 旋转数组
    public static void rotate(int[] nums, int k) {
        if (Utils.isEmpty(nums) || k <= 0 || k > nums.length) return;

        for (int i = 0; i < k; ++i) {
            int prev = nums[nums.length - 1];
            for (int j = 0; j < nums.length; ++j) {
                int temp = nums[j];
                nums[j] = prev;
                prev = temp;
            }
        }
    }

    public static void rotate_1(int[] nums, int k) {
        if (Utils.isEmpty(nums) || k <= 0 || k > nums.length) return;

        reverseArray(nums, 0, nums.length - 1);
        reverseArray(nums, 0, k - 1);
        reverseArray(nums, k, nums.length - 1);
    }

    private static void reverseArray(int[] target, int start, int end) {
        while (start < end) {
            Utils.swap(target, start++, end--);
        }
    }

    // 存在重复元素
    public static boolean containsDuplicate(int[] nums) {
        if (Utils.isEmpty(nums)) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            } else {
                set.add(num);
            }
        }

        return false;
    }

    // 只出现一次的数字
    public static int singleNumber(int[] nums) {
        if (Utils.isEmpty(nums)) {
            return -1;
        }

        int result = 0;
        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    // 两个数组的交集 II
    public static int[] intersect(int[] nums1, int[] nums2) {
        if (Utils.isEmpty(nums1) || Utils.isEmpty(nums2)) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num + 1));
            } else {
                map.put(num, 1);
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num)) {
                list.add(num);
                map.put(num, map.get(num) - 1);
                if (map.get(num) == 0) {
                    map.remove(num);
                }
            }
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); ++i) {
            result[i] = list.get(i);
        }

        return result;
    }

    // 加一
    public static int[] plusOne(int[] digits) {
        if (Utils.isEmpty(digits)) {
            return new int[0];
        }

        for (int i = digits.length - 1; i >= 0; --i) {
            digits[i]++;
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    // 移动零
    public static void moveZeroes(int[] nums) {
        if (Utils.isEmpty(nums)) return;

        for (int i = 0, j = 0; j < nums.length; ++j) {
            if (nums[j] != 0) {
                Utils.swap(nums, i++, j);
            }
        }
    }

    // 两数之和
    public static int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[]{-1, -1};
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            map.put(target - nums[i], i);
        }

        for (int i = 0; i < nums.length; ++i) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i) {
                return new int[]{i, map.get(nums[i])};
            }
        }

        return new int[]{-1, -1};
    }

    // 二叉树的层次遍历
    public static <E> List<List<E>> levelOrder(BTNode<E> root) {
        List<List<E>> result = new ArrayList<>();
        postOrder(root, 0, result);
        return result;
    }

    private static <E> void postOrder(BTNode<E> node, int level, List<List<E>> result) {
        if (node == null) return;

        if (level == result.size()) {
            result.add(new ArrayList<>());
        }

        result.get(level).add(node.data);
        if (node.left != null) {
            postOrder(node.left, level + 1, result);
        }

        if (node.right != null) {
            postOrder(node.right, level + 1, result);
        }
    }

    public static <E> List<List<E>> levelOrder_1(BTNode<E> root) {
        List<List<E>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<BTNode<E>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<E> r = new ArrayList<>();
            result.add(r);

            int levelLength = queue.size();
            for (int i = 0; i < levelLength; ++i) {
                BTNode<E> node = queue.poll();
                if (node == null) continue;

                r.add(node.data);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }

        return result;
    }

    // 验证二叉搜索树
    private static int lastValue = Integer.MIN_VALUE;

    public boolean isValidBST(BTNoteInt root) {
        if (root == null) {
            return true;
        }

        if (isValidBST(root.left)) {
            if (root.data > lastValue) {
                lastValue = root.data;
                return isValidBST(root.right);
            }
        }

        return false;
    }

    // 将有序数组转换为二叉搜索树
    public static BTNoteInt sortedArrayToBST(int[] nums) {
        if (Utils.isEmpty(nums)) {
            return null;
        }

        return rSortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static BTNoteInt rSortedArrayToBST(int[] nums, int start, int end) {
        int mid = ((end - start) >> 1) + start;
        BTNoteInt root = new BTNoteInt();
        root.data = nums[mid];
        if (mid > start) {
            root.left = rSortedArrayToBST(nums, start, mid - 1);
        }

        if (mid < end) {
            root.right = rSortedArrayToBST(nums, mid + 1, end);
        }

        return root;
    }

    // 第一个错误的版本
    public static int firstBadVersion(int n) {
        if (n <= 0) {
            return -1;
        }

        int start = 1;
        int end = n;
        while (start <= end) {
            if (start == end) {
                return isBadVersion(start) ? start : -1;
            }

            int mid = ((end - start) >> 1) + start;
            if (isBadVersion(mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1;
    }

    private static boolean isBadVersion(int version) {
        return false;
    }

}
