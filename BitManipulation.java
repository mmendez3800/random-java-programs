/*
Practice how bits function and are used to represent numbers.

This program will performs different review and manipulation of numbers through their bit format. It can either indicate the most significant 2 bits of number,
the most significant N bits of a number, or convert a number of N bits into 8 bits
*/

import java.io.*;

public class BitManipulation
{
  /*
  This method  preserve the 2 most significant (i.e. leftmost) bits in each 8-bit number
  Example: The most significant 2 bits of 250 (11111010) are 11 in binary which is equal to 3 in decimal. 
  */
  private static int mostSignificant2(int num) 
  {
    return num >> 6;
  }  
  
  /*
  This method  preserve the N most significant (i.e. leftmost) bits in each 8-bit number
  Example: If N=3 ,the most significant 3 bits of 250 (11111010) are 111 in binary which is equal to 7 in decimal. 
  */
  private static int mostSignificantN(int num, int N)
  {
    return num >> (8 - N); 
  }
  
  /* 
  This method  converts num with N bits into 8 bits
  Example: If if num is 2 and N is 3, it means num is 010 in binary. This method will convert it into 01000000, which is eual to 64 in decimal.
  */
  private static int shiftNBitsTo8(int num, int N)
  {
    return num << (8 - N);
  }

  public static void main (String[] args) throws IOException
  {
    // The following section displays the most significant 2 bits of a number
    int result = mostSignificant2(18);
    System.out.println("The most significant 2 bits of 18 (00010010) are " + result);
    if (result == 0) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 0 (00)");
    }
    
    result = mostSignificant2(82);
    System.out.println("The most significant 2 bits of 82 (01010010) are " + result);
    if (result == 1) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 1 (01)");
    }
    
    result = mostSignificant2(0000);
    System.out.println("The most significant 2 bits of 0000 (00000000) are " + result);
    if (result == 0) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 0 (00)");
    }
    
    result = mostSignificant2(250);
    System.out.println("The most significant 2 bits of 250 (11111010) are " + result);
    if (result == 3) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 3 (11)");
    }
    
    // The following section displays the most significant N bits of a number
    result = mostSignificantN(82, 2);
    System.out.println("The most significant 2 bits of 82 (01010010) are " + result);
    if (result == 1) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 1 (01)");
    }
    
    result = mostSignificantN(82, 3);
    System.out.println("The most significant 3 bits of 82 (01010010) are " + result);
    if (result == 2) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 2 (010)");
    }
    
    result = mostSignificantN(82, 4);
    System.out.println("The most significant 4 bits of 82 (01010010) are " + result);
    if (result == 5) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 5 (0101)");
    }
    
    result = mostSignificantN(82, 7);
    System.out.println("The most significant 7 bits of 82 (01010010) are " + result);
    if (result == 41) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 41 (0101001)");
    }

    // The following section converts a number with N bits into a number with 8 bits
    result = shiftNBitsTo8(6, 3);
    System.out.println("110 when shifted to 8 bits, it becomes 11000000, in decimal: " + result);
    if (result == 192) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 192 (11000000)");
    }
    
    result = shiftNBitsTo8(1, 7);
    System.out.println("0000001 when shifted to 8 bits, it becomes 00000010, in decimal: " + result);
    if (result == 2) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 2 (00000010)");
    }
    
    result = shiftNBitsTo8(2, 3);
    System.out.println("010 when shifted to 8 bits, it becomes 01000000, in decimal: " + result);
    if (result == 64) {
      System.out.println("This is correct!");
    }
    else {
      System.out.println("This is WRONG!  Should be 64 (01000000)");
    }
  }
}
