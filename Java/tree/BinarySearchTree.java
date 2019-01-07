public class BinarySearchTree {

    private Node tree;

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