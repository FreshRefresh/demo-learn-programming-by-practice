package com.ram.demolearnprogrammingbypractice.interviews;

/**
 * Write a function to find the target elements positions in a given sorted array of elements.
 * If the target element not found then return {-1,-1 }.
 */
public class BinarySearchApp {

  public static void main(String[] args) {

    int[] inputArray = {1, 2, 3, 4, 5, 5, 5, 5, 9};
    int targetElement = 5;
    int[] result = findIndices(inputArray, targetElement);
    System.out.println("Result = { " + result[0] + "  " + result[1] + " }");
  }

  public static int[] findIndices(int[] inputArray, int target) {

    if (inputArray != null && inputArray.length > 0) {
      int targetElementStartIndex = findIndex(inputArray, target, true);
      if (targetElementStartIndex != -1) {
        int targetElementEndIndex = findIndex(inputArray, target, false);
        return new int[]{targetElementStartIndex, targetElementEndIndex};
      }
    }

    return new int[]{-1, -1}; // If the target element is not found.
  }

  public static int findIndex(int[] inputArray, int targetElement, boolean isTargetElementStartIndex) {

    int targetElementIndex = -1;
    if (inputArray != null && inputArray.length > 0) {
      int startIndex = 0;
      int endIndex = inputArray.length - 1;
      int middleIndex = 0;

      while (startIndex <= endIndex) {
        middleIndex = startIndex + (endIndex - startIndex) / 2;
        if (inputArray[middleIndex] == targetElement) {
          targetElementIndex = middleIndex;
          if (isTargetElementStartIndex) {
            endIndex = middleIndex - 1;
          } else {
            startIndex = middleIndex + 1;
          }
        } else if (targetElement > inputArray[middleIndex]) {
          startIndex = middleIndex + 1;
        } else {
          endIndex = middleIndex - 1;
        }
      }
    }

    return targetElementIndex; // If the target element is not found.
  }
}
