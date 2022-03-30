package com.ram.demolearnprogrammingbypractice.oops;

/**
 *
 */
public class Student {

  private final String name;
  private final int age;

  public Student(String name, int age) {
    if (age > 100) {
      throw new IllegalArgumentException("Invalid age ...");
    }
    this.name = name;
    this.age = age;
  }

  public String getName() {
    System.out.println("I am in super student...");
    return name;
  }

  public int getAge() {
    return age;
  }

}
