package me.cinita.tree;

import me.cinita.base.BSTNode;
import me.cinita.base.BTNode;
import me.cinita.util.Utils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class TreeAlgorithm {

    // 获取二叉树根结点到指定节点的路径
    private static boolean getPath_isFound = false;

    public static <E> Stack<BTNode<E>> getPath(BTNode<E> root, BTNode<E> target) {
        Stack<BTNode<E>> stack = new Stack<>();
        getPath_isFound = false;
        rPrintPath(root, target, stack);
        return stack;
    }

    private static <E> void rPrintPath(BTNode<E> root, BTNode<E> target, Stack<BTNode<E>> stack) {
        if (root == null || getPath_isFound) return;

        stack.push(root);

        if (root == target) {
            getPath_isFound = true;
            Utils.println(stack.toString());
            return;
        }

        rPrintPath(root.left, target, stack);
        rPrintPath(root.right, target, stack);

        stack.pop();
    }

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

    // 打印二叉树指定层的节点
    public static <E> void printLevel1(BTNode<E> root, int targetLevel) {
        printLevelRecursion(root, targetLevel, 1);
    }

    private static <E> void printLevelRecursion(BTNode<E> root, int targetLevel, int nextLevel) {
        if (root == null) {
            return;
        }

        if (nextLevel == targetLevel) {
            Utils.println(root.data);
            return;
        }

        ++nextLevel;
        printLevelRecursion(root.left, targetLevel, nextLevel);
        printLevelRecursion(root.right, targetLevel, nextLevel);
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

    // 二叉树中相距最远的两个节点之间的距离(二叉树每个节点的左右子树高度和的最大值)
    private static int maxDis;

    public static int maxDis(BTNode root) {
        if (root == null) {
            return -1;
        }

        maxDis = Integer.MIN_VALUE;
        maxDisRecursion(root);
        return maxDis;
    }

    private static void maxDisRecursion(BTNode root) {
        if (root == null) return;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        maxDis = Math.max(maxDis, left + right);
        maxDisRecursion(root.left);
        maxDisRecursion(root.right);
    }

    // 验证数组是不是二叉搜索树后序遍历的结果
    public static boolean verifyPostOrder(int[] a) {
        if (a == null || a.length == 1) return false;

        return rVerifyPostOrder(a, 0, a.length - 1);
    }

    private static boolean rVerifyPostOrder(int[] a, int begin, int end) {
        if (begin >= end) {
            return true;
        }

        int root = a[end];

        // Find the left and right child position of root.
        int i;
        for (i = 0; i < end; ++i) {
            if (a[i] > root) break;
        }

        int j = i;
        for (; j < end; ++j) {
            if (a[j] < root) {
                return false;
            }
        }

        return rVerifyPostOrder(a, begin, begin + i - 1)
                && rVerifyPostOrder(a, begin + i, end - 1);
    }

    // 在二元树中找出和为某一值的所有路径
    public static void findAllPath(BTNode<Integer> root, int path) {
        Stack<Integer> stack = new Stack<>();
        rFindAllPath(root, path, stack);
    }

    private static void rFindAllPath(BTNode<Integer> root, int path, Stack<Integer> stack) {
        if (root == null) return;

        stack.push(root.data);
        path -= root.data;

        if (root.left == null && root.right == null) {
            if (path == 0) {
                Utils.println(stack.toString());
            }
        } else {
            rFindAllPath(root.left, path, stack);
            rFindAllPath(root.right, path, stack);
        }

        stack.pop();
    }

    // 二叉查找树转双向链表，要求不能创建任何新的结点，只调整指针的指向
    private static BTNode head = null;
    private static BTNode tail = null;

    public static BTNode f1(BTNode node) {
        head = null;
        tail = null;
        f1Recursion(node);
        return head;
    }

    private static void f1Recursion(BTNode node) {
        if (node == null) return;

        f1Recursion(node.left);
        convert1(node);
        f1Recursion(node.right);
    }

    private static void convert1(BTNode node) {
        node.left = tail;
        if (tail == null) {
            head = node;
        } else {
            tail.right = node;
        }

        tail = node;
    }

    // 求二叉树中两个节点的最近公共祖先节点
    // 1. 该二叉树为二叉搜索树
    public static BSTNode<Integer> f2(BSTNode<Integer> root, BSTNode<Integer> p, BSTNode<Integer> q) {
        int rootData = root.data;
        int pData = p.data;
        int qData = q.data;
        if (rootData < pData && rootData < qData) {
            return f2(root.right, p, q);
        } else if (rootData > pData && rootData > qData) {
            return f2(root.left, p, q);
        } else {
            return root;
        }
    }

    // 2. 该二叉树为一般二叉树
    public static <E> BTNode<E> f3_a(BTNode<E> root, BTNode<E> p, BTNode<E> q) {
        List<BTNode<E>> list_p = getPath(root, p);
        List<BTNode<E>> list_q = getPath(root, q);
        int next = 0;
        while (next < list_p.size() && next < list_q.size()) {
            BTNode<E> result;
            if ((result = list_p.get(next)) == list_q.get(next)) {
                return result;
            }

            ++next;
        }

        return null;
    }

    public static <E> BTNode<E> f3_b(BTNode<E> root, BTNode<E> p, BTNode<E> q) {
        if (root == null || p == null || q == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        BTNode<E> left = f3_b(root.left, p, q);
        BTNode<E> right = f3_b(root.right, p, q);
        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        } else {
            return root;
        }
    }
}
