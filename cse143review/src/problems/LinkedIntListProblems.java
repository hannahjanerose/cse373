package problems;

import datastructures.LinkedIntList;
// Checkstyle will complain that this is an unused import until you use it in your code.
import datastructures.LinkedIntList.ListNode;

/**
 * See the spec on the website for example behavior.
 *
 * REMEMBER THE FOLLOWING RESTRICTIONS:
 * - do not call any methods on the `LinkedIntList` objects.
 * - do not construct new `ListNode` objects for `reverse3` or `firstToLast`
 *      (though you may have as many `ListNode` variables as you like).
 * - do not construct any external data structures such as arrays, queues, lists, etc.
 * - do not mutate the `data` field of any node; instead, change the list only by modifying
 *      links between nodes.
 */

public class LinkedIntListProblems {

    /**
     * Reverses the 3 elements in the `LinkedIntList` (assume there are exactly 3 elements).
     */
    public static void reverse3(LinkedIntList list) {
        list.front.next.next.next = list.front;
        list.front = list.front.next.next;
        list.front.next.next.next = list.front.next;
        list.front.next = list.front.next.next;
        list.front.next.next.next = null;
    }

    /**
     * Moves the first element of the input list to the back of the list.
     */
    public static void firstToLast(LinkedIntList list) {
        if (list.front != null) {
            if (list.front.next != null) {
                ListNode temp = list.front;
                ListNode temp2 = list.front.next;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = list.front;
                temp.next.next = null;
                list.front = temp2;
            }

        }


    }

    /**
     * Returns a list consisting of the integers of a followed by the integers
     * of n. Does not modify items of A or B.
     */
    /*
    given 2 lists, a and b
    create new list with all of a at beginning and all of list b at the end
    iterate thru all of intlist a
        copy data at every single node into a new node

    while curr.next != null
     */
    public static LinkedIntList concatenate(LinkedIntList a, LinkedIntList b) {
        if (a.front != null) {
            LinkedIntList result = new LinkedIntList(a.front.data);
            ListNode current = result.front;
            ListNode tempA = a.front;
            while (tempA.next != null) {
                current.next = new ListNode(tempA.next.data);
                current = current.next;
                tempA = tempA.next;
            }
            current.next = b.front;
            return result;
        } else if (b != null) {
            return b;
        } else {
            return null;
        }
    }
}
