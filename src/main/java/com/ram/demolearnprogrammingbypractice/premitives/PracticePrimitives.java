package com.ram.demolearnprogrammingbypractice.premitives;

/**
 *
 * @see <a href ="https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html"> Java Primitive Data Types</a>
 */
public class PracticePrimitives {

  public static void main(String[] args) {

    //https://docs.oracle.com/javase/specs/jls/se7/html/jls-4.html#jls-4.2.2

    byte byteValue = 123;  // 1 Byte - 8 Bits -128 to 127
    short shortValue = 123; // 2 Bytes
    int intValue = 123;  // 4 Bytes
    long longValue = 123L; // 8 Bytes
    char charValue = 'A'; // 2 Bytes
    char charValue1 = 49;  /// ???
    boolean sayYesOrNo = true; // 1 bit
    float price = 10.50f; // 4 Bytes
    double price2 = 10.50d; // 8 Bytes

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

    // Integer to Binary
    System.out.println("Integer 5 to Binary : " + Integer.toBinaryString(5));
    System.out.println("Integer -5 to Binary : " + Integer.toBinaryString(-5) + " Number of bits : " + Integer.toBinaryString(-5).length());// 2's complement
    System.out.println("Long 5 to Binary : " + Long.toBinaryString(5) + " Number of bits : " + Long.toBinaryString(5).length());// 2's complement
    System.out.println("Long -5 to Binary : " + Long.toBinaryString(-5) + " Number of bits : " + Long.toBinaryString(-5).length());// 2's complement

  }

}
