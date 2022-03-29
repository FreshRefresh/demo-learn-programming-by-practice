package com.ram.demolearnprogrammingbypractice.oops;

public class StudentFactory {

  public static Student createStudent() {
    return new Student("Dummy name", 1);
  }
}
