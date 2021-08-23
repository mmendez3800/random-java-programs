import java.util.*;

/*
 * Class that produces a text version of Connect Four
 *
 * Within the terminal, a Connect Four board is displayed to the user
 *
 * From there, it takes input from two users to drop their chips (represented as X and O)
 *
 * It will then either indicate a winner in the game or say that a tie was achieved 
 */

public class ConnectFourBoard
{
  /** A two-dimensional character array used to represent the game board */
  private char[][] board;
  
  /** An integer used to store the number of rows in the game board */
  private int numRows;
  
  /** An integer used to store the number of columns in the game board */
  private int numColumns;
  
  /** A constructor that takes in a number of columns and rows, sets these values to equal numRows and numColumns, and
    * initializes the two-dimensional character array representing the board
    * @param rows The number of rows in our game board
    * @param columns The number of columns in our game board
    * */
  public ConnectFourBoard( int rows, int columns )
  {
    // Here we set the number of columns and rows of our game board
    this.numColumns = columns;
    this.numRows = rows;
    
    /* Here we initialize our two-dimensional array to represent the game board with the specified number of columns
     * and rows */
    this.board = new char[ this.numColumns ][ this.numRows ];
    
    // This nested for loop loops through all of the columns and rows in our two-dimensional array
    for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1 )
    {
      for( int rowIndex = 0; rowIndex < this.numRows; rowIndex += 1 )
      {
        // Here we set each value in the array to represent an empty space, thus having it equal to ' '
        this.board[ columnIndex ][ rowIndex ] = ' ';
      }
    }
  }
  
  /** A constructor that creates a game board of six rows and seven columns */
  public ConnectFourBoard()
  {
    /* Here we call the constructor we previously wrote above and set the number of rows to equal 6 and the number of
     * columns to equal 7 */
    this(6, 7);
  }
  
  /** Returns a String which provides a visual representation of our game board, with numbers at the bottom to
    * represent the column number and a border to separate the game board from the numbers
    * @return A String representing our game board with numbers representing the columns
    * */
  public String toString()
  {
    String toReturn = new String();
    
    // This nested for loop loops through all of the rows and columns in the two-dimensional character array
    for( int rowIndex = 0; rowIndex < this.numRows; rowIndex += 1 )
    {
      // When this is executed, it will create a new line and print "|", thus representing a new row
      toReturn += "\n" + "|";
      
      for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1 )
      {
        /* This line will print the empty spaces in the two-dimensional array and also print "|", thus representing
         * column space in the row */
        toReturn += this.board[ columnIndex ][ rowIndex ] + "|";
      }
    }
    
    toReturn += "\n";
    
    // This for loop loops through the number of columns in the game board
    for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1 )
    {
      /* For each column, the String "--" is printed to create a border between the game board and numbers representing
       * the specified column */
      toReturn += "--";
    }
    
    toReturn += "-\n";
    
    // This loop loops through the number of columns in the game board
    for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1 )
    {
      /* For each column, the String " " as well as a number to represent a specified column (e.g. 0 = column number 0,
       * 1 = column number 1, 2 = column number 2, ...) */
      toReturn += " " + columnIndex % 10;
    }
    
    toReturn += "\n";
    return toReturn;
  }
  
  /** Places a checker on the board down a specified column, which falls to the bottommost free space in that column.
    * It does not, however, check to see if the column is a valid input or if the column has a free space to place a
    * checker.
    * @param columns The indicated column on the game board
    * @param checker The char representing a checker to add to the board
    * */
  public void addMove( int columns, char checker )
  {
    // The for loop loops through through all of the rows in a specified column of the game board
    for( int rowIndex = this.numRows - 1; rowIndex >=0; rowIndex -= 1 )
    {
      // This if statement is executed if the specified index in the two-dimensional array is equal to ' '
      if( this.board[ columns ][ rowIndex ] == ' ' )
      {
        // Here we set the index in the two-dimensional array equal to our checker. We then stop the for loop.
        this.board[ columns ][ rowIndex ] = checker;
        break;
      }
    }
  }
  
  /** Clears the game board of any checkers that may have been placed in it */
  public void clear()
  {
    // This nested for loop loops through all of the columns and rows in the two-dimensional array
    for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1 )
    {
      for( int rowIndex = 0; rowIndex < this.numRows; rowIndex += 1 )
      {
        // Here we set each index in the two-dimensional array to equal ' '
        this.board[ columnIndex ][ rowIndex ] = ' ';
      }
    }
  }
  
  /** Takes in a String of columns and places alternating checkers in those columns, starting with 'X'. For example,
    * calling the method with the String as "012345" alternates the 'X's and 'O's into the corresponding column numbers
    * (i.e. 0 = 'X' in column 0, 1 = 'O' in column 1, 2 = 'X' in column 2, ...). Also, if the String was interpreted as
    * "00000", it alternates the 'X's and 'O's into the 0 column.
    * @param moveString A string of integers which can only play for the first 10 columns of a board
    * */
  public void setBoard( String moveString )
  {
    // Here we start by playing 'X'
    char nextChecker = 'X';
    
    // This for loop loops through all of the characters in our String
    for ( int index = 0; index < moveString.length(); index += 1 )
    {
      // Here we determine the column number from the String by changing the character to a numeric value
      int column = Character.getNumericValue( moveString.charAt(index) );
      
      /* This if statement is executed if column is greater than or equal to 0 and column is less than the number of
       * columns in the game board */
      if ( 0 <= column && column < this.numColumns )
      {
        this.addMove(column, nextChecker);
      }
      
      // This if statement is exectued if nextChecker is equal to 'X'
      if ( nextChecker == 'X' )
      {
        // Here we change nextChecker to equal 'O'
        nextChecker = 'O';
      }
      
      else
      {
        // If our nextChecker is equal to 'O', we now change it to equal 'X'
        nextChecker = 'X';
      }
    }
  }
  
  /** Checks to see if a move is allowed on the game board
    * @param column The column number of the game board
    * @return Returns false if the move is not allowed
    * @return Returns true if the move is allowed
    * */
  public boolean allowsMove( int column )
  {
    /* This if statement is executed if the value of column is less than 0, or if the value of column is greater than
     * or equal to the number of columns, or if the first row of the column on the game board is not empty */
    if( column < 0 || column >= this.numColumns || this.board[ column ][0] != ' ' )
    {
      return false;
    }
    
    // We return true since the move is allowed
    return true;
  }
  
  /** Checks to see if the game board if full or not
    * @return Returns false if the game board is not full
    * @return Returns true if the game board is full
    * */
  public boolean isFull()
  {
    // This for loop loops through all of the columns in the game board
    for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1)
    {
      /* This if statement is executed if the index in the two-dimensional array is equal to ' ' (i.e. if one index in 
       * the first row of the game board is an empty space */
      if( this.board[ columnIndex ][0] == ' ' )
      {
        return false;
      }
    }
    
    // We return true since the board is full
    return true;
  }
  
  /** Helper method to see if a player has made a row of four checkers horizontally
    * @param checker The checker of a player which will be checked to see if that player won horizontally
    * @return Returns true if the checker of a player appears four times in the same row (i.e. has won horizontally)
    * @return Returns false if the checker of a player has not won horizontally
    * */
  private boolean winsHorizontal( char checker )
  {
    // The nested for loop loops through all of the rows and all but the last three columns of the game board
    for( int columnIndex = 0; columnIndex < this.numColumns - 3; columnIndex += 1 )
    {
      for( int rowIndex = 0; rowIndex < this.numRows; rowIndex += 1)
      {
        // This if statement is executed if the checker has been found four times consecutively in the same row
        if( this.board[ columnIndex ][ rowIndex ] == checker && 
            this.board[ columnIndex + 1 ][ rowIndex ] == checker &&
            this.board[ columnIndex + 2 ][ rowIndex ] == checker && 
            this.board[ columnIndex + 3 ][ rowIndex ] == checker )
        {
          return true;
        }
      }
    }
    
    // We return false since the player has not won horizontally
    return false;
  }
  
  /** Helper method to see if a player has made a row of four checkers vertically
    * @param checker The checker of a player which will be checked to see if that player won vertically
    * @return Returns true if the checker of a player appears four times in the same column (i.e. has won vertically)
    * @return Returns false if the checker of a player has not won vertically
    * */
  private boolean winsVertical( char checker )
  {
    // The nested for loop loops through all of the columns and all but the last three rows of the game board
    for( int columnIndex = 0; columnIndex < this.numColumns; columnIndex += 1 )
    {
      for( int rowIndex = 0; rowIndex < this.numRows - 3; rowIndex += 1 )
      {
        // This if statement is executed if the checker has been found four times consecutively in the same column
        if( this.board[ columnIndex ][ rowIndex ] == checker &&
            this.board[ columnIndex ][ rowIndex + 1 ] == checker &&
            this.board[ columnIndex ][ rowIndex + 2 ] == checker && 
            this.board[ columnIndex ][ rowIndex + 3 ] == checker )
        {
          return true;
        }
      }
    }
    
    // We return false since the player has not won horizontally
    return false;
  }
  
  /** Helper method to see if a player has made a row of four checkers from the top-left to the bottom-right diagonally
    * @param checker The checker of a player which will be checked to see if that player won diagonally
    * @return Returns true if the checker of a player appears four times diagonally (i.e. has won by connecting four
    * from the top-left to the bottom-right)
    * @return Returns false if the checker of a player has not won diagonally from the top-left to the bottom-right
    * */
  private boolean winsDiagonalMannerOne( char checker )
  {
    // The nested for loop loops through all but the last three columns and rows in the game board
    for( int columnIndex = 0; columnIndex < this.numColumns - 3; columnIndex += 1 )
    {
      for( int rowIndex = 0; rowIndex < this.numRows - 3; rowIndex += 1 )
      {
        /* This if statement is executed if the checker has been found four times consecutively from the top-right to
         * the bottom-left diagonally */
        if( this.board[ columnIndex ][ rowIndex ] == checker && 
            this.board[ columnIndex + 1][ rowIndex + 1 ] == checker &&
            this.board[ columnIndex + 2 ][ rowIndex + 2 ] == checker && 
            this.board[ columnIndex + 3 ][ rowIndex + 3 ] == checker )
        {
          return true;
        }
      }
    }
    
    // We return false since the player has not won diagonally from top-right to bottom-left
    return false;
  }
  
  /** Helper method to see if a player has made a row of four checkers from the bottom-left to the top-right diagonally
    * @param checker The checker of a player which will be checked to see if that player won diagonally
    * @return Returns true if the checker of a player appears four times diagonally (i.e. has won by connecting four
    * from the bottom-left to the top-right)
    * @return Returns false if the checker of a player has not won diagonally from the bottom-left to the top-right
    * */
  private boolean winsDiagonalMannerTwo( char checker )
  {
    //The nested for loop loops through all but the first three columns and rows in the game board
    for( int columnIndex = 0; columnIndex < this.numColumns - 3; columnIndex += 1 )
    {
      for( int rowIndex = this.numRows - 1; rowIndex >= 3; rowIndex -= 1 )
      {
        /* This if statement is executed if the checker has been found four times consecutively from the bottom-left to
         * the top-right diagonally */
        if( this.board[ columnIndex ][ rowIndex ] == checker &&
            this.board[ columnIndex + 1 ][ rowIndex - 1 ] == checker &&
            this.board[ columnIndex + 2 ][ rowIndex - 2 ] == checker &&
            this.board[ columnIndex + 3 ][ rowIndex - 3 ] == checker )
        {
          return true;
        }
      }
    }
    
    // We return false since the player has not won diagonally from bottom-left to top-right
    return false;
  }
  
  /** Checks to see if a checker has won by seeing if the character appears four times consecutively, either
    * horizontally, vertically, or diagonally
    * @param checker The checker of a player which will be checked to see if that player won horizontally, vertically,
    * or vertically
    * @return Returns true if the checker of a player appears four times consecutively
    * @return Returns false if the checker of a player does not appear four times consecutively
    * */
  public boolean winsFor( char checker )
  {
    // This if statement is executed if the helper method winsHorizontal returns true
    if( this.winsHorizontal(checker) == true )
    {
      return true;
    }
    
    // This else if statement is executed if the helper method winsVertical returns true
    else if( this.winsVertical(checker) == true )
    {
      return true;
    }
    
    // This else if statement is executed if the helper method winsDiagonalMannerOne returns true
    else if( this.winsDiagonalMannerOne(checker) == true )
    {
      return true;
    }
    
    // This else if statement is executed if the helper method winsDiagonalMannerTwo returns true
    else if( this.winsDiagonalMannerTwo(checker) == true )
    {
      return true;
    }
    
    else
    {
      // We return false since the checker of a player has not appeared four times consecutively
      return false;
    }
  }
  
  /** Hosts a new game of Connect Four, which alternates the checker to represent a player */
  public void hostGame()
  {
    // Here we clear the Connect Four board
    this.clear();
    
    System.out.println( "\n" + "Welcome to Connect Four!" + "\n" + this );
    char player;
    
    // This while loop is executed when 'X' does not win, when 'O' does not win, or when the board is not full
    while( this.winsFor('X') == false || this.winsFor('O') == false || this.isFull() == false )
    {
      // Here we create a Scanner object which accepts an input
      Scanner input = new Scanner( System.in );
      
      // We assign our player to be represented by the character 'X'
      player = 'X';
      
      System.out.println( "X's choice: " );
      
      // This primitive variable represents the column which the player chooses to drop their checker onto the board
      int nextMove = input.nextInt();
      
      // This while loop is executed when the move the player wants to make is not valid
      while( this.allowsMove(nextMove) == false )
      {
        // Here we ask the player to choose a valid column and allow the player to input a new number
        System.out.println("Please choose a valid column: ");
        nextMove = input.nextInt();
      }
      
      // Here we make the move since the input of the player is now valid
      this.addMove(nextMove, player);
      
      // This prints out our game board after every input
      System.out.println(this);
      
      // This if statement is executed when the player has won the game
      if( this.winsFor(player) == true )
      {
        System.out.println( player + " wins!" + "\n" + this );
        break;
      }
       
      // This else if statement is executed when the game has tied
      else if( this.isFull() == true )
      {
        System.out.println( "The game has ended in a tied. Please play again and defeat your enemy!" + "\n" + this );
        break;
      }
      
      // We now assign our player to be represented by the character 'O'
      player = 'O';
      
      System.out.println( "O's choice: " );
      nextMove = input.nextInt();
      
      // This while loop is executed when the move the player wants to make is not valid
      while( this.allowsMove(nextMove) == false )
      {
        // Here we ask the player to choose a valid column and allow the player to input a new number
        System.out.println("Please choose a valid column: ");
        nextMove = input.nextInt();
      }
      
      // Here we make the move since the input of the player is now valid
      this.addMove(nextMove, player);
      
      // This prints out our game board after every input
      System.out.println(this);
      
      // This if statement is executed when the player has won the game
      if( this.winsFor(player) == true )
      {
        System.out.println( player + " wins!" + "\n" + this );
        break;
      }
      
      // This else if statement is executed when the game has tied
      else if( this.isFull() == true )
      {
        System.out.println( "The game has ended in a tied. Please play again and defeat your enemy!" + "\n" + this );
        break;
      }
    }
  }

  public static void main(String[] args)
  {
    ConnectFourBoard c = new ConnectFourBoard();
    c.hostGame();
  }
}
