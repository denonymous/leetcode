package com.leetcode.problems.reverseinteger;

import org.junit.jupiter.api.Assertions;

/**
 * See https://leetcode.com/problems/reverse-integer/
 */
public class Solution {

    public int reverse(int x) {
        final boolean negative = x < 0;
        final String[] digits = String.valueOf(Math.abs(x)).split("");

        final StringBuilder builder = new StringBuilder(negative ? "-" : "");

        for (int idx = digits.length - 1; idx >= 0; idx--) {
            builder.append(digits[idx]);
        }

        try {
            return Integer.parseInt(builder.toString());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Assertions.assertEquals(321, new Solution().reverse(123));
        Assertions.assertEquals(-321, new Solution().reverse(-123));
        Assertions.assertEquals(987654321, new Solution().reverse(1234567890));
        Assertions.assertEquals(0, new Solution().reverse(1111111119));
    }
}
