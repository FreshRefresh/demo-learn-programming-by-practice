package com.ram.demolearnprogrammingbypractice.oops;

public final class UsStudent extends Student {

  private final String color;

  public UsStudent(String name, int age, String color) {
    super(name, age);
    this.color = color;
  }

  @Override
  public String getName() {

    return "I am us us student my name is " + super.getName();
  }

  public String getColor() {
    return color;
  }
}
