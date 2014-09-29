package game;

public class RoomCell extends BoardCell{
	char roomInitial;
	public enum DoorDirection {
	    UP,DOWN,LEFT,RIGHT,NONE
	}
	DoorDirection doorDirection;
	public RoomCell(){
		super(0,0);
		doorDirection = DoorDirection.NONE;
		roomInitial=' ';
	}
	public RoomCell(int row,int col,char initial){
		super(row,col);
		doorDirection = DoorDirection.NONE;
		roomInitial=initial;
	}
	
	public RoomCell(int row,int col,char initial,char direction) throws BadConfigFormatException{
		super(row,col);
		switch(direction){
		case 'U':
			doorDirection = DoorDirection.UP;
			break;
		case 'D':
			doorDirection = DoorDirection.DOWN;
			break;
		case 'L':
			doorDirection = DoorDirection.LEFT;
			break;
		case 'R':
			doorDirection = DoorDirection.RIGHT;
			break;
		default:
			throw new BadConfigFormatException();
		}
	}
	
	@Override
	public boolean isRoom(){
		return true;
	}
	public char getInitial() {
		return roomInitial;
	}
}
