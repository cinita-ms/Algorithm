package tree;

import base.BTNode;

public class BinarySearchTree<E extends Comparable<E>> {

    private BTNode<E> tree;

    public BTNode find(E data) {
        BTNode<E> p = tree;

        while (p != null) {
            int result = p.data.compareTo(data);

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

    public void insert(E data) {
        if (tree == null) {
            tree = new BTNode<>();
            tree.data = data;
            return;
        }

        BTNode<E> p = tree;
        while (true) {
            int result = p.data.compareTo(data);
            if (result > 0) {
                if (p.right == null) {
                    p.right = new BTNode<>();
                    p.right.data = data;
                    return;
                }

                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new BTNode<>();
                    p.left.data = data;
                    return;
                }

                p = p.left;
            }
        }
    }

    public void delete(E data) {
        if (tree == null) return;

        // Find target(p) and target's parent(pp);
        BTNode<E> p = tree;
        BTNode<E> pp = null;
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

        // If p has double child.
        // 1. find the min child(minP) on right child tree.
        // 2. exchange the p and minP data.
        // 3. make p to point to minP, also make the pp to point to mimPP.
        // 4. dispatch the current delete logic to next(Handle delete).
        if (p.left != null || p.right != null) {
            BTNode<E> minP = p.right;
            BTNode<E> minPP = p;
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
        BTNode<E> child;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }

        // Make pp's child to point to child.
        if (pp == null) {
            tree = null;
        } else if (p == pp.left) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }
}