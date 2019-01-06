public class Tree {

    static class Node {
        Comparable<Object> data;
        Node left;
        Node right;
    }

    // Traverse binary tree start.
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

    private static void p(Node<E> n) {
        if (n != null) {
            System.out.println(n.data);
        }
    }
    // Traverse binary tree end.

    public static class BinarySearchTree {

        public Node tree;

        public Node find(Object data) {
            Node p = tree;

            while (p != null) {
                int result = tree.data.compareTo(data);

                if (result < 0) {
                    p = p.left;
                } else if (result > 0) {
                    p = p.right;
                } else {
                    return p;
                }
            }

            return null;
        }

        public void insert(Node root, Object data) {
            if (tree == null) {
                tree = new Node();
                tree.data = data;
                return;
            }

            Node p = tree;
            while (p != null) {
                int result = tree.data.compareTo(data);
                if (result > 0) {
                    if (p.right == null) {
                        Node right = new Node();
                        right.data = data;
                        p.right = right;
                        return;
                    }
                    p = p.right;
                } else {
                    if (p.left == null) {
                        Node left = new Node();
                        left.data = data;
                        p.left = left;
                        return;
                    }
                    p = p.left;
                }
            }
        }
    }

    public static void main(String[] args) {

    }
}