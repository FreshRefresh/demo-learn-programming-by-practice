package com.ram.demolearnprogrammingbypractice.premitives;

/**
 * @see <a href ="https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html"> Java Primitive Data Types</a>
 */
public class PracticePrimitives {

  public static void main(String[] args) {

    // https://docs.oracle.com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.2
    byte byteValue = 123;        // 1 Byte -> 8 Bits -> 128 to 127
    short shortValue = 123;      // 2 Bytes
    int intValue = 123;          // 4 Bytes
    long longValue = 123L;       // 8 Bytes
    char charValue = 'A';        // 2 Bytes
    boolean sayYesOrNo = true;   // 1 Bit
    float price = 10.50f;        // 4 Bytes
    double price2 = 10.50d;      // 8 Bytes

    byte byteSum = (byte) (byteValue + byteValue);
    char charSum = (char) (charValue + charValue);
    short sumShort = (short) (shortValue + shortValue);  // Addition of two short values will result to integer
    short sumShort2 = 343 + 444;
    int sum2 = intValue + intValue;  // Addition of two short values will result to integer

    Byte wrapperByte = byteValue;
    Short wrapperShort = shortValue;
    Integer wrapperInteger = intValue;
    Long wrapperLong = longValue;
    Character wrapperChar = charValue;
    Boolean wrapperBoolean = sayYesOrNo;
    Float wrapperFloat = price;
    Double wrapperDouble = price2;

    Byte tmep = Byte.valueOf("122");
    // Integer to Binary
//    System.out.println("Integer 5 to Binary : " + Integer.toBinaryString(5));
    System.out.println("Integer -1 to Binary : " + Integer.toBinaryString(-1) + " Number of bits : " + Integer.toBinaryString(-5).length());// 2's complement
//    System.out.println("Long 5 to Binary : " + Long.toBinaryString(5) + " Number of bits : " + Long.toBinaryString(5).length());// 2's complement
//    System.out.println("Long -5 to Binary : " + Long.toBinaryString(-5) + " Number of bits : " + Long.toBinaryString(-5).length());// 2's complement
    //1 * 2 pw 2+ 0 * 2 pw 1+  2pow 0 * 1

    int res = computeAverage(Integer.MAX_VALUE, Integer.MAX_VALUE);
    System.out.println("res  = " + res);
    System.out.println("Integer max = " + Integer.toBinaryString(Integer.MAX_VALUE));
    System.out.println("Average of 10, 20 = " + res);


    float q = 506.123456f;
    float fs = 6.1231233f;
    System.out.println("Float value = " + Float.MAX_EXPONENT);
    System.out.println("Float value = " + Float.MAX_VALUE);
    System.out.println("Float value = " + Double.MAX_VALUE);

  }

  public static int computeAverage(int a, int b) {
    int result = (a + b) / 2;
    //int result = a + (b - a) / 2;
    return result;
  }


/// Float - Base Exponent and Mantissa  = 1 , 8 bits for
}
