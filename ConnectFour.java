import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** A class that implements a graphical version of Connect Four */
public class ConnectFour extends JFrame
{
  /** The underlying board that will hold the state of the game */
  private ConnectFourBoard theBoard;

  /** The turn of a player */
  private char turn;
  
  /** The status message at the top of the window */
  private JLabel status;
  
  /** A constructor that creates a new ConnectFour game with 6 rows and 7 columns */
  public ConnectFour()
  {
    this( 6, 7 );
  }

  /** 
   * A constructor that creates a new ConnectFour game with a specified number of rows and columns
   * @param row The number of rows in the ConnectFour game
   * @param column The number of columns in the ConnectFour game
   * */
  public ConnectFour( int row, int column )
  {
    // Player X starts
    this.turn = 'X';
    
    // Makes a new underlying board
    this.theBoard = new ConnectFourBoard(row, column);
    
    // The reset button
    JButton jbtReset = new JButton("New Game");
    jbtReset.addActionListener( new ResetListener() );
    
    // This message will always display the current status
    this.status = new JLabel("Welcome to Connect Four! Red's turn");
    
    // This is the panel that will hold the BoardCells
    JPanel displayBoard = gameBoard(row, column);

    // This uses a BorderLayout to lay out the game board
    setLayout( new BorderLayout() );
    add(this.status, BorderLayout.NORTH);
    add(displayBoard, BorderLayout.CENTER);
    add(jbtReset, BorderLayout.SOUTH);
    
    // This sizes and shows the board
    pack();
    setVisible(true);
  }
  
  /** An interface used in the creation of our "New Game" button */
  class ResetListener implements ActionListener
  {
    public void actionPerformed( ActionEvent e )
    {
      // The board is cleared
      theBoard.clear();
      
      // This message displays the current status
      status.setText("Welcome to ConnectFour! Red's turn");
      
      // Player X starts
      turn = 'X';
      
      repaint();
    }
  }
  
  /**
   * This helper method creates a ConnectFour board containing a cell within every index of each row and column
   * @param row The number of rows in the game board
   * @param column The number of columns in the game board
   * */
  private JPanel gameBoard( int row, int column )
  {
    // A new JPanel is created with a GridLayout to accomodate for the necessary number of rows and columns
    JPanel board = new JPanel();
    board.setLayout( new GridLayout(row, column) );
    
    // This for loop loops through all of the rows and columns in our game board
    for( int rowIndex = 0; rowIndex < row; rowIndex++ )
    {
      for( int columnIndex = 0; columnIndex < column; columnIndex++ )
      {
        // We add a cell, located at a specified row and column, into our game board
        board.add( new BoardCell(rowIndex, columnIndex) );
      }
    }
    
    return board;
  }
  
  /** This is the method that is called when a BoardCell is clicked on
    * @param col The specified column in the ConnectFour game
    * */
  private void makeMove( int col )
  {
    // This if statement is executed when either 'X' wins, 'O' wins, or when the game is tied
    if ( (this.theBoard.winsFor('X') ) || ( this.theBoard.winsFor('O') ) || ( this.theBoard.isFull() ) )
    {
      // This causes the if statement to execute nothing
      return;
    }
    
    // This if statement is executed when the player is allowed to make a move on the game board
    if ( this.theBoard.allowsMove(col) )
    {
      this.theBoard.addMove(col, this.turn);
      
      // This if-else statement changes the character of turn after a player has placed his/her checker
      if(this.turn == 'X')
      {
        this.turn = 'O';
      }
      else
      {
        this.turn = 'X';
      }
    }
    
    String color;
    
    // This if-else statement changes color to represent the colored checker of a player
    if(this.turn == 'X')
    {
      color = "Red";
    }
    else
    {
      color = "Yellow";
    }
    
    // When 'X' wins, this if statement allows status to indicate that player 'X' has won
    if( this.theBoard.winsFor('X') )
    {
      this.status.setText("Game over. Red wins!");
    }
    
    // When 'O' wins, this else if statement allows status to indicate that player 'O' has won
    else if( this.theBoard.winsFor('O') )
    {
      this.status.setText("Game over. Yellow wins!");
    }
    
    // When the game board is full, this else if statement allows status to indicate that the game has ended in a tie
    else if( this.theBoard.isFull() )
    {
      this.status.setText("Game over. Tie game!");
    }
    
    // When none of the above have happened, this else statment allows status to indicate that it is a player's turn
    else
    {
      this.status.setText(color + "'s turn.  Click a column to play.");
    }
    
    repaint();
  }
  

  /** An inner class that represents one graphical cell in the ConnectFour board */
  class BoardCell extends JPanel
  {
    /** The row in which this BoardCell appears in the board */
    private int row;
    
    /** The column in which this BoardCell appears in the board */
    private int column;
    
    /** A constructor that creates a new BoardCell object at row r and column c */
    BoardCell( int r, int c )
    {
      // Sets the row and column of the cell
      this.row = r;
      this.column = c;
      
      // We add a MouseListener to handle the clicks on a BoardCell
      addMouseListener( new PlayListener() );
    }
  
    /** Returns the preferred size for this BoardCell */
    public Dimension getPreferredSize()
    {
      return new Dimension( 50, 50 );
    }
  
    /** Paints the BoardCell */
    protected void paintComponent( Graphics g )
    { 
      // Here we call the paintComponent of the JPanel
      super.paintComponent(g);
      
      // We find out which character is placed in a specific row and column of the board
      char contents = theBoard.getContents(this.row, this.column);
      
      // We set the color of the board to be blue and fill in a rectangle with the appropriate size for our game board
      g.setColor(Color.BLUE);
      g.fillRect( 0, 0, getWidth(), getHeight() );
      
      // This if statement sets the BoarderCell to be white if there is no checker in the specific row and column
      if (contents == ' ')
      {
        g.setColor(Color.WHITE);
      }
      
      // Thise else if statement sets the BoarderCell to be red if 'X' is the checker in the specific row and column
      else if (contents == 'X')
      {
        g.setColor(Color.RED);
      }
      
      /* This else statmenent sets the BoarderCell to be yellow since 'O' will have a checker in the specific row and
       * column*/
      else
      {
        g.setColor(Color.YELLOW);
      }
      
      // This creates the holes in our board to represent where checkers and free spaces are
      g.fillOval( (int)( 0.1 * getWidth() ), (int)( 0.1 * getHeight() ),
                  (int)( 0.8 * getWidth() ), (int)( 0.8 * getHeight() ) );
    }
    
    /** This is the listener that will handle clicks on the board cell */
    class PlayListener implements MouseListener
    {
      /** Informs the ConnectFour object that the corresponding column has been clicked */
      public void mouseClicked(MouseEvent e) 
      {
        makeMove(column);
      }
      
      // We implement these methods, even though they do not do anything, because we have to
      public void mousePressed(MouseEvent e)
      { }
      
      public void mouseReleased(MouseEvent e)
      { }
      
      public void mouseEntered(MouseEvent e)
      { }
      
      public void mouseExited(MouseEvent e)
      { }
    }
  }

  public static void main(String[] args)
  {
    ConnectFour c = new ConnectFour();
  }
} 
