package test;

import java.awt.Color;

import junit.framework.Assert;
import main.board.BadConfigException;
import main.board.Board;
import main.board.Card;
import main.board.ComputerPlayer;
import main.board.HumanPlayer;
import main.board.Player;

import org.junit.Before;
import org.junit.Test;

public class GameSetupTest {
	Board b;
	
	@Before
	public void tester() throws BadConfigException{
		b = new Board();
		b.loadConfigFiles("work1.csv");
		b.loadLegend("initials.csv");
		b.deal();
	}
	
	@Test
	public void testLoadingPeople() {
		//Test first 
		ComputerPlayer p = b.computerPlayers.get(0);
		Assert.assertEquals("Ms.Peacock", p.name);
		Assert.assertEquals(Color.BLUE, p.color);
		Assert.assertEquals(42, p.currentLocation.getRow());
		Assert.assertEquals(21, p.currentLocation.getCol());
		//Test last 
		p = b.computerPlayers.get(4);
		Assert.assertEquals("Reverend Green", p.name);
		Assert.assertEquals(Color.GREEN, p.color);
		Assert.assertEquals(11, p.currentLocation.getRow());
		Assert.assertEquals(18, p.currentLocation.getCol());
		//Test human 
		HumanPlayer h = b.humanPlayer;
		Assert.assertEquals("Miss Scarlet", h.name);
		Assert.assertEquals(Color.RED, h.color);
		Assert.assertEquals(8, h.currentLocation.getRow());
		Assert.assertEquals(24, h.currentLocation.getCol());
	}
	@Test
	public void testLoadingCards() {
		Assert.assertEquals(21,b.deck.size());
		int playerCount=0;
		int weaponCount=0;
		int locationCount=0;
		
		for(Card c : b.deck) {
			switch (c.cardType){
			case PERSON:
				playerCount++;
				break;
			case ROOM:
				locationCount++;
				break;
			case WEAPON:
				weaponCount++;
				break;
			default:
				break;
			}
		}
		Assert.assertEquals(6, playerCount);
		Assert.assertEquals(6, weaponCount);
		Assert.assertEquals(9, locationCount);
		Card sampleRoom = new Card("BALLROOM",Card.CardType.ROOM);
		Card sampleWeapon = new Card("Knife",Card.CardType.WEAPON);
		Card samplePerson = new Card("Colonel Mustard",Card.CardType.PERSON);
 		Assert.assertTrue(b.deck.contains(samplePerson));
 		Assert.assertTrue(b.deck.contains(sampleWeapon));
 		Assert.assertTrue(b.deck.contains(sampleRoom));
 		
	}	
	@Test
	public void testDealingCards() {
		b.deal();
		//check all cards are dealt
		Assert.assertEquals(0,b.deck.size());
		//All players have roughly (within one) the same number cards
		int humanTotalCards = b.humanPlayer.cardsInHand.size();
		for(ComputerPlayer p: b.computerPlayers){
			Assert.assertTrue(Math.abs(humanTotalCards-p.cardsInHand.size()) <=1);
		}
		//check that one card isn't given to two players
		for(Player p: b.computerPlayers)
			for(Card c: p.cardsInHand) 
				for(Player p2: b.computerPlayers)
					if(p!=p2)
						Assert.assertFalse(p2.cardsInHand.contains(c));

		
		
	}
}