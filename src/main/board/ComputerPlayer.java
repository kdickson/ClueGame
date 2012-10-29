package main.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ComputerPlayer extends Player{
	public ComputerPlayer(){
		resetUnseenCards();
	}
	public void resetUnseenCards(){
		unseenCards = new HashSet<Card>();
		for(Card c: Board.deck){
			unseenCards.add(c);	
		}
		
	}
	public BoardCell getPreviousLocation() {
		return previousLocation;
	}
	public void setPreviousLocation(BoardCell previousLocation) {
		this.previousLocation = previousLocation;
	}
	public BoardCell pickLocation(Set<BoardCell> set){
		return null;
	}
	Set<Card> unseenCards = new HashSet<Card>();
	public void updateSeen(Card seen){
		unseenCards.remove(seen);
	}
	public void createSuggestion(){
	}
	Card suggestedRoom;
	Card suggestedWeapon;
	Card suggestedPerson;
	public Card getSuggestedRoom() {
		return suggestedRoom;
	}
	public Card getSuggestedWeapon() {
		return suggestedWeapon;
	}
	public Card getSuggestedPerson() {
		return suggestedPerson;
	}
	BoardCell previousLocation;
}
