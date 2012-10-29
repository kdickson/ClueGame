package main.board;

public abstract class BoardCell {
	
    int row;
    int col;
    String cellChar;
    private boolean doorway = false;
    private boolean room = false;
    private boolean walkway = false;
    
    public BoardCell(String cellChar) {
    	this.cellChar = cellChar;
    }
	
    public void setWalkway(boolean w){
        this.walkway = w;
    }
    
	public void setDoorway(boolean d){
		this.doorway = d;
	}
    
    public void setRoom(boolean r){
        this.room = r;
    }
    
    public boolean isWalkway(){
        return walkway;
    }
    
	public boolean isDoorway(){
		return doorway;
	}
    
    public boolean isRoom(){
        return room;
    }
    
    @Override
    public String toString() {
    	return this.cellChar;
    }
    
    public abstract void draw();

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

}
