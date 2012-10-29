package main.board;

import java.awt.Color;
import java.util.ArrayList;

public class Player {

	public String name;
	public ArrayList<Card> cardsInHand = new ArrayList<Card>();
	public Color color;
	public BoardCell currentLocation;
	public Card disproveSuggestion(Card person, Card room, Card weapon){
		return null;
	}
	public void addCard(Card c) {
		cardsInHand.add(c);
	}
	
}
