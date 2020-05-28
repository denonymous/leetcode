package com.leetcode.problems.addtwonumbers;

import org.junit.jupiter.api.Assertions;

import java.util.Objects;

/**
 * See https://leetcode.com/problems/add-two-numbers/
 *
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single
 * digit. Add the two numbers and return it as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the
 * number 0 itself.
 *
 * Example:
 *
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class Solution {

    public ListNode addTwoNumbers(final ListNode l1, final ListNode l2) {
        final long firstVal = nodeToLong(l1, 0, 1);
        final long secondVal = nodeToLong(l2, 0, 1);

        return longToNode(firstVal + secondVal);
    }

    private long nodeToLong(final ListNode node, long nodeValue, int depth) {
        if (Objects.isNull(node)) {
            return nodeValue;
        }

        nodeValue += depth * node.val;

        return nodeToLong(node.next, nodeValue, depth *= 10);
    }

    private ListNode longToNode(final long value) {
        final long thisValue = value % 10;
        final long nextValue = value / 10;

        return (nextValue == 0)
                ? new ListNode(Math.toIntExact(thisValue), null)
                : new ListNode(Math.toIntExact(thisValue), longToNode(nextValue));
    }

    public static void main(final String[] args) {
        ListNode first;
        ListNode second;

        first = new ListNode(2, new ListNode(4, new ListNode(3)));
        second = new ListNode(5, new ListNode(6, new ListNode(4)));

        Assertions.assertEquals(
                new ListNode(7, new ListNode(0, new ListNode(8))),
                new Solution().addTwoNumbers(first, second)
        );

        Assertions.assertEquals(
                new ListNode(7, new ListNode(0, new ListNode(8))),
                new Solution().addTwoNumbers(first, second)
        );

        first = new ListNode(9);
        second = new ListNode(
                1,
                new ListNode(
                        9,
                        new ListNode(
                                9,
                                new ListNode(
                                        9,
                                        new ListNode(
                                                9,
                                                new ListNode(
                                                        9,
                                                        new ListNode(
                                                                9,
                                                                new ListNode(
                                                                        9,
                                                                        new ListNode(
                                                                                9,
                                                                                new ListNode(
                                                                                        9
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );

        final ListNode result = new Solution().addTwoNumbers(first, second);

        Assertions.assertEquals(
                new ListNode(
                        0,
                        new ListNode(
                                0,
                                new ListNode(
                                        0,
                                        new ListNode(
                                                0,
                                                new ListNode(
                                                        0,
                                                        new ListNode(
                                                                0,
                                                                new ListNode(
                                                                        0,
                                                                        new ListNode(
                                                                                0,
                                                                                new ListNode(
                                                                                        0,
                                                                                        new ListNode(
                                                                                                0,
                                                                                                new ListNode(
                                                                                                        1
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                result
        );
    }
}
