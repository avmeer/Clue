package game;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

public class ClueGame {
	Map<Character,String> rooms;
	Board board;
	String layout;
	String legend;

	public ClueGame(String layout, String legend){
		this.legend = legend;
		this.layout = layout;
	}

	public void loadConfigFiles() {
		//load legend
		try{
			FileReader reader = new FileReader(legend);
			Scanner in = new Scanner(reader);
			while (in.hasNextLine()) {
				String line = in.nextLine();
				String parts[] = line.split(", ");
				if(parts.length != 2){
					throw new BadConfigFormatException("Invalid number of items on line");
				}
				if(parts[0].length() != 1){
					throw new BadConfigFormatException("Invalid Initial");
				}
				char initial = parts[0].charAt(0);
				String room = parts[1];
				rooms.put(initial, room);
			}
			board  = new Board(this.layout,rooms);
		}catch(FileNotFoundException e){
			e.getLocalizedMessage();
		} catch (BadConfigFormatException e) {
			e.getLocalizedMessage();
		}
	}

	public Board getBoard() {
		return board;
	}

	public void loadRoomConfig() {
		// TODO Auto-generated method stub

	}
}
