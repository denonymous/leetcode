package com.leetcode.problems.twosum;

import org.junit.jupiter.api.Assertions;

/**
 * See https://leetcode.com/problems/two-sum/
 */
public class Solution {

    public int[] twoSum(final int[] nums, final int target) {
        for (int a = 0; a < nums.length; a++) {
            for (int b = 0; b < nums.length; b++) {
                if (a == b) {
                    continue;
                }

                if (nums[a] + nums[b] == target) {
                    return new int[]{a, b};
                }
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

        final int[] answer = solution.twoSum(
                new int[]{2, 7, 11, 15},
                9
        );

        final int[] expected = new int[]{0, 1};

        Assertions.assertArrayEquals(expected, answer);
    }
}
