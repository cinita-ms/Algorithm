import base.BTNode;
import base.Node;

import java.util.ArrayList;
import java.util.List;

public class TestNote {


    private static boolean isFound = false;

    public static <E> List<BTNode<E>> getPath(BTNode<E> root, BTNode<E> target) {
        List<BTNode<E>> results = new ArrayList<>();
        isFound = false;
        rGetPath(root, target, results);
        return results;
    }

    private static <E> void rGetPath(BTNode<E> root, BTNode<E> target, List<BTNode<E>> results) {
        if (root == null || isFound) return;

        results.add(root);
        if (root == target) {
            isFound = true;
            return;
        }

        rGetPath(root.left, target, results);
        rGetPath(root.right, target, results);
        results.remove(results.size() - 1);
    }

    public static <E> void reverse(BTNode<E> root) {
        if (root == null) return;

        BTNode<E> temp = root.left;
        root.left = root.right;
        root.right = temp;
        reverse(root.left);
        reverse(root.right);
    }

    public static <E> int maxDepth(BTNode<E> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public static int last = Integer.MIN_VALUE;

    public static boolean isValid(BTNode<Integer> root) {
        if (root == null) {
            return true;
        }

        if (isValid(root.left)) {
            if (last < root.data) {
                last = root.data;
                return isValid(root.right);
            }
        }

        return false;
    }

    private static int maxDistance;

    public static <E> int maxDistance(BTNode<E> root) {
        if (root == null) {
            return -1;
        }

        maxDistance = Integer.MIN_VALUE;
        rMaxDistance(root);
        return maxDistance;
    }

    private static <E> void rMaxDistance(BTNode<E> root) {
        if (root == null) return;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        maxDistance = Math.max(maxDistance, leftDepth + rightDepth);
        rMaxDistance(root.left);
        rMaxDistance(root.right);
    }

    public static boolean isPostOrder(int[] a) {
        if (a == null || a.length == 0) {
            return false;
        }

        return rIsPostOrder(a, 0, a.length - 1);
    }

    private static boolean rIsPostOrder(int[] a, int start, int end) {
        if (start >= end) {
            return true;
        }

        int root = a[end];

        int i = 0;
        for (; i < end; ++i) {
            if (a[i] > root) {
                break;
            }
        }

        int j = i;
        for (; j < end; ++j) {
            if (a[j] < root) {
                return false;
            }
        }

        return rIsPostOrder(a, start, start + i - 1)
                && rIsPostOrder(a, start + i, end - 1);
    }

    // 合并两个有序链表
    public static Node<Integer> mergeSortedList(Node<Integer> list1, Node<Integer> list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        Node<Integer> dump = new Node<>();
        Node<Integer> tail = dump;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                tail.next = list1;
                tail = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                tail = list2;
                list2 = list2.next;
            }
        }

        if (list1 == null) {
            tail.next = list2;
        } else {
            tail.next = list1;
        }

        return dump.next;
    }

}
