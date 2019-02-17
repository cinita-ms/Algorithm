package tree;

public class BinaryTree {

    // Previous order.
    public static <E extends Comparable<E>> void prevOrder(BTNode<E> root) {
        if (root == null)
            return;

        print(root);
        prevOrder(root.left);
        prevOrder(root.right);
    }

    // Middle order.
    public static <E extends Comparable<E>> void midOrder(BTNode<E> root) {
        if (root == null)
            return;

        midOrder(root.left);
        print(root);
        midOrder(root.right);
    }

    // Post order.
    public static <E extends Comparable<E>> void postOrder(BTNode<E> root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        print(root);
    }

    private static void print(BTNode n) {
        if (n != null) {
            System.out.println(n.data);
        }
    }
}