package main.board;

public class WalkWayCell extends BoardCell{

	

	public WalkWayCell(String cellChar, int row, int col) {
		super(cellChar, row, col);
	}

	@Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean isWalkway(){
        return true;
    }

}
