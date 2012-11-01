	package main.board;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ComputerPlayer extends Player{

	
	
	public ComputerPlayer(String name, Color color) {
		super(name, color);
		resetUnseenCards();
	}
	
	
	
	public ComputerPlayer(String name, Color color, BoardCell currentLocation) {
		super(name, color, currentLocation);
		resetUnseenCards();
	}



	public void resetUnseenCards(){
		unseenCards = new HashSet<Card>();
		for(Card c: Board.getMasterDeck()){
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
		for(BoardCell bc : set){
			if(bc.isDoorway() && previousLocation != bc){
				return bc;
			}else if(bc.isRoom() && previousLocation != bc){
				return bc;
			}
		}
		int i=0;
		int theRandom = new Random().nextInt(set.size());
		for(BoardCell obj : set)
		{
		    if (i == theRandom)
		        return obj;
		    i = i + 1;
		}
		
		return null;
	}
	Set<Card> unseenCards = new HashSet<Card>();
	public void updateSeen(Card seen){
		unseenCards.remove(seen);
	}
	public void createSuggestion(){
		ArrayList<Card> suggestedRooms = new ArrayList<Card>();
		ArrayList<Card> suggestedWeapons = new ArrayList<Card>();
		ArrayList<Card> suggestedPersons = new ArrayList<Card>();
		for(Card c : unseenCards){
			switch(c.cardType){
			case PERSON:
				suggestedPersons.add(c);
				break;
			case ROOM:
				suggestedRooms.add(c);
				break;
			case WEAPON:
				suggestedWeapons.add(c);
				break;
			default:
				break;
			
			}
		}
		if(suggestedRooms.size()>0)
			suggestedRoom = suggestedRooms.get(new Random().nextInt(suggestedRooms.size()));
		else 
			suggestedRoom = null;
		if(suggestedWeapons.size()>0)
			suggestedWeapon = suggestedWeapons.get(new Random().nextInt(suggestedWeapons.size()));
		else
			suggestedWeapon = null;
		if(suggestedPersons.size()>0)
			suggestedPerson = suggestedPersons.get(new Random().nextInt(suggestedPersons.size()));
		else
			suggestedPerson = null;
		
	}
	Card suggestedRoom;
	Card suggestedWeapon;
	Card suggestedPerson;
	public Card getSuggestedRoom() {
		Board b = new Board();
		b.loadLegend("initials.csv");
		return b.getRoomCardFromCell(previousLocation);
		//return suggestedRoom;
	}
	public Card getSuggestedWeapon() {
		return suggestedWeapon;
	}
	public Card getSuggestedPerson() {
		return suggestedPerson;
	}
	BoardCell previousLocation;
}
