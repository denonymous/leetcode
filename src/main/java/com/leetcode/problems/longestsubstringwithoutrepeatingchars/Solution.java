package com.leetcode.problems.longestsubstringwithoutrepeatingchars;

import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * See https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * 
 * Example 1:
 * 
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * 
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * 
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class Solution {

    public int lengthOfLongestSubstring(final String s) {
        final List<String> letters = new LinkedList<>(Arrays.asList(s.split("")));

        final Set<String> substrings = new HashSet<>();

        for (int idx = 0; idx < letters.size(); idx++) {
            final Set<String> substring = new HashSet<>();
            final ListIterator<String> iterator = letters.listIterator(idx);
            while (iterator.hasNext()) {
                final String letter = iterator.next();
                if (substring.contains(letter)) {
                    break;
                }

                substring.add(letter);
            }

            substrings.add(String.join("", substring));
        }

        return substrings.stream().mapToInt(String::length).max().orElseThrow();
    }

    public static void main(String[] args) {
        Assertions.assertEquals(
                3,
                new Solution().lengthOfLongestSubstring("abcabcbb")
        );

        Assertions.assertEquals(
                1,
                new Solution().lengthOfLongestSubstring("bbbbb")
        );

        Assertions.assertEquals(
                3,
                new Solution().lengthOfLongestSubstring("pwwkew")
        );

        Assertions.assertEquals(
                5,
                new Solution().lengthOfLongestSubstring("aaaaaathree")
        );

        Assertions.assertEquals(
                2,
                new Solution().lengthOfLongestSubstring("aaaaab")
        );
    }
}
