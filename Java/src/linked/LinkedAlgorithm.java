package linked;

import base.Node;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * Delete the last target k node.
     */
    public static Node deleteLastTargetNode(Node list, int k) {
        Node fast = list;
        Node cur = null;
        Node prev = null;

        int i = 1;
        while (fast != null) {
            if (i == k) {
                cur = fast;
            }

            if (i == k + 1) {
                prev = fast;
            }

            fast = fast.next;

            if (cur != null) {
                cur = cur.next;
            }

            if (prev != null) {
                prev = prev.next;
            }

            ++i;
        }

        if (cur == null) {
            return list;
        }

        if (prev == null) {
            list = list.next;
        } else {
            prev.next = cur.next;
        }

        return list;
    }

    /**
     * The chars are in linked list.
     */
    public static boolean isPalindrome(Node<Character> list) {
        // Step :
        // 1. Find the middle.
        // 2. Reverse the first half.
        // 3. Compare the first half and the second half.

        if (list == null) {
            return true;
        }

        Node<Character> fast = list;
        Node<Character> cur = list;
        Node<Character> prev = null;

        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;

            Node<Character> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        if (cur == null || cur.next == null) {
            return true;
        }

        Node<Character> first = list;
        Node<Character> second = cur.next;
        while (first != null && second != null) {
            if (first.data != second.data) {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
    }

    public static class RandomListNode<E> {
        public E data;
        public RandomListNode<E> next;
        public RandomListNode<E> random;
    }

    /**
     * Clone the random list solution 1.
     */
    public static <E> RandomListNode<E> cloneRandomLsitNode1(RandomListNode<E> head) {
        if (head == null) {
            return null;
        }

        Map<RandomListNode<E>, RandomListNode<E>> map = new HashMap<>();
        RandomListNode<E> src = head;
        RandomListNode<E> dump = new RandomListNode<>();
        RandomListNode<E> result = dump;
        while (src != null) {
            RandomListNode<E> temp = new RandomListNode<>();
            temp.data = src.data;
            map.put(src, temp);
            result.next = temp;

            src = src.next;
            result = result.next;
        }

        src = head;
        while (src != null) {
            if (src.random == null) {
                RandomListNode<E> target = map.get(src);
                target.random = map.get(src.random);
            }

            src = src.next;
        }

        return dump.next;
    }

    /**
     * Clone the random list solution 2.
     */
    public static <E> RandomListNode<E> cloneRandomLsitNode2(RandomListNode<E> head) {
        if (head == null) {
            return null;
        }

        RandomListNode<E> src = head;
        while (src != null) {
            RandomListNode<E> temp = new RandomListNode<>();
            temp.data = src.data;
            temp.next = src.next;
            src.next = temp;
            src = temp.next;
        }

        src = head;
        while (src != null) {
            if (src.random != null) {
                src.next.random = src.random.next;
            }

            src = src.next.next;
        }

        src = head;
        RandomListNode<E> dump = head.next;
        RandomListNode<E> result = dump;
        while (result.next != null) {
            src.next = result.next;
            src = src.next;

            result.next = src.next;
            result = result.next;
        }

        src.next = null;
        return dump;
    }
}