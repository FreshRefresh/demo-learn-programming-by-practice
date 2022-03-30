package com.ram.demolearnprogrammingbypractice.oops;

public class IndiaStudent extends Student {

  private final String cast;
  public IndiaStudent(String name, int age, String cast) {
    super(name, age);
    this.cast = cast;
  }
  @Override
  public String getName() {
    return "I am Iam us studnet my name is " + super.getName();
  }

  public String getCast() {
    return cast;
  }
}
