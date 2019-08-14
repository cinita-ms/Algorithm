package me.cinita.leetcode;

import me.cinita.base.BTNodeInt;
import me.cinita.util.Utils;

import java.util.*;

public class MediumAlgorithm {

    // 字谜分组
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (Utils.isEmpty(strs)) {
            return null;
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            if (s == null) continue;

            int[] table = new int[26];
            for (char c : s.toCharArray()) {
                table[c - 'a']++;
            }

            String key = arrayToKey(table);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }

            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    private static String arrayToKey(int[] target) {
        StringBuilder builder = new StringBuilder(target.length);
        for (int e : target) {
            builder.append(e);
        }

        return builder.toString();
    }

    // 从前序与中序遍历序列构造二叉树
    public static BTNodeInt buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; ++i) {
            map.put(inorder[i], i);
        }

        index = 0;
        return rBuildTree(preorder, map, 0, inorder.length);
    }

    private static int index = 0;

    private static BTNodeInt rBuildTree(int[] preorder, Map<Integer, Integer> map, int inStart, int inEnd) {
        if (inStart == inEnd) {
            return null;
        }

        BTNodeInt root = new BTNodeInt();
        root.data = preorder[index++];
        int inIndex = map.get(root.data);
        root.left = rBuildTree(preorder, map, inStart, inIndex);
        root.right = rBuildTree(preorder, map, inIndex + 1, inEnd);
        return root;
    }

    // 填充每个节点的下一个右侧节点指针
    public static class MyBTNode {
        public int data;
        public MyBTNode left;
        public MyBTNode right;
        public MyBTNode next;
    }

    public static MyBTNode connect(MyBTNode root) {
        if (root == null) {
            return null;
        }

        if (root.left != null) {
            root.left.next = root.right;
        }

        if (root.next != null) {
            root.right.next = root.next.left;
        }

        connect(root.left);
        connect(root.right);
        return root;
    }

    // 二叉搜索树中第K小的元素
    private static Integer result;
    private static int count;

    public int kthSmallest(BTNodeInt root, int k) {
        count = k;
        result = null;
        dfs_1(root);
        return result;
    }

    private static void dfs_1(BTNodeInt root) {
        if (root == null || result != null) return;

        dfs_1(root.left);
        if ((--count) == 0) {
            result = root.data;
        }

        dfs_1(root.right);
    }

    // 岛屿数量
    public static int numIslands(char[][] grid) {
        int islandCount = 0;
        int rLen = grid.length;
        int cLen = grid[0].length;
        for (int i = 0; i < rLen; ++i) {
            for (int j = 0; j < cLen; ++j) {
                if (grid[i][j] == '1') {
                    ++islandCount;
                    dfs_2(grid, i, j);
                }
            }
        }

        return islandCount;
    }

    private static void dfs_2(char[][] grid, int i, int j) {
        int rLen = grid.length;
        int cLen = grid[0].length;
        if (i < 0 || j < 0 || i >= rLen || j >= cLen || grid[i][j] == '0') return;

        grid[i][j] = '0';
        dfs_2(grid, i - 1, j);
        dfs_2(grid, i + 1, j);
        dfs_2(grid, i, j - 1);
        dfs_2(grid, i, j + 1);
    }

    // 电话号码的字母组合
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        // Init map.
        f1(result, map, "", digits);
        return result;
    }

    private static void f1(List<String> result, Map<String, String> map, String cur, String digits) {
        if (digits.length() == 0) {
            result.add(cur);
        } else {
            String number = digits.substring(0, 1);
            String chars = map.get(number);
            for (int i = 0; i < chars.length(); ++i) {
                String toAdd = chars.substring(i, i + 1);
                f1(result, map, cur + toAdd, digits.substring(1));
            }
        }
    }

    // 生成括号
    public List<String> generateParenthesis(int n) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            builder.append('(').append(')');
        }
        String s = builder.toString();
        List<String> result = new ArrayList<>();
        f2(result, "", s);
        return result;
    }

    private static void f2(List<String> result, String cur, String s) {
        if (0 == s.length() && isValid(cur)) {
            result.add(cur);
            return;
        }

        for (int i = 0; i < s.length(); ++i) {
            String toAdd = s.substring(i, i + 1);
            String rest = removeAt(s, i);
            f2(result, cur + toAdd, rest);
        }
    }

    private static boolean isValid(String s) {
        return true;
    }

    private static String removeAt(String s, int i) {
        return s.substring(0, i) + s.substring(i + 1);
    }

    // 子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        f3(nums, result, 0);
        return result;
    }

    private static void f3(int[] nums, List<List<Integer>> result, int position) {
        if (position >= nums.length) return;

        final int size = result.size();
        for (int i = 0; i < size; ++i) {
            List<Integer> newSub = new ArrayList<>(result.get(i));
            newSub.add(nums[position]);
            result.add(newSub);
        }

        f3(nums, result, position + 1);
    }

    public static List<List<Integer>> subsets_1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < (1 << nums.length); ++i) {
            List<Integer> sub = new ArrayList<>();
            for (int j = 0; j < nums.length; ++j) {
                if (((i >> j) & 1) == 1) {
                    sub.add(nums[j]);
                }
            }

            result.add(sub);
        }

        return result;
    }

    // 前 K 个高频元素
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (int num : map.keySet()) {
            heap.add(num);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }

        Collections.reverse(result);
        return result;
    }

    // 跳跃游戏
    public static boolean canJump(int[] nums) {
        return r1(nums, 0);
    }

    private static boolean r1(int[] nums, int position) {
        if (position == nums.length - 1) {
            return true;
        }

        int toJumpLen = Math.min(nums.length - 1, nums[position]);
        for (int nextPosition = toJumpLen; nextPosition > position; --nextPosition) {
            if (r1(nums, nextPosition)) {
                return true;
            }
        }

        return false;
    }

    // 不同路径
    public static int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        return uniquePaths(m - 1, n) + uniquePaths(m, n - 1);
    }

    public static int uniquePaths_1(int m, int n) {
        int[][] table = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i == 0 || j == 0) {
                    table[i][j] = 1;
                } else {
                    table[i][j] = table[i - 1][j] + table[i][j - 1];
                }
            }
        }

        return table[m - 1][n - 1];
    }

    // 零钱兑换
    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        Integer result = null;
        for (int coin : coins) {
            if (amount - coin < 0) continue;

            int subResult = coinChange(coins, amount - coin);
            if (subResult == -1) continue;

            if (result == null) {
                result = subResult + 1;
            } else {
                result = Math.min(result, subResult + 1);
            }
        }

        return result == null ? -1 : result;
    }

    public static int coinChange_1(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 0; i <= amount; ++i) {
            for (int coin : coins) {
                if (amount >= coin) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
