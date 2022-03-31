package com.ram.demolearnprogrammingbypractice.interviews.problemsolving;

/**
 * <a href="https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/" > LeetCode URL </a>
 * <p>
 * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
 *
 * <pre>
 *    Explanation: The odd numbers between 3 and 7 are [3,5,7].
 * </pre>
 * <pre>
 *   Input: low = 8, high = 10
 *   Output: 1
 *   Explanation: The odd numbers between 8 and 10 are [9].
 * </pre>
 * <pre>
 *   Constraints:
 *
 *    0 <= low <= high <= 10^9
 * </pre>
 */
public class CountOddNumbers {

  public void count() {

  }

  public int countOdds(int low, int high) {
    if (low % 2 != 0 || high % 2 != 0) {
      return 1 + (high - low) / 2;
    } else {
      return (high - low) / 2;
    }
  }
}
