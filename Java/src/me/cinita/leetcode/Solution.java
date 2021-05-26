package me.cinita.leetcode;

import me.cinita.base.BTNode;
import me.cinita.base.BTNodeInt;
import me.cinita.base.Node;
import me.cinita.base.NodeInt;
import me.cinita.util.Utils;

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

    // 4. 寻找两个有序数组的中位数
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (Utils.isEmpty(nums1) && Utils.isEmpty(nums2)) {
            throw new IllegalArgumentException();
        }

        int l1 = Utils.isEmpty(nums1) ? 0 : nums1.length;
        int l2 = Utils.isEmpty(nums2) ? 0 : nums2.length;
        if (l1 == 0) {
            if (l2 % 2 == 0) {
                return (nums2[(l2 >> 1) - 1] + nums2[l2 >> 1]) / 2.0d;
            } else {
                return nums2[l2 >> 1];
            }
        }

        if (l2 == 0) {
            if (l1 % 2 == 0) {
                return (nums1[(l1 >> 1) - 1] + nums1[l1 >> 1]) / 2.0d;
            } else {
                return nums1[l1 >> 1];
            }
        }

        int l = l1 + l2;
        int[] result = new int[l];
        int i = 0, j = 0, k = 0;
        while (i < l1 && j < l2) {
            result[k++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }

        int p = i;
        int q = l1;
        int[] rest = nums1;
        if (p == l1) {
            p = j;
            q = l2;
            rest = nums2;
        }

        for (; p < q; ++p) {
            result[k++] = rest[p++];
        }

        if (l % 2 == 0) {
            return (result[(l >> 1) - 1] + result[l >> 1]) / 2.0d;
        } else {
            return result[l >> 1];
        }
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

    // 141. 环形链表
    public static <E> boolean hasCycle(Node<E> head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node<E> slow = head;
        Node<E> fast = head.next;
        while (slow != null && fast != null && fast.next != null) {
            if (slow == fast) {
                return true;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    // 155. 最小栈
    public static class MinStack {

        private Stack<Integer> stack;
        private Stack<Integer> min;

        public MinStack() {
            stack = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if (min.isEmpty() || x <= min.peek()) {
                min.push(x);
            }
        }

        public void pop() {
            int top = stack.pop();
            if (!min.isEmpty() && min.peek() == top) {
                min.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    // 160. 相交链表
    public static <E> Node<E> getIntersectionNode(Node<E> headA, Node<E> headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int sizeA = 0;
        int sizeB = 0;
        Node<E> tempA = headA;
        Node<E> tempB = headB;

        while (tempA != null) {
            tempA = tempA.next;
            ++sizeA;
        }

        while (tempB != null) {
            tempB = tempB.next;
        }

        int dis = Math.abs(sizeA - sizeB);
        tempA = sizeA > sizeB ? headA : headB; // longest
        tempB = tempA == headA ? headB : headA;// shortest
        while (dis != 0) {
            tempA = tempA.next;
            --dis;
        }

        while (tempA != null && tempB != null) {
            if (tempA == tempB) {
                return tempA;
            }

            tempA = tempA.next;
            tempB = tempB.next;
        }

        return null;
    }

    // 169. 求众数
    public static int majorityElement(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Unsupported param.");
        }

        Integer maj = null;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                maj = num;
            }

            count += (num == maj) ? 1 : -1;
        }

        return maj;
    }

    // 198. 打家劫舍
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        int[] dp = new int[nums.length];

        for (int i = 2; i < nums.length; ++i) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[1]);
        }

        return dp[dp.length - 1];
    }

    public static int rob_1(int[] nums) {
        if (nums == null) {
            return 0;
        }

        int prevMax = 0;
        int currMax = 0;
        for (int num : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + num, currMax);
            prevMax = temp;
        }

        return currMax;
    }

    // 206. 反转链表
    public static <E> Node<E> reverseList(Node<E> head) {
        Node<E> prev = null;
        Node<E> curr = head;
        while (curr != null) {
            Node<E> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    // 226. 翻转二叉树
    public <E> BTNode<E> invertTree(BTNode<E> root) {
        if (root == null) {
            return null;
        }

        BTNode<E> left = invertTree(root.left);
        BTNode<E> right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }

    // 234. 回文链表
    public static <E> boolean isPalindrome(Node<E> head) {
        // 允许改变链表结构
        // Time : O(n), Space : O(1)

        if (head == null) {
            return false;
        }

        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> fast = head.next;
        while (fast != null && fast.next != null) {
            Node<E> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

            fast = fast.next.next;
        }

        // 奇数, prev为中间位
        // 偶数, prev左边最后一位
        Node<E> left = prev == null ? null : fast == null ? prev.next : prev;
        Node<E> right = curr;
        while (left != null && right != null) {
            if (!left.data.equals(right.data)) {
                return false;
            }

            left = left.next;
            right = right.next;
        }

        return true;
    }

    public static <E> boolean isPalindrome_1(Node<E> head) {
        // 不允许改变链表结构
        // Time : O(n), Space : O(n)

        if (head == null) {
            return false;
        }

        Stack<E> stack = new Stack<>();
        Node<E> slow = head;
        Node<E> fast = head.next;
        stack.add(slow.data);
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            stack.add(slow.data);
        }

        // 奇数, slow为中间位
        // 偶数, slow为左边最后一位
        if (fast == null) {
            // 奇数, 移除栈顶的中间位
            stack.pop();
        }

        Node<E> right = slow.next;
        while (!stack.isEmpty() && right != null) {
            if (!stack.pop().equals(right.data)) {
                return false;
            }

            right = right.next;
        }

        return true;
    }

    // 283. 移动零
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;

        for (int cur = 0, firstZero = 0; cur < nums.length; ++cur) {
            if (nums[cur] != 0) {
                Utils.swap(nums, firstZero++, cur);
            }
        }
    }

    // 437. 路径总和 III
    public static int pathSum(BTNode<Integer> root, int sum) {
        if (root == null) {
            return 0;
        }

        return pathSumFromRoot(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }

    private static int pathSumFromRoot(BTNode<Integer> root, int sum) {
        if (root == null) {
            return 0;
        }

        sum -= root.data;
        return (sum == 0 ? 1 : 0) + pathSumFromRoot(root.left, sum) + pathSumFromRoot(root.right, sum);
    }

    // 438. 找到字符串中所有字母异位词
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || p == null || p.length() == 0 || p.length() > s.length()) {
            return result;
        }

        String sortedP = getSortedString(p);

        for (int i = 0; i < s.length() - p.length(); ++i) {
            String sub = s.substring(i, i + p.length());
            if (sortedP.equals(getSortedString(sub))) {
                int j = 0;
                for (; j < sub.length(); j++) {
                    if (sub.charAt(j) == p.charAt(j)) break;
                }

                if (j == sub.length()) {
                    result.add(i);
                }
            }
        }

        return result;
    }

    private static String getSortedString(String s) {
        if (s == null) {
            return null;
        }

        char[] sorted = s.toCharArray();
        Arrays.sort(sorted);
        return new String(sorted);
    }

    // 448. 找到所有数组中消失的数字
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return list;
        }

        for (int i = 0; i < nums.length; ++i) {
            while (nums[i] != i + 1) {
                Utils.swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != i + 1) {
                list.add(i + 1);
            }
        }

        return list;
    }

    // 538. 把二叉搜索树转换为累加树
    private static int sum = 0;

    public static BTNodeInt convertBST(BTNodeInt root) {
        sum = 0;
        backMidOrder(root);
        return root;
    }

    private static void backMidOrder(BTNodeInt root) {
        if (root == null) return;

        backMidOrder(root.right);
        sum += root.data;
        root.data = sum;
        backMidOrder(root.left);
    }

    // 543. 二叉树的直径
    private static int maxDis = 0;

    public static <E> int diameterOfBinaryTree(BTNode<E> root) {
        maxDis = 0;
        depth(root);
        return maxDis;
    }

    private static <E> int depth(BTNode<E> root) {
        if (root == null) {
            return 0;
        }

        int left = depth(root.left);
        int right = depth(root.right);
        maxDis = Math.max(maxDis, left + right);
        return Math.max(left, right) + 1;
    }

    // 581. 最短无序连续子数组
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }


        int left = 0;
        int right = nums.length - 1;
        for (int i = 0, j = nums.length - 1; i < nums.length; ++i, j -= i) {
            // Is min, start -> end.
            int m = i + 1;
            while (m < nums.length) {
                if (nums[i] > nums[m]) {
                    left = i;
                    break;
                }

                ++m;
            }

            // Is max, end -> start.
            int n = j - 1;
            while (n >= 0) {
                if (nums[j] < nums[n]) {
                    right = j;
                    break;
                }

                --n;
            }
        }

        return right - left + 1;
    }

    // 617. 合并二叉树
    public static BTNodeInt mergeTrees(BTNodeInt t1, BTNodeInt t2) {
        if (t1 == null && t2 == null) {
            return null;
        }

        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        BTNodeInt result = new BTNodeInt();
        result.data = t1.data + t2.data;
        result.left = mergeTrees(t1.left, t2.left);
        result.right = mergeTrees(t1.right, t2.right);
        return result;
    }
}
