package main.board;

import java.awt.Color;

public class HumanPlayer extends Player{

	public HumanPlayer(String name, Color color) {
		super(name, color);
	}

	public HumanPlayer(String name, Color color, BoardCell currentLocation) {
		super(name, color, currentLocation);
	}
	
	

}
