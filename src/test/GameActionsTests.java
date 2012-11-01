package test;

import static org.junit.Assert.fail;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import main.board.BadConfigException;
import main.board.Board;
import main.board.BoardCell;
import main.board.Card;
import main.board.ComputerPlayer;
import main.board.HumanPlayer;
import main.board.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameActionsTests {
	Board b;
	static Card scarlet,knife,hall,green,candlestick,study,library,white,rope;
	@Before
	public void tester() throws BadConfigException{
		b = new Board();
		b.loadConfigFiles("work1.csv");
		b.loadLegend("initials.csv");
		b.calcAdjacencies();
		b.initializePlayers();
		b.initializeDeck();
		b.deal();
	}
	@BeforeClass
	public static void initTestCards() {
		scarlet = new Card("Miss Scarlet",Card.CardType.PERSON);
		knife = new Card("Knife",Card.CardType.WEAPON);
		hall = new Card("HALL",Card.CardType.ROOM);
		green = new Card("Reverend Green",Card.CardType.PERSON);
		candlestick = new Card("Candlestick",Card.CardType.WEAPON);
		study = new Card("STUDY",Card.CardType.ROOM);
		library = new Card("LIBRARY",Card.CardType.ROOM);
		white = new Card("Mrs. White",Card.CardType.PERSON);
		rope = new Card("Rope",Card.CardType.WEAPON);
	}
	
	@Test
	public void checkAccusing() {

		b.setAnswer(scarlet, hall, knife);
		Assert.assertTrue(b.checkAccusation(scarlet,hall , knife));
		Assert.assertFalse(b.checkAccusation(green, hall, knife));
		Assert.assertFalse(b.checkAccusation(scarlet,study , knife));
		Assert.assertFalse(b.checkAccusation(scarlet,hall , candlestick));
		Assert.assertFalse(b.checkAccusation(green,study , candlestick));
	}
	
	@Test
	public void testTargetRandomSelection() {
		ComputerPlayer player = new ComputerPlayer("Reverend Green", Color.green);
		// Pick a location with no rooms in target, just three targets
		b.calcTargets(b.calcRoomIndex(14, 0), 2);
		int loc_12_0Tot = 0;
		int loc_14_2Tot = 0;
		int loc_15_1Tot = 0;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(b.getTargets());
			if (selected == b.getCellAt(12, 0))
				loc_12_0Tot++;
			else if (selected == b.getCellAt(14, 2))
				loc_14_2Tot++;
			else if (selected == b.getCellAt(15, 1))
				loc_15_1Tot++;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		Assert.assertEquals(100, loc_12_0Tot + loc_14_2Tot + loc_15_1Tot);
		// Ensure each target was selected more than once
		Assert.assertTrue(loc_12_0Tot > 10);
		Assert.assertTrue(loc_14_2Tot > 10);
		Assert.assertTrue(loc_15_1Tot > 10);							
	}

	@Test
	public void testTargetRandomSelectionFromJustVisitingRoom() {
		ComputerPlayer player = new ComputerPlayer("Reverend Green", Color.green);
		player.setPreviousLocation(b.getCellAt(4,2));
		// Pick a location that you can move into a room you were just in
		b.calcTargets(b.calcRoomIndex(4, 4), 2);
		int loc_4_2Tot = 0;
		int loc_5_3Tot = 0;
		int loc_6_4Tot = 0;
		int loc_5_5Tot = 0;
		int loc_3_5Tot = 0;
		int loc_2_4Tot = 0;
		int loc_4_6Tot = 0;
		// Run the test 100 times
		for (int i=0; i<1000; i++) {
			BoardCell selected = player.pickLocation(b.getTargets());
			if (selected == b.getCellAt(4, 2))
				loc_4_2Tot++;
			else if (selected == b.getCellAt(5, 3))
				loc_5_3Tot++;
			else if (selected == b.getCellAt(6, 4))
				loc_6_4Tot++;
			else if (selected == b.getCellAt(5, 5))
				loc_5_5Tot++;
			else if (selected == b.getCellAt(3, 5))
				loc_3_5Tot++;
			else if (selected == b.getCellAt(2, 4))
				loc_2_4Tot++;
			else if (selected == b.getCellAt(4, 6))
				loc_4_6Tot++;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		Assert.assertEquals(1000, loc_4_2Tot + loc_5_3Tot + loc_6_4Tot + loc_5_5Tot + loc_3_5Tot + loc_2_4Tot + loc_4_6Tot);
		// Ensure each target was selected more than once
		Assert.assertTrue(loc_4_2Tot > 10);
		Assert.assertTrue(loc_5_3Tot > 10);
		Assert.assertTrue(loc_6_4Tot > 10);
		Assert.assertTrue(loc_5_5Tot > 10);
		Assert.assertTrue(loc_3_5Tot > 10);
		Assert.assertTrue(loc_2_4Tot > 10);
	}
	
	@Test
	public void testTargetGoToNewRoom() {
		ComputerPlayer player = new ComputerPlayer("Reverend Green", Color.green);
		player.setPreviousLocation(b.getCellAt(2,4));
		// Pick a location that you can move into a room you were just in
		b.calcTargets(b.calcRoomIndex(4, 4), 2);
		int loc_4_2Tot = 0;
		int loc_5_3Tot = 0;
		int loc_6_4Tot = 0;
		int loc_5_5Tot = 0;
		int loc_3_5Tot = 0;
		int loc_2_4Tot = 0;
		// Run the test 100 times
		for (int i=0; i<100; i++) {
			BoardCell selected = player.pickLocation(b.getTargets());
			if (selected == b.getCellAt(4, 2))
				loc_4_2Tot++;
			else if (selected == b.getCellAt(5, 3))
				loc_5_3Tot++;
			else if (selected == b.getCellAt(6, 4))
				loc_6_4Tot++;
			else if (selected == b.getCellAt(5, 5))
				loc_5_5Tot++;
			else if (selected == b.getCellAt(3, 5))
				loc_3_5Tot++;
			else if (selected == b.getCellAt(2, 4))
				loc_2_4Tot++;
			else
				fail("Invalid target selected");
		}
		// Ensure we have 100 total selections (fail should also ensure)
		Assert.assertEquals(100, loc_4_2Tot + loc_5_3Tot + loc_6_4Tot + loc_5_5Tot + loc_3_5Tot + loc_2_4Tot);
		// Ensure each target was selected more than once
		Assert.assertTrue(loc_4_2Tot ==100);
		Assert.assertTrue(loc_5_3Tot ==0);
		Assert.assertTrue(loc_6_4Tot ==0);
		Assert.assertTrue(loc_5_5Tot ==0);
		Assert.assertTrue(loc_3_5Tot ==0);
		Assert.assertTrue(loc_2_4Tot ==0);
	}
	
	@Test
	public void testOnePlayerOneMatch(){
		Player p = new Player("Reverend Green", Color.green);
		p.addCard(scarlet);
		p.addCard(candlestick);
		p.addCard(green);
		p.addCard(hall);
		p.addCard(knife);
		p.addCard(study);
		//disprove weapon
		Assert.assertEquals(knife,p.disproveSuggestion(white, library, knife));
		//disprove person
		Assert.assertEquals(green,p.disproveSuggestion(green, library, rope));
		//disprove room
		Assert.assertEquals(hall,p.disproveSuggestion(white, hall, rope));
		//check can't disprove
		Assert.assertEquals(null,p.disproveSuggestion(white, library, rope));
	}
	
	@Test
	public void testMultiplePossibleMatches(){
		Player p = new Player("Reverend Green", Color.green);
		p.addCard(scarlet);
		p.addCard(candlestick);
		p.addCard(study);
		
		int scarletTot = 0;
		int candlestickTot = 0;
		int studyTot = 0;
		for(int i=0;i<1000;i++){
			Card c = p.disproveSuggestion(scarlet, candlestick, study);
			if(c.equals(scarlet))
				scarletTot++;
			else if(c.equals(candlestick))
				candlestickTot++;
			else if(c.equals(study))
				studyTot++;
			else
				fail("invalidCard");
		}
		Assert.assertTrue(scarletTot>0);
		Assert.assertTrue(candlestickTot>0);
		Assert.assertTrue(studyTot>0);
	}
	@Test
	public void testAllPlayersQueried(){
		HumanPlayer hp = new HumanPlayer("Miss Scarlet", Color.red);
		b.setHumanPlayer(hp);
		ComputerPlayer cp1 = new ComputerPlayer("Reverend Green", Color.green);
		ComputerPlayer cp2 = new ComputerPlayer("Mrs. White", Color.white);
		b.computerPlayers.clear();
		Assert.assertTrue(b.computerPlayers.isEmpty());
		b.computerPlayers.add(cp1);
		b.computerPlayers.add(cp2);
		hp.addCard(candlestick);
		cp1.addCard(scarlet);
		cp2.addCard(hall);
		//suggestion no players could disprove
		Assert.assertEquals(null, b.handleSuggestion(white, library, knife));
		//make suggestion only human can disprove
		b.setCurrentPlayer(cp1);
		Assert.assertEquals(candlestick, b.handleSuggestion(white, library, candlestick));
		//humans player turn return null
		b.setCurrentPlayer(hp);
		Assert.assertEquals(null, b.handleSuggestion(white, library, candlestick));
		//only cp2 can disprove
		b.setCurrentPlayer(cp2);
		Assert.assertEquals(null, b.handleSuggestion(white, hall, rope));
		//test a suggestion that two players can disprove
		//test that both players disproves the suggestion more than once
		int hpDisproveTot = 0;
		int cp1DisproveTot = 0;
		for(int i=0;i<100;i++){
			b.setCurrentPlayer(cp2);
			Card c = b.handleSuggestion(scarlet, library, candlestick);
			if(c.equals(scarlet))
				cp1DisproveTot++;
			else if(c.equals(candlestick))
				hpDisproveTot++;
			else
				fail("invalid Card");
		}
		Assert.assertTrue(hpDisproveTot>0);
		Assert.assertTrue(cp1DisproveTot>0);
	}

	@Test
	public void testComputerPlayerMakingSuggestion(){
		//one correct suggestion
		ComputerPlayer cp1 = new ComputerPlayer("Reverend Green", Color.green);
		cp1.resetUnseenCards();
		System.out.println("the size of Board.deck is " + Board.getMasterDeck().size());
		for(Card c :Board.getMasterDeck()) {
			if(!c.equals(green) && !c.equals(candlestick)) {
				System.out.println("updating seen!!!!");
				cp1.updateSeen(c);
			}
		}
		//this is da hall
		cp1.setPreviousLocation(b.getCellAt(8, 19));
		cp1.createSuggestion();
		
		Assert.assertEquals(hall, cp1.getSuggestedRoom());
		Assert.assertEquals(green, cp1.getSuggestedPerson());
		Assert.assertEquals(candlestick, cp1.getSuggestedWeapon());
	}
	

	@Test
	public void testComputerPlayerMakingMultipleSuggestions(){
		//one correct suggestion
		ComputerPlayer cp1 = new ComputerPlayer("Reverend Green", Color.green);
		cp1.resetUnseenCards();
		int greenAccused = 0;
		int scarletAccused = 0;
		int candlestickAccused = 0;
		int knifeAccused = 0;
		for(Card c :Board.getMasterDeck()) {
			if(!c.equals(green) && !c.equals(candlestick) && !c.equals(scarlet) && !c.equals(study) && !c.equals(knife)) {
				cp1.updateSeen(c);
			}
		}
		//this is da hall
		for(int i=0; i<100; i++){
			
			//this is da hall
			cp1.setPreviousLocation(b.getCellAt(8, 19));
			cp1.createSuggestion();
			Assert.assertEquals(hall, cp1.getSuggestedRoom());
			Card suggestedPerson = cp1.getSuggestedPerson();
			Card suggestedWeapon = cp1.getSuggestedWeapon();
			if (suggestedPerson.equals(green)) {
				greenAccused++;
			}
			else if (suggestedPerson.equals(scarlet)){
				scarletAccused++;
			}
			else {
				fail("Invalid person");
			}
			if(suggestedWeapon.equals(candlestick)){
				candlestickAccused++;
			}
			else if (suggestedWeapon.equals(knife)){
				knifeAccused++;
			}
			else {
				fail("Invalid Weapon");
			}
		}
		Assert.assertTrue(greenAccused>0);
		Assert.assertTrue(scarletAccused>0);
		Assert.assertTrue(candlestickAccused>0);
		Assert.assertTrue(knifeAccused>0);
	}
}
