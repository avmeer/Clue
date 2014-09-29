package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

public class Board {
	BoardCell[][] board;
	Map<Character,String> rooms;
	int numRows;
	int numColumns;
	String layout;

	public Board(){
		this.layout = "";
	}

	public Board(String layout,Map<Character,String> rooms){
		this.layout = layout;
		this.rooms = rooms;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public BoardCell getCellAt(int row, int col) {
		return board[row][col];
	}
	public RoomCell getRoomCellAt(int i, int j) {
		return (RoomCell) board[i][j];
	}
	public void loadBoardConfig() throws BadConfigFormatException {
		try{
			FileReader reader = new FileReader(layout);
			Scanner in = new Scanner(reader);
			int currentRow=0;
			int currentCol=0;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String parts[] = line.split(",");
				for(String cell:parts){
					if(rooms.containsKey(cell.charAt(0))){
						if(rooms.get(cell.charAt(0))== "Walkway"){
							board[currentRow][currentCol]= new WalkwayCell(currentRow,currentCol);
						}
						else if(cell.length()==1){
							board[currentRow][currentCol]= new RoomCell(currentRow,currentCol,cell.charAt(0));
						}
						else if(cell.length()==2){
							board[currentRow][currentCol]= new RoomCell(currentRow,currentCol,cell.charAt(0),cell.charAt(1));
						}
						else{
							throw new BadConfigFormatException("Invalid room config");
						}
						currentRow++;
					}
					else{
						throw new BadConfigFormatException("Room not included in legend");
					}
				}
				currentCol++;
				currentRow=0;
			}


		}catch(FileNotFoundException e){
			e.getLocalizedMessage();
		}

	}
}
