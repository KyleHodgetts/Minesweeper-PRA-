package main;

import model.MinesweeperBoard;

public class BoardTest {
	public static void main(String[] args){
		MinesweeperBoard mb = new MinesweeperBoard(5,5,10);
		mb.printGrid();
	}
}
