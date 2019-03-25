package tree;

import base.BSTNode;
import base.BTNode;
import base.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeAlgorithm {

    // 翻转二叉树
    public static void reverse(BTNode root) {
        if (root == null) {
            return;
        }

        BTNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        reverse(root.left);
        reverse(root.right);
    }

    // 二叉树最深度
    public static int maxDepth(BTNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 验证二叉查找树
    private static int last = Integer.MIN_VALUE;

    public static boolean isValidBST(BSTNode<Integer> root) {
        if (root == null) {
            return true;
        }

        if (isValidBST(root.left)) {
            if (last < root.data) {
                last = root.data;
                return isValidBST(root.right);
            }
        }

        return false;
    }

    // 验证二叉树路径总和
    public static boolean hasPathSum(BTNode<Integer> root, int sum) {
        if (root == null) {
            return false;
        }

        int rest = sum - root.data;

        if (root.left == null && root.right == null) {
            return rest == 0;
        }

        return hasPathSum(root.left, rest) || hasPathSum(root.right, rest);
    }

    // TODO: 2019-03-17  连接二叉树同一层上的结点

    // 打印二叉树指定层的节点
    public static <E> void printLevel1(BTNode<E> root, int level) {
        printLevelRecursion(root, level, 1);
    }

    private static <E> void printLevelRecursion(BTNode<E> root, int level, int nextLevel) {
        if (root == null) {
            return;
        }

        if (nextLevel == level) {
            Utils.println(root.data);
        }

        ++nextLevel;
        printLevelRecursion(root.left, level, nextLevel);
        printLevelRecursion(root.right, level, nextLevel);
    }

    public static <E> void printLevel2(BTNode<E> root, int level) {
        if (root == null) return;

        Queue<BTNode<E>> queue = new LinkedList<>();
        int currentNodeSize = 1;
        int nextNodeSize = 0;
        int currentLevel = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            BTNode<E> node = queue.poll();

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
                ++nextNodeSize;
            }

            if (currentLevel == level) {
                Utils.println(node.data);
                ++nextNodeSize;
            }

            --currentNodeSize;
            if (currentNodeSize == 0) {
                ++currentLevel;
                currentNodeSize = nextNodeSize;
            }
        }
    }

    // 判断二叉树是否平衡二叉树
    public static <E> boolean isBalancedBT(BTNode<E> root) {
        if (root == null) {
            return false;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        if (Math.abs(left - right) > 1) {
            return false;
        }

        return isBalancedBT(root.left) && isBalancedBT(root.right);
    }

    // 验证数组是不是二叉搜索树后序遍历的结果
    public static boolean verifyPostOrder(int[] a) {
        if (a == null || a.length == 1) return false;

        return verifyPostOrderRecursion(a, 0, a.length - 1);
    }

    private static boolean verifyPostOrderRecursion(int[] a, int begin, int end) {
        if (begin > end) {
            return true;
        }

        int root = a[end];

        // Find the left and right child position of root.
        int i;
        for (i = 0; i < end - 1; ++i) {
            if (a[i] > root) break;
        }

        int j = i;
        for (; j < end - 1; ++j) {
            if (a[j] < root) {
                return false;
            }
        }

        boolean left = verifyPostOrderRecursion(a, 0, i - 1);
        boolean right = verifyPostOrderRecursion(a, i, end - 1);
        return left && right;
    }
}
