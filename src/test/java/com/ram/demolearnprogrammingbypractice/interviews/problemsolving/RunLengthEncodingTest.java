package com.ram.demolearnprogrammingbypractice.interviews.problemsolving;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RunLengthEncodingTest {

  @Test
  void compress() {
    RunLengthEncoding runLengthEncoding = new RunLengthEncoding();
    char[] input1 = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
    assertEquals(6, runLengthEncoding.compress(input1));

    char[] input12 = {'a'};
    assertEquals(1, runLengthEncoding.compress(input12));

    char[] input4 = {'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'};
    assertEquals(4, runLengthEncoding.compress(input4));
  }
}