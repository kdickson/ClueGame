package main.board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Player {

	public String name;
	public ArrayList<Card> cardsInHand = new ArrayList<Card>();
	public Color color;
	public BoardCell currentLocation;
	public Card disproveSuggestion(Card person, Card room, Card weapon){

		boolean isPerson = false, isRoom = false, isWeapon = false;
		ArrayList<Card> matches = new ArrayList<Card>();
		for(Card c : cardsInHand){
			if(c.equals(person)){
				matches.add(c);
			}else if (c.equals(room)){
				matches.add(c);
			}else if (c.equals(weapon)){
				matches.add(c);
			}
		}
		if(matches.size()>0)
			return matches.get(new Random().nextInt(matches.size()));
		return null;
	}


//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Player other = (Player) obj;
//		if (cardsInHand == null) {
//			if (other.cardsInHand != null)
//				return false;
//		} else if (!cardsInHand.equals(other.cardsInHand))
//			return false;
//		return true;
//	}

	public void addCard(Card c) {
		cardsInHand.add(c);
	}

}
