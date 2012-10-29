package test;

import junit.framework.Assert;
import main.board.Board;
import main.board.BoardCell;
import main.board.RoomCell;
import main.board.RoomCell.DoorDirection;

import org.junit.Before;
import org.junit.Test;

public class BoardTest  {
    public static Board board;
    public final int ROW = 22;
    private final int COL = 23;
    
    @Before
    public void beforeMethod() {
    	board = new Board();
    	try{
            board.loadConfigFiles("work1.csv");
            board.loadLegend("initials.csv");
        }catch(Exception e){
            Assert.fail(e.toString());
        } 
    }
    
    @Test
    public void testConfigLoading() {
         board = new Board();
        try{
            board.loadConfigFiles("work1.csv");
        }catch(Exception e){
            Assert.fail(e.toString());
        } 
    }
    
    @Test
    public void testBoardSizes() {
        Assert.assertEquals(ROW, board.cells.size()/board.getNumColumns());
        Assert.assertEquals(COL, board.cells.size()/board.getNumRows());
    }
    
    @Test
    public void testCalcIndex() {
        Assert.assertEquals(0, board.calcRoomIndex(0, 0));
        Assert.assertEquals(1, board.calcRoomIndex(0, 1));
        Assert.assertEquals(4*COL + 2, board.calcRoomIndex(4,2));
        Assert.assertEquals(COL, board.calcRoomIndex(0, board.getNumColumns()));
    }
    
    @Test
    public void testBoardCharMapping() { 
        Assert.assertEquals("CONSERVATORY", board.rooms.get('C'));
        Assert.assertEquals("KITCHEN", board.rooms.get('K'));
        Assert.assertEquals("LIBRARY", board.rooms.get('L'));
    }
    
    @Test
    public void testNumRoomMap() {
        Assert.assertEquals(11, board.rooms.keySet().size());
        Assert.assertEquals(11, board.rooms.values().size());
        Assert.assertEquals(11,board.rooms.size());
    }
    
    @Test
    public void testNumOfDoors() {
    	int numDoors = 0;
    	int numOfCells = board.getNumColumns()*board.getNumRows();
    	Assert.assertEquals(506, numOfCells);
    	
    	for (int c=0;c<numOfCells;c++){
    		BoardCell cell = board.getCellAt(c);
    		if(cell.isDoorway())
    			numDoors++;
    	}
    	
    	Assert.assertEquals(17, numDoors);
    }
    
    @Test
    public void testDoors() {
    	//not doorway
    	RoomCell r = board.getRoomCellAt(0, 0);
    	Assert.assertFalse(r.isDoorway());
    	//Right
    	r = board.getRoomCellAt(4, 3);
    	Assert.assertTrue(r.isDoorway());
    	Assert.assertEquals(RoomCell.DoorDirection.RIGHT, r.getDoorDir());
    	//UP
    	r = board.getRoomCellAt(14, 11);
    	Assert.assertTrue(r.isDoorway());
    	Assert.assertEquals(RoomCell.DoorDirection.UP, r.getDoorDir());
    	//Down
    	r = board.getRoomCellAt(4, 8);
    	Assert.assertTrue(r.isDoorway());
    	Assert.assertEquals(RoomCell.DoorDirection.DOWN, r.getDoorDir());
    	//Left
    	r = board.getRoomCellAt(2, 13);
    	Assert.assertTrue(r.isDoorway());
    	Assert.assertEquals(RoomCell.DoorDirection.LEFT, r.getDoorDir());
    }
    
    @Test
	public void testRoomInitials() {
    	Assert.assertEquals('C', board.getRoomCellAt(0, 0).getInitial());
    	Assert.assertEquals('R', board.getRoomCellAt(2, 9).getInitial());
    	Assert.assertEquals('B', board.getRoomCellAt(10, 2).getInitial());
		Assert.assertEquals('O', board.getRoomCellAt(21, 21).getInitial());
		Assert.assertEquals('K', board.getRoomCellAt(21, 0).getInitial());
	}
}
