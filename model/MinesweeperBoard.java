package model;

/**
 * Formalises the logic for where the bombs will be placed on the minesweeper board
 * 
 * @author Kyle Hodgetts
 * @author Peter Barta
 * 
 */
public class MinesweeperBoard {

	/** Width of the board */
	private int width;
	
	/** Height of the board*/
	private int height;
	
	/** Number of mines on the board*/
	private int mines;
	
	/**Squares that make up the board */
	private Square[][] squares;
	
	/**
	 * Constructs a minesweeper board with {@width} x {@height} squares 
	 * Among them contains {@mines} amount of mines.
	 * @param width
	 * @param height
	 * @param mines
	 */
	public MinesweeperBoard(int width, int height, int mines){
		this.width = width;
		this.height = height;
		this.mines = mines;
		
		squares = new Square[width][height];
		for(int i = 0; i<width; ++i){
			for(int j = 0; j<height; ++j){
				squares[i][j] = Square.ZERO;
			}
		}
		this.placeMines();
		
		for(int i = 0; i<width; ++i){
			for(int j = 0; j<height; ++j){
				if(squares[i][j] != Square.MINE){
					switch(numOfMinesAround(i,j)){
					case 1:
						squares[i][j] = Square.ONE;
						break;
					case 2:
						squares[i][j] = Square.TWO;
						break;
					case 3:
						squares[i][j] = Square.THREE;
						break;
					case 4:
						squares[i][j] = Square.FOUR;
						break;
					case 5:
						squares[i][j] = Square.FIVE;
						break;
					case 6:
						squares[i][j] = Square.SIX;
						break;
					case 7:
						squares[i][j] = Square.SEVEN;
						break;
					case 8:
						squares[i][j] = Square.EIGHT;
						break;
					}
						
				}
			}
		}
	}
	
	private void placeMines(){
		int x;
		int y;
		for(int i = 0; i<mines; ++i){
			x = (int)(Math.random()*width);
			y = (int)(Math.random()*height);
			if(squares[x][y]!= Square.MINE){
				squares[x][y]= Square.MINE;
			}
			else{
				i-=1;
			}
		}
	}
	
	/**
	 * Debug method to print the grid
	 */
	public void printGrid()
	{
	   for(int i = 0; i < width; i++)
	   {
	      for(int j = 0; j < height; j++)
	      {
	         System.out.print(squares[i][j] +" ");
	      }
	      System.out.println("\n");
	   }
	}
	
	/**
	 * 
	 * @param x coordinate of the square
	 * @param y coordinate of the square
	 * @return the number of mines around the given square. 0 if the square contains a mine
	 */
	public int numOfMinesAround(int x, int y){
		int mines = 0;
		if(squares[x][y] == Square.MINE){
			return mines;
		}
		else{

			/*
			 * With thanks to: 
			 * https://code.google.com/a/eclipselabs.org/p/minesweepergame/source/browse/src/Minesweeper.java
			 */
			for (int q = (x - 1) ; q <= (x + 1) ; q++)
			{
				for (int w = y - 1 ; w <= y + 1 ; w++)
				{
					while (true)
					{
						if (q < 0 || w < 0 || q >= getWidth() || w >= getHeight())
							break;
						if (squares[q][w] == Square.MINE)
							mines++;
						break;
					}
				}
			}
		}
		
		return mines;
	}

	/**
	 * 
	 * @return width of the board
	 */
	public int getWidth(){
		return this.width;
	}
	
	/**
	 * 
	 * @return the height of the board
	 */
	public int getHeight(){
		return this.height;
	}
	
	/**
	 * 
	 * @return the number of mines placed on the board
	 */
	public int getMineNum(){
		return this.mines;
	}
	
	/**
	 * 
	 * @param x coordinate of the square.
	 * @param y coordinate of the square.
	 * @return the label value of the square.
	 */
	public int getSquareValue(int x, int y){
		return squares[x][y].returnValue(squares[x][y]);
	}
	
	public Square getSquare(int x, int y){
		return squares[x][y];
	}
	
}
