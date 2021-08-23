import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

/*
 * Class that implements a word cloud
 *
 * Given a certain text file, the program produces a word cloud which displays the size of each word in the file by the number of times it appears
 *
 * The more frequent a word appears, the larger it will appear in the word cloud 
 */

public class WordCloud
{
  /** An array of unique strings, to appear in the word cloud. */
  public String[] uniqueWords;
  
  /** Counts corresponding to the number of times each word in the uniqueStrings array appeard in the original file.
    * These counts will be used to size the words in the word cloud. */
  public int[] counts;
  
  /** Returns a String array consisting of all of the words in a given file and arranges them according to the ASCII
    * value of each word in the file
    * @param filename The file from which we will grab the text from
    * @return The String array consisting of all of the text in the file, arranged in order of increasing ASCII value
    * */
  public String[] getWordsFromFile( String filename ) throws IOException
  {
    // Here we create a File object from the parameter and a Scanner object using the File object
    File sourceFile = new File( filename );
    Scanner input = new Scanner( sourceFile );
    
    // This String will be the collection of all words in the file
    String allText = "";
    
    // This while loop is executed when the Scanner object has more data to read
    while( input.hasNext() )
    {
      /* A String consisting of a line ending with the line separator is created and is concatenated with " " to
       * produce a String consisting of all of the text in the file */
      String text = input.nextLine();
      allText = allText.concat( text + " " );
    }
    
    input.close();
    String[] words = new String[ allText.length() ];
    
    /* We use the String split method to create a String array around matchs of the String " " and use the Arrays sort
     * method to arrange the String array in increasing ASCII values */
    words = allText.split( " " );
    Arrays.sort( words );
    
    return words;
  }
  
  /** Returns an integer which represents the number of unique elements in a String array
    * @param words The String array containing a distinct number of elements
    * @return The number of unique elements in the called String array
    * */
  private int numberOfUniqueWords( String[] words )
  {
    /* Two Strings are created, one to represent the String in the String array that is being compared for distinction
     * and another to represent the String that is being compared to */
    String previous = words[0];
    String current = words[0];
    
    int count = 1;
    
    //  This for loop loops through all of the elements in the String array
    for( int index = 1; index < words.length; index++ )
    {
      current = words[index];
      
      // This if statement is executed when current is not equivalent to previous
      if( current.equals( previous) == false )
      {
        // The count increases by one to notify that another distinct element in String array has been found
        count++;
        
        /* The String previous is set to equal the String current so that the new String found can be used to compare
         * the rest of the Strings in the String array */
        previous = current;
      }
    }
    
    return count;
  }
  
  /** Creates a String array which contains each unique element of the String array in the parameter. Also creates an
    * integer array to represent the number of times a specific element appears in the String array of the parameter.
    * @param words The String array which will be used as reference for creating the new String array and integer array
    * */
  public void setUniqueAndCounts( String[] words )
  {
    // The private method above is used to determine the length of the String array and the integer array
    uniqueWords = new String[ numberOfUniqueWords( words ) ];
    counts = new int[ numberOfUniqueWords( words ) ];
    
    /* Two Strings are created, one to represent the String in the String array that is being compared for distinction
     * and another to represent the String that is being compared to */
    String previous = words[0];
    String current = words[0];
    
    int numberOfRepeats = 1;
    int indexOfUniqueAndCounts = 0;
    
    // This for loop loops through all of the elements of the String array in the parameter
    for( int index = 1; index < words.length; index++ )
    {
      uniqueWords[0] = words[0];
      
      // We assign current to equal the nth index of words to compare this String to the String representing previous
      current = words[index];
      
      // This if statement is executed when current is equivalent to previous
      if( current.equals( previous ) )
      {
        // numberOfRepeats increases by one to indicate that the String representing previous appears again
        numberOfRepeats++;
        
        // The specified element of the integer array changes to represent the number of times a String has appeared
        counts[indexOfUniqueAndCounts] = numberOfRepeats;
      }
      
      else
      {
        /* The nth plus one element of uniqueWords becomes current since this String is different from previous. Also
         * the nth plus one elemtn of counts becomes one to represent that a new String has appeared at least once */
        uniqueWords[indexOfUniqueAndCounts + 1] = current;
        counts[indexOfUniqueAndCounts + 1] = 1;
        
        // We make previous equal current so that there is new String to be used for comparison on repetition
        previous = current;
        
        /* The value of numberOfRepeats changes to one to indicate the number of times the new String appears in the
         * String array of the parameter */
        numberOfRepeats = 1;
        
        /* The value of indexOfUniqueAndCounts increases by one to represent the new nth element of both arrays,
         * representing the new String that has appeared in the String array of the parameter */
        indexOfUniqueAndCounts++;
      }
    }
  }
  
  /** Creates a JFrame which shows each unique word in a file and scales them based on the number of times that word has
    * appeared using the new String array and new integer array from the previous method
    * */
  public void displayWords()
  {
    JFrame wordFrame = new JFrame( "Word Cloud" );
    wordFrame.setLayout( new FlowLayout( FlowLayout.LEFT ) );
    
    // This for loop loops through all of the elements in String[] uniqueWords
    for( int index = 0; index < uniqueWords.length; index++ )
    {
      /* The size of the font is determined by accessing the corresponding element in int[] count and multiplying this
       * value by 15 to visually see how many times a word has appeared compared to others */
      Font currentFont = new Font( "SansSerif", Font.PLAIN, counts[index] * 15 );
      
      JLabel wordLabel = new JLabel( uniqueWords[index] );
      wordLabel.setFont( currentFont );
      wordFrame.add( wordLabel );
    }
    // This method resizes the JFrame window to accomodate the content within it
    wordFrame.pack();
    
    wordFrame.setVisible( true );
  }
  
  /** Uses the three public methods above and incorporates them to create a method that does all of the following:
    * reads the text, counts the words, and then graphically displays the word cloud
    * */
  public static void main( String[] args ) throws IOException
  {
    WordCloud w = new WordCloud();
    String[] allText = w.getWordsFromFile("wordCloud.txt");
    w.setUniqueAndCounts( allText );
    w.displayWords();
  }
}
