package com.ram.demolearnprogrammingbypractice.oops;

public class StudentApp {

  public static void main(String[] args) {

    Student usStudent = new UsStudent("Ram", 23, "White");
    Student indiaStudent = new IndiaStudent("Ram", 23, "Reddy");
    printStudentName(usStudent);
    printStudentName(indiaStudent);
  }

  public static void printStudentName(Student student) {
    System.out.println(student.getName());
  }
}
