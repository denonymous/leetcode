package com.leetcode.problems.twosum;

import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

public class ImprovedSolution {

    public int[] twoSum(final int[] nums, final int target) {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            final int other = target - nums[i];

            if (map.containsKey(other) && map.get(other) != i) {
                return new int[]{i, map.get(other)};
            }
        }

        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

        final int[] answer = solution.twoSum(
                new int[]{3, 2, 4},
                6
        );

        final int[] expected = new int[]{1, 2};

        Assertions.assertArrayEquals(expected, answer);
    }
}
