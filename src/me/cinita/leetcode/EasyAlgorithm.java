package me.cinita.leetcode;

import me.cinita.base.BTNode;
import me.cinita.base.BTNodeInt;
import me.cinita.base.Node;
import me.cinita.base.NodeInt;
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

    // 删除链表的倒数第N个节点
    public static <E> Node<E> removeNthFromEnd(Node<E> head, int n) {
        Node<E> fast = head;
        Node<E> slow = head;
        for (int index = 1; index <= n; ++index) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return head;
    }

    // 反转链表
    public static <E> Node<E> reverseList(Node<E> head) {
        Node<E> prev = null;
        Node<E> cur = head;
        while (cur != null) {
            Node<E> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    // 合并两个有序链表
    public static NodeInt mergeTwoLists(NodeInt l1, NodeInt l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        NodeInt dump = new NodeInt();
        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                dump.next = l1;
                l1 = l1.next;
            } else {
                dump.next = l2;
                l2 = l2.next;
            }

            dump = dump.next;
        }

        if (l1 == null) {
            dump.next = l2;
        } else {
            dump.next = l1;
        }

        return dump.next;
    }

    //  二叉树的最大深度
    public static <E> int maxDepth(BTNode<E> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 验证二叉搜索树
    private static int lastValue = Integer.MIN_VALUE;

    public boolean isValidBST(BTNodeInt root) {
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

    // 对称二叉树
    public static <E> boolean isSymmetric(BTNode<E> root) {
        return rIsSymmetric(root, root);
    }

    private static <E> boolean rIsSymmetric(BTNode<E> root1, BTNode<E> root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        return root1.data == root2.data && rIsSymmetric(root1.left, root2.right) && rIsSymmetric(root1.right, root2.left);
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
        postOrder(node.left, level + 1, result);
        postOrder(node.right, level + 1, result);
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

            int levelLength = queue.size(); // Can't use queue's size to iterate.
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

    // 将有序数组转换为二叉搜索树
    public static BTNodeInt sortedArrayToBST(int[] nums) {
        if (Utils.isEmpty(nums)) {
            return null;
        }

        return rSortedArrayToBST(nums, 0, nums.length - 1);
    }

    private static BTNodeInt rSortedArrayToBST(int[] nums, int start, int end) {
        int mid = ((end - start) >> 1) + start;
        BTNodeInt root = new BTNodeInt();
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

    // 最大子序和
    public static int maxSubArray(int[] nums) {
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

    //  打家劫舍
    public static int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[nums.length - 1];
    }

    public static int rob_1(int[] nums) {
        int prev = 0;
        int cur = 0;
        for (int num : nums) {
            int temp = cur;
            cur = Math.max(cur, prev + num);
            prev = temp;
        }

        return cur;
    }
}
