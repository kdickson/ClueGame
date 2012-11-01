package main.board;

public class RoomCell extends BoardCell{
	
	
    
	public RoomCell(String cellChar, int row, int col) {
		super(cellChar, row, col);
	}

	@Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

	public enum DoorDirection{
		UP, DOWN, LEFT, RIGHT, NONE
	}
	
	private DoorDirection doorDirection;
	char initial;
	
    @Override
	public boolean isRoom(){
		return true;
	}

	public char getInitial() {
		return this.initial;
	}
	
	public DoorDirection getDoorDir() {
		return this.doorDirection;
	}
	
	public void setDoorDir(DoorDirection drDir) {
		this.doorDirection = drDir;
	}
}
