import java.io.*;
import java.util.*;

/** 
 * Class that implements some manipulation of data using characters from strings
 *
 * The first part implements a Scrabble score calculation. Based on the word provided, it will calculate the Scrabble score that would be given
 *
 * The second part implements a Caesar Cipher. Based on the text file provided, you can either encrypt or decrypt the text using Caesar Cipher
 * When encrypting or decrypting, you can indicate the nuber of rotations to perform in order to see the resulting text
 * See https://en.wikipedia.org/wiki/Caesar_cipher for more details on Caesar Cipher
 */
public class WordFun
{
  /** Return the score of the letter in scrabble.
    * @param ch The letter in question
    * @return The scrabble value of the letter
    * */
  private int letterScore( char ch )
  {
    char lowerCase = Character.toLowerCase( ch );
    switch ( lowerCase )
    {
      case 'a': return 1;
      case 'b': return 3;
      case 'c': return 3;
      case 'd': return 2;
      case 'e': return 1;
      case 'f': return 4;
      case 'g': return 2;
      case 'h': return 4;
      case 'i': return 1;
      case 'j': return 8;
      case 'k': return 5;
      case 'l': return 1;
      case 'm': return 3;
      case 'n': return 1;
      case 'o': return 1;
      case 'p': return 3;
      case 'q': return 10;
      case 'r': return 1;
      case 's': return 1;
      case 't': return 1;
      case 'u': return 1;
      case 'v': return 4;
      case 'w': return 4;
      case 'x': return 8;
      case 'y': return 4;
      case 'z': return 10;
      default: return 0;
    }
  }

  /** Returns the value of a word as if it were played in Scrabble
    * @param word The word in question
    * @return The value of the word when played in Scrabble
    * */
  public int scrabbleScore( String word )
  {
    int sum = 0;
    for( int index = 0; index < word.length(); index++ )
    {
      char letter = word.charAt( index );
      int value = letterScore( letter );
      sum = sum + value;
    }
    return sum;
  }
  
  /** Returns a character that corresponds to the character in the parameter with the specified rotation
    * @param letter The character which will be rotated
    * @param rotation The number of rotations the character will take
    * @return The character that results from the rotation
    * */
  private char rot( char letter, int rotation )
  {
    /* This new rotation returns a value ranging from 0 to 25 for all possible positive and negative integer values
     * (e.g. 45 = 19 or -35 = 17) */
    int newRotation = ( rotation + ( 26 * Math.abs( rotation ) ) ) % 26;
    
    int value = (int) letter;
    
    // This if statement is executed if the ASCII value of a character ranges from 'A' to 'Z'
    if( value >= 'A' && value <= 'Z' )
    {
      value = value + newRotation;
      
      // This if statement is executed if the ASCII value exceeds the value of 'Z'
      if( value > 'Z' )
      {
        value = value - 26;
      }
    }
    
    // This if statement is executed if the ASCII value of a character ranges from 'a' to 'z'
    else if( value >= 'a' && value <= 'z' )
    {
      value = value + newRotation;
      
      // This if statement is executed if the ASCII value exceeds the value of 'z'
      if( value > 'z' )
      {
        value = value - 26;
      }
    }
    
    else
    {
      value = value;
    }
    return (char) value;
  }
  
  /** Returns a String that is encrypted by changing each character using a certain rotation
    * @param s The String that will be encrypted
    * @param rotation The number of rotations each character in the String will go through
    * @return The new encrypted String
    * */
  public String encrypt( String s, int rotation )
  {
    String newWord = null;
    char[] array = s.toCharArray();
    for( int index = 0; index < array.length; index++ )
    {
      char oldLetter = array[index];
      
      // Here we call upon our rot method to change a character in the character array using a certain rotation
      char newLetter = rot( oldLetter, rotation );
      
      // This if statement is executed when the String newWord is equal to null
      if( newWord == null )
      {
        newWord = String.valueOf( newLetter );
      }
      
      else
      {
        newWord = newWord + String.valueOf( newLetter );
      }
    }
    return newWord;
  }
  
  /** Returns a String that is decrypted by changing each character using a certain rotation
    * @param s The String that will be decrypted
    * @param rotation The number of rotations each character in the String will go through
    * @return The new decrypted String
    * */
  public String decrypt( String s, int rotation )
  {
    // Here we use the encrypt method with a negative rotation to decrypt the String s
    String newWord = encrypt( s, -rotation );
    
    return newWord;
  }
  
  /** Reads from the file named by the first argument, encrypts it using a certain number of rotations, and writes the
   * result to the file named by the second argument
   * @param infile The first file which we will encrypt
   * @param outfile The second file which contains the encryption of the firsst file
   * @param rotation The number of rotations to encrypt the first file
   * */
  public void encryptFile( String infile, String outfile, int rotation ) throws IOException
  {
    // Here we create a File object from the first parameter and create a Scanner object using the File object
    File sourceFile1 = new File( infile );
    Scanner input = new Scanner( sourceFile1 );
    
    // Here we create a File object from the second parameter and create a PrintWriter object using the File Object
    File sourceFile2 = new File( outfile );
    PrintWriter outputFile = new PrintWriter( sourceFile2 );
    
    /* This while loop acquires each line from the first File object, prints it, encrypts it, and writes it into the
     * second File object */ 
    while( input.hasNext() )
    {
      String one = input.nextLine();
      System.out.println(one);
      String newOne = encrypt( one, rotation );
      outputFile.print(newOne);
    }
    
    outputFile.close();
    input.close();
    
    // Here we create a Scanner object using the second File object
    Scanner output = new Scanner( sourceFile2 );
    
    // This while loop acquires each line from the second File object and prints it
    while( output.hasNext() )
    {
      String two = output.nextLine();
      System.out.println(two);
    }
    
    output.close();
  }
  
  /** Reads from the file named by the first argument, decrypts it using a certain number of rotations, and writes the
   * result to the file named by the second argument
   * @param infile The first file which we will encrypt
   * @param outfile The second file which contains the encryption of the firsst file
   * @param rotation The number of rotations to encrypt the first file
   * */
  public void decryptFile(String infile, String outfile, int rotation) throws IOException
  {
    // Here we create a File object from the first parameter and create a Scanner object using the File object
    File sourceFile1 = new File( infile );
    Scanner input = new Scanner( sourceFile1 );
    
    // Here we create a File object from the second parameter and create a PrintWriter object using the File Object
    File sourceFile2 = new File( outfile );
    PrintWriter outputFile = new PrintWriter( sourceFile2 );
    
    /* This while loop acquires each line from the first File object, prints it, decrypts it, and writes it into the
     * second File object */ 
    while( input.hasNext() )
    {
      String one = input.nextLine();
      System.out.println(one);
      String newOne = decrypt(one, rotation);
      outputFile.print(newOne);
    }
    
    outputFile.close();
    input.close();
    
    // Here we create a Scanner object using the second File object
    Scanner output = new Scanner( sourceFile2 );
    
    // This while loop acquires each line from the second File object and prints it
    while( output.hasNext() )
    {
      String two = output.nextLine();
      System.out.println(two);
    }
    
    output.close();
  }

  /**
   * Main method to test the WordFun class
   */
  public static void main( String[] args ) throws IOException
  {
    //Part 1. Scrabble Scoring Tests
    WordFun f = new WordFun();
    System.out.println("Score for 'Student' = " + f.scrabbleScore("Student") + " ...(should be 8)");
    System.out.println("Score for 'programming' = " + f.scrabbleScore("programming") + " ...(should be 19)" + "\n");
    
    //Part 2. The Caesar Cipher Tests
    System.out.println("Encrypting Text: Programming in Java");
    String encryptedText = f.encrypt("Programming in Java", 17);
    System.out.println("Expected Result: Gifxirddzex ze Armr");
    System.out.println("Actual Result: " + encryptedText);

    System.out.println("Decrypting Text: Byffi yhwlsjncih qilfx");
    String decryptedText = f.decrypt("Byffi yhwlsjncih qilfx", -58);
    System.out.println("Expected Result: Hello encryption world");
    System.out.println("Actual Result: " + decryptedText + "\n");
    
    //Part 3a. The encryptFile Test
    System.out.println("Test for encryptFile");
    f.encryptFile("testMessage.txt", "testMessageEncrypt.txt", 42);
    System.out.println("");
    
    //Part 3b. The decryptFile Test
    System.out.println("Test for decryptFile");
    f.decryptFile("secretMessage.txt", "secretMessageDecrypt.txt", -39);
  }
} 
