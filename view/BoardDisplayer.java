package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import model.MinesweeperBoard;
import model.Square;

/**
 * Displays the game interface.
 * The board will be generated based on the logic of the <code>MinesweeperBoard</code>
 * @author Kyle Hodgetts
 * @author Peter Barta
 *
 */
public class BoardDisplayer extends JFrame implements ActionListener{
	private MinesweeperBoard board;

	private JPanel optionPanel;
	private JToolBar toolBar;
	private JButton btnNewGame;
	private JComboBox<String> cbDifficulty;
	private final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};

	private JPanel boardPanel;
	private ArrayList<JButton>buttons;
	private JButton[][] buttonss;

	private JPanel statusPanel;
	private JLabel lblStatus = new JLabel();
	private JLabel lblFlags;
	private int flags;
	private int mines;

	public BoardDisplayer(){
		super("Minesweeper");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.initComponents();
		this.pack();
	}

	/*
	 * Create all display Components
	 */
	private void initComponents(){
		this.setLayout(new BorderLayout());

		/*
		 * Instantiate optionPanel components
		 */
		optionPanel = new JPanel(new FlowLayout());
		toolBar = new JToolBar();
		btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(this);
		cbDifficulty = new JComboBox<String>(DIFFICULTIES);
		toolBar.add(btnNewGame);
		toolBar.add(cbDifficulty);
		optionPanel.add(toolBar);

		/*
		 * Instantiate boardPanel components
		 */
		this.newGame();

		/*
		 * Instantiate statusPanel components
		 */
		statusPanel = new JPanel(new FlowLayout());
		statusPanel.add(lblStatus);
		lblFlags = new JLabel("Flags: " + flags);
		statusPanel.add(lblFlags);
		
		/*
		 * Add container panels to main frame.
		 */
		this.add(optionPanel, BorderLayout.NORTH);
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(statusPanel, BorderLayout.SOUTH);
		board.printGrid();
	}

	/**
	 * Generates a new game
	 * @author Kyle Hodgetts
	 * @author Peter Barta
	 *
	 */
	class NewGameHandler {
		private String selectedDiff;
		private int boardWidth;
		private int boardHeight;
		private int boardMines;

		/**
		 * Constructs a new game
		 * @param aDiff a difficulty which determines the size of the board and number of mines.
		 */
		public NewGameHandler(String aDiff){
			this.selectedDiff = aDiff;
			flags = 0;
			if(this.selectedDiff.equals("Easy")){
				this.boardWidth = this.boardHeight = 9;
				this.boardMines = mines = 10;
			}
			else if (this.selectedDiff.equals("Medium")){
				this.boardWidth = this.boardHeight = 16;
				this.boardMines = mines = 40;
			}
			else if (this.selectedDiff.equals("Hard")){
				this.boardWidth = 30;
				this.boardHeight = 16;
				this.boardMines = mines = 99;
			}
		}

		/**
		 * 
		 * @return the width of the board
		 */
		public int getBoardWidth(){
			return this.boardWidth;
		}

		/**
		 * 
		 * @return the height of the board
		 */
		public int getBoardHeight(){
			return this.boardHeight;
		}

		/**
		 * 
		 * @return the number of mines on the board
		 */
		public int getBoardMines(){
			return this.boardMines;
		}

	}

	/**
	 * Reveals the button value when pressed.
	 * Game over if the button sheilds a bomb
	 * @author Kyle Hodgetts
	 * @author Peter Barta
	 *
	 */
	class ButtonPressedHandler implements MouseListener{
		private int xCoord;
		private int yCoord;

		/**
		 * Constructs the handler for a button
		 * @param x coordinate of the button
		 * @param y coordinate of the button
		 */
		public ButtonPressedHandler(int x, int y){
			this.xCoord = x;
			this.yCoord = y;
		}


		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}

		@Override
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e)){
				JButton pressed = (JButton)e.getSource();
				if(!pressed.getText().equals("F"))
				{
					if(pressed.isEnabled()){
						int value = board.getSquareValue(xCoord, yCoord);
						pressed.setEnabled(false);
						
                        /*
                         * No bomb on button, clear surrounding squares
                         */
						if(value == 0){
							for(int i = 1; i<9; ++i){
								try{
									switch(i){
										case 1:
											if(board.getSquare(xCoord, yCoord+1) != Square.MINE){
												buttonss[xCoord][yCoord+1].setText(Integer.toString(board.getSquareValue(xCoord, yCoord+1))); 
												buttonss[xCoord][yCoord+1].setEnabled(false);
											}
											break;
										case 2:
											if(board.getSquare(xCoord, yCoord-1) != Square.MINE){
												buttonss[xCoord][yCoord-1].setText(Integer.toString(board.getSquareValue(xCoord, yCoord-1))); 
												buttonss[xCoord][yCoord-1].setEnabled(false);
											}
											break;
										case 3:
											if(board.getSquare(xCoord-1, yCoord) != Square.MINE){
												buttonss[xCoord-1][yCoord].setText(Integer.toString(board.getSquareValue(xCoord-1, yCoord))); 
												buttonss[xCoord-1][yCoord].setEnabled(false);
											}
											break;
										case 4:
											if(board.getSquare(xCoord+1, yCoord) != Square.MINE){
												buttonss[xCoord+1][yCoord].setText(Integer.toString(board.getSquareValue(xCoord+1, yCoord))); 
												buttonss[xCoord+1][yCoord].setEnabled(false);
											}
											break;
										case 5:
											if(board.getSquare(xCoord-1, yCoord-1) != Square.MINE){
												buttonss[xCoord-1][yCoord-1].setText(Integer.toString(board.getSquareValue(xCoord-1, yCoord-1))); 
												buttonss[xCoord-1][yCoord-1].setEnabled(false);
											}
											break;
										case 6:
											if(board.getSquare(xCoord-1, yCoord+1) != Square.MINE){
												buttonss[xCoord-1][yCoord+1].setText(Integer.toString(board.getSquareValue(xCoord-1, yCoord+1))); 
												buttonss[xCoord-1][yCoord+1].setEnabled(false);
											}
											break;
										case 7:
											if(board.getSquare(xCoord+1, yCoord+1) != Square.MINE){
												buttonss[xCoord+1][yCoord+1].setText(Integer.toString(board.getSquareValue(xCoord+1, yCoord+1))); 
												buttonss[xCoord+1][yCoord+1].setEnabled(false);
											}
											break;
										case 8:
											if(board.getSquare(xCoord+1, yCoord-1) != Square.MINE){
												buttonss[xCoord+1][yCoord-1].setText(Integer.toString(board.getSquareValue(xCoord+1, yCoord-1))); 
												buttonss[xCoord+1][yCoord-1].setEnabled(false);
											}
											break;
									}
								}
								catch(Exception ex){
									//Off the board, so ignore it
								}
							}
							
						}
						if(value == 99){
							pressed.setText("X");
							for(JButton b : buttons){
								b.setEnabled(false);
							}
							lblStatus.setText("GAME OVER!");
						}
						else if(hasWon()){
							lblStatus.setText("You Win!");
						}
						else{
							pressed.setText(Integer.toString(board.getSquareValue(xCoord, yCoord)));
							
						}
					}

				}
			}
			else if(SwingUtilities.isRightMouseButton(e)){
				JButton pressed = (JButton)e.getSource();
				if(!pressed.getText().equals("F") && flags < mines){
					if(pressed.isEnabled()){
						pressed.setText("F");
						pressed.setEnabled(false);
						++flags;
						
					}
				}
				else{
					pressed.setText("");
					pressed.setEnabled(true);
					--flags;
				}
				lblFlags.setText("Flags: " + flags);
			}
		}
		

	}

	/**
	 * Generates a new game
	 */
	public void newGame(){
		lblStatus.setText("In Progress");
		buttons = new ArrayList<JButton>();
		NewGameHandler ng = new NewGameHandler((String)cbDifficulty.getSelectedItem());
		boardPanel = new JPanel(new GridLayout(ng.getBoardHeight(), ng.getBoardWidth()));
		this.board = new MinesweeperBoard(ng.getBoardHeight(), ng.getBoardWidth(), ng.getBoardMines());
		buttonss = new JButton[board.getWidth()][board.getHeight()];
		for(int i = 0; i<board.getWidth(); ++i){
			for(int j = 0; j<board.getHeight(); ++j){
				JButton x = new JButton();
				buttons.add(x);
				buttonss[i][j] = x;
				x.addMouseListener(new ButtonPressedHandler(i,j));
				boardPanel.add(x);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.remove(boardPanel);
		this.newGame();
		this.add(boardPanel, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}

	public boolean hasWon(){
		for(JButton b : buttons){
			if(b.isEnabled()){
				return false;
			}
		}
		return true;
	}

}
