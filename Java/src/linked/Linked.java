package linked;

import base.Node;

public class Linked {

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
     * Whether the char linked list is a palindrome.
     */
    public static boolean isPalindrome(Node list) {
        return false;
    }
}