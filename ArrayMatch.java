/** 
 * Class that implements some basic matching functions on an array of integers 
 */
public class ArrayMatch
{
  private int[] storedArray;
  
  /** 
   * Constructor that takes an integer array that is stored in the object to match against and makes a copy of it 
   * @param inputArray the integer array that will be used for our comparisons
   */
  public ArrayMatch( int[] inputArray )
  {
    storedArray = new int[inputArray.length];
    for( int i = 0; i < inputArray.length; i++ )
    {
      storedArray[i] = inputArray[i];
    }
  }
  
  /**
   * Method to return a string with the values of the integers in the integer array
   * @return a string with the integer array information
   */
  public String toString()
  {
    String ret = "[";
    for( int i = 0; i < storedArray.length-1; i++ )
    {
      ret += storedArray[i] + ", ";
    }
    return ret + storedArray[storedArray.length-1] + "]";
  }
  
  /** 
   * Method to see if the stored array contains a particular integer
   * @param toCheck the integer to check against the array
   * @return true if the stored array contains toCheck
   * @return false if the store array does not contain toCheck
   */
  public boolean contains( int toCheck )
  {
    for( int index = 0; index < storedArray.length; index++ )
    {
      if( storedArray[index] == toCheck )
      {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Method to see if the stored array contains a particulat integer at least four times in a row
   * @param toCheck the integer to check agaisnt the array
   * @return true if the stored array contains toCheck at least four times in a row
   * @return false if the stored array does not contain toCheck at least four times in a row
   */
  public boolean fourInARow( int toCheck )
  {
    for( int index = 0; index < storedArray.length - 3; index++ )
    {
      if( storedArray[index] == toCheck && storedArray[index + 1] == toCheck && storedArray[index + 2] == toCheck &&
          storedArray[index + 3] == toCheck )
      {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Main method to test the ArrayMatch class
   */
  public static void main( String[] args )
  {
    int[] myArray = {2, 1, 1, 4, 3, 3, 3, 3, 1, 1};
    ArrayMatch tester = new ArrayMatch( myArray );
    System.out.println( "test array is " + tester );
    System.out.print( "Checking whether 1 is in the array (should be true)..." );
    System.out.println( tester.contains( 1 ) );
    System.out.print( "Checking whether 2 is in the array (should be true)..." );
    System.out.println( tester.contains( 2 ) );
    System.out.print( "Checking whether 0 is in the array (should be false)..." );
    System.out.println( tester.contains( 0 ) );
    System.out.print( "Checking whether there are four 3's in a row " + "(should be true )..." );
    System.out.println( tester.fourInARow( 3 ) );
    System.out.print( "Checking whether there are four 1's in a row " + "(should be false )..." );
    System.out.println( tester.fourInARow( 1 ) );
  }
}
