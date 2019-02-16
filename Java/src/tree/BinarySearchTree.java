import base.Node;

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

    public void insert(Object data) {
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

    public void delete(Object data) {
        if (tree == null)
            return;

        // Find target(p) and target's parent(pp);
        Node p = tree;
        Node pp = null;
        int result;
        while (p != null && (result = p.data.compareTo(data)) != 0) {
            pp = p;
            if (result > 0) {
                p = p.right;
            } else {
                p = p.left;
            }
        }

        if (p == null)
            return;

        // If p has double childs
        // 1. find the minest child(minP) on right child tree
        // 2. exchange the p and minP data
        // 3. make p to point to minP, also make the pp to point to mimPP
        // 4. dispatch the current delete logic to next(Handle delete)
        if (p.left != null || p.right != null) {
            Node minP = p.right;
            Node minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }

        // Handle delete.
        // The child is p's left child or right child or null.
        Node child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // Make pp's child to point to child.
        if (pp == null) {
            tree = child;
        } else if (p == pp.left) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }
}