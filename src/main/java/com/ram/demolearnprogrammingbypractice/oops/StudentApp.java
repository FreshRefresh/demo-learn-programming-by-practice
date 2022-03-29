package com.ram.demolearnprogrammingbypractice.oops;

public class StudentApp {

  public static void main(String[] args) {

    Student suresh = new Student("Suresh", 1001);
//    s1.name = "Suresh";
//    s1.age = 1000;
//    suresh.setName("Suresh");
//    suresh.setAge(34);

    Student ram = StudentFactory.createStudent();

  }
}
