package test;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import main.board.Board;
import main.board.BoardCell;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class BoardAdjTargetTests {
	private static Board board;
	
	@Before
    public void beforeMethod() {
    	board = new Board();
    	
    	try{
            board.loadConfigFiles("work1.csv");
            board.loadLegend("initials.csv");
        }catch(Exception e){
            Assert.fail(e.toString());
        } 
    	
    	board.calcAdjacencies();
    }

	// Ensure that player does not move around within room
	@Test
	public void testAdjacenciesInsideRooms()
	{
		
		// Test a corner
		LinkedList<Integer> testList = board.getAdjList(board.getCellAt(0, 0));
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(board.getCellAt(3, 0));
		Assert.assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(board.getCellAt(15, 20));
		Assert.assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(board.getCellAt(18, 11));
		Assert.assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(board.getCellAt(2, 3));
		Assert.assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(board.getCellAt(3, 3));
		Assert.assertEquals(0, testList.size());
	}

	// Ensure that the adjacency list from a doorway is only the
	// walkway. NOTE: This test could be merged with door 
	// direction test. 
	@Test
	public void testAdjacencyRoomExit()
	{
		// TEST DOORWAY RIGHT 
		LinkedList<Integer> testList = board.getAdjList(board.getCellAt(4, 2));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcRoomIndex(4,3)));
		// TEST DOORWAY LEFT
		testList = board.getAdjList(board.getCellAt(2, 13));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcRoomIndex(2, 12)));
		//TEST DOORWAY DOWN
		testList = board.getAdjList(board.getCellAt(4, 8));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcRoomIndex(5, 8)));
		//TEST DOORWAY UP
		testList = board.getAdjList(board.getCellAt(7, 4));
		Assert.assertEquals(1, testList.size());
		Assert.assertTrue(testList.contains(board.calcRoomIndex(6, 4)));
		
	}

	// Test a variety of walkway scenarios
	@Test
	public void testAdjacencyWalkways()
	{
		// Test on top edge of board, just one walkway piece
		LinkedList<Integer> testList = board.getAdjList(board.getCellAt(0, 3));
		Assert.assertTrue(testList.contains(4));
		Assert.assertEquals(1, testList.size());
		
		// Test on left edge of board, three walkway pieces
		testList = board.getAdjList(board.getCellAt(6, 0));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(5, 0)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(6, 1)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(7, 0)));
		Assert.assertEquals(3, testList.size());

		// Test between two rooms, walkways right and left
		testList = board.getAdjList(board.getCellAt(14, 21));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(14, 20)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(14, 22)));
		Assert.assertEquals(2, testList.size());

		// Test surrounded by 4 walkways
		testList = board.getAdjList(board.getCellAt(15,7));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(15, 8)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(15, 6)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(14, 7)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(16, 7)));
		Assert.assertEquals(4, testList.size());
		
		// Test on bottom edge of board, next to 1 room piece
		testList = board.getAdjList(board.getCellAt(21, 15));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(21, 16)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(20, 15)));
		Assert.assertEquals(2, testList.size());
		
		// Test on ridge edge of board, next to 1 room piece
		testList = board.getAdjList(board.getCellAt(7, 22));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(7, 21)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(6, 22)));
		Assert.assertEquals(2, testList.size());

	}

	// Test adjacency at entrance to rooms
	@Test
	public void testAdjacencyDoorways()
	{
		// Test beside a door direction RIGHT
		LinkedList<Integer> testList = board.getAdjList(board.getCellAt(4, 3));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(4, 2)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(4, 4)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(5, 3)));
		Assert.assertEquals(3, testList.size());
		// Test beside a door direction DOWN
		testList = board.getAdjList(board.getCellAt(5, 8));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(4, 8)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(5, 9)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(5, 7)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(6, 8)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction LEFT
		testList = board.getAdjList(board.getCellAt(15, 17));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(15, 16)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(15, 18)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(14, 17)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(16, 17)));
		Assert.assertEquals(4, testList.size());
		// Test beside a door direction UP
		testList = board.getAdjList(board.getCellAt(14, 11));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(14, 10)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(14, 12)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(13, 11)));
		Assert.assertTrue(testList.contains(board.calcRoomIndex(15, 11)));
		Assert.assertEquals(4, testList.size());
	}

	
	// Tests of just walkways, 1 step, includes on edge of board
	// and beside room
	// Have already tested adjacency lists on all four edges, will
	// only test two edges here
	@Test
	public void testTargetsOneStep() {
		board.calcTargets(board.calcRoomIndex(20, 7), 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(19, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(20, 6))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(21, 7))));

		board.targets= new HashSet<BoardCell>();
		
		board.calcTargets(board.calcRoomIndex(14, 0), 1);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 1))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(13, 0))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(15, 0))));
		
		board.targets= new HashSet<BoardCell>();
		board.calcTargets(board.calcRoomIndex(14, 18), 1);
		targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 19))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 17))));			
		
	}
	// Tests of just walkways, 2 steps
	@Test
	public void testTargetsTwoSteps() {
		board.calcTargets(board.calcRoomIndex(21, 7), 2);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(19, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(20, 6))));
		
		board.targets= new HashSet<BoardCell>();
		
		board.calcTargets(board.calcRoomIndex(14, 0), 2);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(12, 0))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 2))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(15, 1))));			
	}
	// Tests of just walkways, 4 steps
	@Test
	public void testTargetsFourSteps() {
		board.calcTargets(board.calcRoomIndex(21, 7), 4);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(17, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(19, 7))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(18, 6))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(20, 6))));
		
		board.targets= new HashSet<BoardCell>();
		
		// Includes a path that doesn't have enough length
		board.calcTargets(board.calcRoomIndex(14, 0), 4);
		targets= board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 4))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(15, 3))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 2))));	
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(15, 1))));	
	}	
	// Tests of just walkways plus one door, 6 steps
	@Test
	public void testTargetsSixSteps() {
		board.calcTargets(board.calcRoomIndex(14, 22), 6);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(14, 16))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(15, 17))));
		
			
	}	
	
	// Test getting into a room
	@Test 
	public void testTargetsIntoRoom()
	{
		// One room is exactly 2 away
		board.calcTargets(board.calcRoomIndex(0, 12), 1);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(0, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(1, 12))));
		
		
	}
	
	// Test getting into room, doesn't require all steps
	@Test
	public void testTargetsIntoRoomShortcut() 
	{
		board.calcTargets(board.calcRoomIndex(0, 12), 4);
		Set<BoardCell> targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(0, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(4, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(2, 13))));
		
		board.targets = new HashSet<BoardCell>();
		
		board.calcTargets(board.calcRoomIndex(1, 12), 3);
		targets= board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(0, 11))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(4, 12))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(2, 13))));
	}

	// Test getting out of a room
	@Test
	public void testRoomExit()
	{
		board.calcTargets(board.calcRoomIndex(0, 11), 5);
		Set<BoardCell> targets= board.getTargets();
		// Ensure doesn't exit through the wall
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(2, 13))));
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(4, 12))));
		
		board.targets = new HashSet<BoardCell>();
		
		// Take two steps
		board.calcTargets(board.calcRoomIndex(0, 11), 2);
		targets= board.getTargets();
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.contains(board.getCellAt(board.calcRoomIndex(1, 12))));
	}

}