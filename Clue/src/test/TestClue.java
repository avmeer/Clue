package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import junit.framework.Assert;
import game.BadConfigFormatException;
import game.Board;
import game.BoardCell;
import game.ClueGame;

import org.junit.Before;
import org.junit.Test;

public class TestClue {
	private static Board board;
	public static final int NUM_ROOMS = 9;
	public static final int NUM_ROWS = 21;
	public static final int NUM_COLUMNS = 22;
	
	@Before
	public void setup(){
		ClueGame game = new ClueGame("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		game.loadConfigFiles();
		board = game.getBoard();
	}

	@Test
	public void testRooms() {
		Map<Character, String> rooms = board.getRooms();
		// Ensure we read the correct number of rooms
		assertEquals(NUM_ROOMS, rooms.size());
		// Test retrieving a few from the hash, including the first
		// and last in the file and a few others
		assertEquals("Classroom", rooms.get('C'));
		assertEquals("Family Room", rooms.get('F'));
		assertEquals("Kitchen", rooms.get('K'));
		assertEquals("Dining room", rooms.get('D'));
		assertEquals("Closet", rooms.get('X'));
		assertEquals("Study", rooms.get('S'));
		assertEquals("Office", rooms.get('O'));
		assertEquals("Library", rooms.get('L'));
		assertEquals("GuestRoom", rooms.get('G'));
	}
	
	@Test
	public void testBoardDimensions() {
		// Ensure we have the proper number of rows and columns
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());		
	}
	@Test
	public void testNumberOfDoorways() 
	{
		int numDoors = 0;
		int totalCells = board.getNumColumns() * board.getNumRows();
		int actualCells=NUM_ROWS*NUM_COLUMNS;
		Assert.assertEquals(actualCells, totalCells);
		for (int row=0; row<board.getNumRows(); row++)
			for (int col=0; col<board.getNumColumns(); col++) {
				BoardCell cell = board.getCellAt(row, col);
				if (cell.isDoorway())
					numDoors++;
			}
		Assert.assertEquals(13, numDoors);
	}
	@Test
	public void testRoomInitials() {
		assertEquals('G', board.getRoomCellAt(0, 0).getInitial());
		assertEquals('O', board.getRoomCellAt(0, 7).getInitial());
		assertEquals('L', board.getRoomCellAt(0, 14).getInitial());
		assertEquals('C', board.getRoomCellAt(0, 21).getInitial());
		assertEquals('D', board.getRoomCellAt(8, 18).getInitial());
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testBadColumns() throws BadConfigFormatException, FileNotFoundException {
		ClueGame game = new ClueGame("ClueLayoutBadColumns.csv", "ClueLegend.txt");
		game.loadRoomConfig();
		game.getBoard().loadBoardConfig();
	}

}
