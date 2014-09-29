package game;


public abstract class BoardCell {
	int col;
	int row;
	public BoardCell(int row,int col){
		this.col=col;
		this.row=row;
	}
	
	public boolean isWalkway(){
		return false;
	}
	public boolean  isRoom(){
		return false;
	}
	public boolean isDoorway(){
		return false;
	}
	
}
