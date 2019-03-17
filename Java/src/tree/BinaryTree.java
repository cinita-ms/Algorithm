package tree;

import base.BTNode;
import base.Utils;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {

    // Previous, middle and post are all DFS.
    // Previous order.
    public static <E> void prevOrder(BTNode<E> root) {
        if (root == null) return;

        print(root);
        prevOrder(root.left);
        prevOrder(root.right);
    }

    // Middle order.
    public static <E> void midOrder(BTNode<E> root) {
        if (root == null) return;

        midOrder(root.left);
        print(root);
        midOrder(root.right);
    }

    // Post order.
    public static <E> void postOrder(BTNode<E> root) {
        if (root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        print(root);
    }

    // BFS.
    public static <E> void bfs(BTNode<E> root) {
        if (root == null) return;

        Queue<BTNode<E>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BTNode<E> node = queue.poll();
            print(node);

            if (node.left != null) {
                queue.offer(node.left);
            }

            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    private static <E> void print(BTNode<E> n) {
        Utils.println(n.data);
    }
}