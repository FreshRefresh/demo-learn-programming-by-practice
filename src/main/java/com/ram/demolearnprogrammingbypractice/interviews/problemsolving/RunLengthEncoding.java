package com.ram.demolearnprogrammingbypractice.interviews.problemsolving;

/**
 * Given an array of characters chars, compress it using the following algorithm:
 * <p>
 * Begin with an empty string s. For each group of consecutive repeating characters in chars:
 * <p>
 * If the group's length is 1, append the character to s. Otherwise, append the character followed by the group's length. The compressed string s should not be returned separately, but instead, be
 * stored in the input character array chars. Note that group lengths that are 10 or longer will be split into multiple characters in chars.
 * <p>
 * After you are done modifying the input array, return the new length of the array.
 * <p>
 * You must write an algorithm that uses only constant extra space.
 *
 * <pre>
 *
 * Example 1:
 *
 * Input: chars = ['a','a','b','b','c','c','c']
 * Output: Return 6, and the first 6 characters of the input array should be: ['a','2','b','2','c','3']
 * Explanation: The groups are 'aa', 'bb', and 'ccc'. This compresses to 'a2b2c3'.
 * Example 2:
 *
 * Input: chars = ['a']
 * Output: Return 1, and the first character of the input array should be: ['a']
 * Explanation: The only group is 'a', which remains uncompressed since it's a single character.
 * Example 3:
 *
 * Input: chars = ['a','b','b','b','b','b','b','b','b','b','b','b','b']
 * Output: Return 4, and the first 4 characters of the input array should be: ['a','b','1','2'].
 * Explanation: The groups are 'a' and 'bbbbbbbbbbbb'. This compresses to 'ab12'.
 *
 *
 * Constraints:
 *
 * 1 <= chars.length <= 2000
 * chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
 * </pre>
 */
public class RunLengthEncoding {

  public static void main(String[] args) {

    char[] input1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
    char[] input12 = {'a'};
    char[] input4 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
//    System.out.println(compress(input1));
//    System.out.println(compress(input12));
    RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
    System.out.println(runLengthEncoding.compress(input4));
  }

  public int compress(char[] chars) {

    StringBuilder sb = new StringBuilder();
    if (chars == null || chars.length == 0) {
      return 0;
    }

    char previousChar = 0;
    int counter = 0;
    for (char ch : chars) {
      if (ch == previousChar) {
        counter++;
      } else {

        if (previousChar != 0) {

          sb.append(previousChar);
          if (counter != 1) {
            sb.append(counter);
          }
        }
        previousChar = ch;
        counter = 1;
      }
    }
    sb.append(previousChar);
    if (counter != 1) {
      sb.append(counter);
    }
    char[] result = sb.toString().toCharArray();
    int i = 0;
    if (chars.length < result.length) {
      return chars.length;
    }

    for (char ch : result) {

      chars[i] = ch;
      i++;

    }

    return i;
  }
}
