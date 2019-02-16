public class BinaryTree {

    // All O(n).
    // Previous order.
    public static void prevOrder(Node root) {
        if (root == null)
            return;

        p(root);
        prevOrder(root.left);
        prevOrder(root.right);
    }

    // Middle order.
    public static void midOrder(Node root) {
        if (root == null)
            return;

        midOrder(root.left);
        p(root);
        midOrder(root.right);
    }

    // Post order.
    public static void postOrder(Node root) {
        if (root == null)
            return;

        postOrder(root.left);
        postOrder(root.right);
        p(root);
    }

    private static void p(Node n) {
        if (n != null) {
            System.out.println(n.data);
        }
    }
}