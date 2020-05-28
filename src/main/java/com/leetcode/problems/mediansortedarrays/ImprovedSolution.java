package com.leetcode.problems.mediansortedarrays;

import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.Objects;

/**
 * See https://leetcode.com/problems/median-of-two-sorted-arrays/
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class ImprovedSolution {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        final int totalLength = nums1.length + nums2.length;

        final int[] mergedLeftHalf = leftHalfArrayMerge(nums1, nums2);

        final int leftHalfLength = mergedLeftHalf.length;

        if (totalLength % 2 == 0) {
            return (double) (mergedLeftHalf[leftHalfLength - 2] + mergedLeftHalf[leftHalfLength - 1]) / 2;
        } else {
            return mergedLeftHalf[leftHalfLength - 1];
        }
    }

    private int[] leftHalfArrayMerge(final int[] nums1, final int[] nums2) {
        final int totalLength = nums1.length + nums2.length;

        final int desiredLength = (totalLength / 2) + 1;

        final int[] merged = new int[desiredLength];
        int mergedIdx = 0;

        final Iterator<Integer> it1 = toIterator(nums1);
        final Iterator<Integer> it2 = toIterator(nums2);

        Solution.Optional<Integer> optionalFirst = optionalNext(it1);
        Solution.Optional<Integer> optionalSecond = optionalNext(it2);
        while (true) {
            if (optionalFirst.isEmpty() && optionalSecond.isEmpty()) {
                break;
            }

            if (mergedIdx >= desiredLength) {
                break;
            }

            if (optionalFirst.isPresent() && optionalSecond.isPresent()) {
                final int first = optionalFirst.get();
                final int second = optionalSecond.get();
                if (first == second) {
                    merged[mergedIdx++] = first;
                    optionalFirst = optionalNext(it1);

                    if (mergedIdx < merged.length) {
                        merged[mergedIdx++] = second;
                        optionalSecond = optionalNext(it2);
                    }
                } else if (first < second) {
                    merged[mergedIdx++] = first;
                    optionalFirst = optionalNext(it1);
                } else {
                    merged[mergedIdx++] = second;
                    optionalSecond = optionalNext(it2);
                }
            } else if (optionalFirst.isPresent()) {
                merged[mergedIdx++] = optionalFirst.get();
                optionalFirst = optionalNext(it1);
            } else {
                merged[mergedIdx++] = optionalSecond.get();
                optionalSecond = optionalNext(it2);
            }
        }

        return merged;
    }

    private <T> Solution.Optional<T> optionalNext(final Iterator<T> iterator) {
        return iterator.hasNext() ? Solution.Optional.of(iterator.next()) : Solution.Optional.empty();
    }

    private Iterator<Integer> toIterator(final int[] nums) {
        final int len = nums.length;

        return new Iterator<>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < len;
            }

            @Override
            public Integer next() {
                return nums[pos++];
            }
        };
    }

    static class Optional<T> {
        T value;

        Optional(final T value) {
            this.value = value;
        }

        static <T> Solution.Optional<T> of(final T value) {
            return new Solution.Optional<T>(value);
        }

        static <T> Solution.Optional<T> empty() {
            return new Solution.Optional<T>(null);
        }

        boolean isPresent() {
            return Objects.nonNull(value);
        }

        boolean isEmpty() {
            return !isPresent();
        }

        T get() {
            if (value == null) {
                throw new IllegalArgumentException();
            }

            return value;
        }
    }

    public static void main(String[] args) {
        Assertions.assertEquals(
                0,
                new ImprovedSolution().findMedianSortedArrays(new int[]{0, 0}, new int[]{0, 0})
        );

        Assertions.assertEquals(
                2.5,
                new ImprovedSolution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3,4})
        );

        Assertions.assertEquals(
                2,
                new ImprovedSolution().findMedianSortedArrays(new int[]{1, 3}, new int[]{2})
        );

        Assertions.assertEquals(
                -1,
                new ImprovedSolution().findMedianSortedArrays(new int[]{3}, new int[]{-2, -1})
        );

        Assertions.assertEquals(
                2.5,
                new ImprovedSolution().findMedianSortedArrays(new int[]{}, new int[]{2, 3})
        );

        Assertions.assertEquals(
                1,
                new ImprovedSolution().findMedianSortedArrays(new int[]{1}, new int[]{})
        );

        Assertions.assertEquals(
                1,
                new ImprovedSolution().findMedianSortedArrays(new int[]{1}, new int[]{1})
        );
    }
}
