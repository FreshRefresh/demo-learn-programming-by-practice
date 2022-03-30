package com.ram.demolearnprogrammingbypractice.premitives;

import java.math.BigDecimal;

public class FloatingPointApp {

  public static void main(String[] args) {
    exploreFloats();
  }

  public static void exploreFloats() {

    double f1 = 0.3D;
    double f2 = 0.1D;
    double f3 = f1 - f2;

    System.out.println(f3);

    BigDecimal b1 = BigDecimal.valueOf(f1);
    BigDecimal b2 = BigDecimal.valueOf(f2);
    BigDecimal b3  = b1.subtract(b2);
    System.out.println(b3);
  }

}
