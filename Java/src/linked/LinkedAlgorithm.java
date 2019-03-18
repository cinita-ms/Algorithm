package linked;

import base.Node;

public class LinkedAlgorithm {

    /**
     * Reverse the linked list.
     */
    public static Node reverse(Node list) {
        Node head = null;
        Node prev = null;
        Node cur = list;
        while (cur != null) {
            Node next = cur.next;
            if (next == null) {
                head = cur;
            }

            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return head;
    }

    /**
     * Whether the linked list is cycled.
     */
    public static boolean isCycled(Node list) {
        if (list == null) {
            return false;
        }

        Node slow = list;
        Node fast = list.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    /**
     * Find the middle node in linked list.
     */
    public static Node findMiddle(Node list) {
        if (list == null) {
            return null;
        }

        Node slow = list;
        Node fast = list;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     * Merge two sorted list and return the result.
     */
    public static Node mergeTwoSortedLists(Node<Integer> list1, Node<Integer> list2) {
        Node dummy = new Node();
        Node tail = dummy;
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

        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return dummy.next;
    }
}