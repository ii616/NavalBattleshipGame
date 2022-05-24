package navalGame.board;

import java.io.IOException;
import java.util.ArrayList;


/**
 * Class that checks whether there are errors within the text file input by the user. Checks:
 * 1. Overlapping ships--> the user first inputs the coordinates, and then these
 * are checked for overlapping ships. If ships overlap, the user is notified and prompted to re-start the game.
 * 2. Inconsistent board specifications (coordinates that are out of bounds), through the ReadInputBoard class' checkOutOfBounds method .
 * 
 * Note that ships are placed on the 2D array boardPlacement. When a carrier/battleship/submarine/destroyer is placed,
 * the corresponding tile's value is 5/4/3/2 respectively (equal to the ship's points per hit). If no ship is placed in a tile, the
 * value of that tile is set to 0. 
 * 
 * @author Ioannis Karatsivoulis
 * 
 */
public class CheckBoard {
	// private variables: can only be accessed from within the class.
	// boardSize is kept to 8--> Need to CHANGE LATER if we scale up the game board's size
	private int boardSize = 8;
	private int[][] boardPlacement = new int[boardSize][boardSize];


	// Method 1: Checks whether there are overlapping ships
	// Returns an integer is there is an overlap
	// Input 1: the Board size, so the array can be initialised accordingly
	// Input 2: The filename in which the coordinate of the ships are contained.
	// NOTE: The board is scanned using the ScanFile method from the ReadInputBoard class.

	public boolean checkOverlappingShips(int boardSize, String filename) throws Exception {

		//ReadInputBoard scanTheInputBoardFile = new ReadInputBoard();
		// Scan the Input file
		ReadInputBoard scanTheInputBoardFile = new ReadInputBoard();
		try {
			scanTheInputBoardFile.scanFile(filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("You have perhaps changed your filename and the"
					+ "CheckBoard class can't access it");
			e.printStackTrace();
		}

		// Get Carrier's X,Y coordinates
		ArrayList<Integer> carrierXCoordinates = scanTheInputBoardFile.getCarrierX();
		ArrayList<Integer> carrierYCoordinates = scanTheInputBoardFile.getCarrierY();
		// Get Battleship's X,Y coordinates
		ArrayList<Integer> battleshipXCoordinates = scanTheInputBoardFile.getBattleshipX();
		ArrayList<Integer> battleshipYCoordinates = scanTheInputBoardFile.getBattleshipY();

		// Get submarine's X,Y coordinates
		ArrayList<Integer> submarineXCoordinates = scanTheInputBoardFile.getSubmarineX();
		ArrayList<Integer> submarineYCoordinates = scanTheInputBoardFile.getSubmarineY();

		// Get destroyer's X,Y coordinates
		ArrayList<Integer> destroyerXCoordinates = scanTheInputBoardFile.getDestroyerX();
		ArrayList<Integer> destroyerYCoordinates = scanTheInputBoardFile.getDestroyerY();

		// Initialise a 2D array (called boardPlacement) as zeros
		// This will be used to check if there is overlap

		// Initialise the tempBoolean variable--> Stores whether or not we have found any overlaps so far
		// If tempOverlap is false, it means that there is no overlap and we can play the game.
		boolean tempOverlap = false;

		// Occupy the Carrier's coordinates on the board
		for (int i = 0; i < carrierXCoordinates.size(); i++) {
			for (int j = 0; j < carrierYCoordinates.size(); j++) {
				//Need to access the arrays element-wise=> i must be equal to j, otherwise
				// the code will loop over the same coordinates multiple times
				if (i == j) {
					// Initialise variables tempXPosCarrier and tempYPosCarrier, which store the position
					// of the carrier given the corresponding arrays:
					// carrierXCoordinates and carrierYCoordinates accordingly.
					// The same logic is repeated for the remaining ships
					int tempXPosCarrier = carrierXCoordinates.get(i) - 1;
					int tempYPosCarrier = carrierYCoordinates.get(j) - 1;

					// If board is taken, there is an overlap;
					if (boardPlacement[tempXPosCarrier][tempYPosCarrier] == 1) {
						tempOverlap = true; // there is an overlap
						// Notify the user
						throw new Exception("Your Carrier overlaps. Please re-place your ships and ensure this does not happen");

						// If the place is free (i.e. boardsize at that position is 0), then place the ship

					} else if (boardPlacement[tempXPosCarrier][tempYPosCarrier] == 0) {
						//setBoardPlacement(boardPlacement[tempXPos][tempYPos] = 1);
						// Set value to the points per hit of the Carrier
						boardPlacement[tempXPosCarrier][tempYPosCarrier] = 5;

					}
				}


			}
		}

		// Repeat for battleship
		for (int i = 0; i < battleshipXCoordinates.size(); i++) {
			for (int j = 0; j < battleshipYCoordinates.size(); j++) {
				// Access the coordinate arrays in an elementwise  manner- eg. (i=0,j=0) then (i=1,j=1),...
				if (i == j) {
					// Store the X and Y coordinates of the battleship in the
					// tempXPos and tempYPos (integer) variables accordingly
					int tempXPos = battleshipXCoordinates.get(i) - 1;
					int tempYPos = battleshipYCoordinates.get(j) - 1;

					// If the place is free (i.e. boardsize at that position is 0), then place the ship
					if (boardPlacement[tempXPos][tempYPos] == 0) {
						// Set value to the points per hit of the battleship
						boardPlacement[tempXPos][tempYPos] = 4;

						// If place is not free, then the board place is taken. Inform the user.
					} else {
						tempOverlap = true; // there is an overlap
						// Notify the user
						throw new Exception("Your Battleship overlaps. Please re-place your ships and ensure this does not happen");

						

					}
				}
			}
		}

		// Repeat for submarine
		for (int i = 0; i < submarineXCoordinates.size(); i++) {
			for (int j = 0; j < submarineYCoordinates.size(); j++) {

				if (i == j) {
					// Store the X and Y coordinates of the submarine in the tempXPos and tempYPos arrays
					// accordingly
					int tempXPos = submarineXCoordinates.get(i) - 1;
					int tempYPos = submarineYCoordinates.get(j) - 1;
					// If the place is free (i.e. boardsize at that position is 0), then place the ship
					if (boardPlacement[tempXPos][tempYPos] == 0) {
						// Set value to the points per hit of the submarine
						boardPlacement[tempXPos][tempYPos] = 3;

						// If place is not free, then that place is taken. Inform the user with a message on the command line. 
					} else {
						tempOverlap = true; // there is an overlap
						// Notify the user
						throw new Exception("Your Submarine overlaps. Please re-place your ships and ensure this does not happen");

						

					}
				}


			}
		}

		// Repeat for the destroyer ship
		for (int i = 0; i < destroyerXCoordinates.size(); i++) {
			for (int j = 0; j < destroyerYCoordinates.size(); j++) {
				// Access the coordinate arrays in an element-wise  manner- eg. (i=0,j=0) then (i=1,j=1),...
				if (i == j) {
					// Store the destroyer's coordinates in the temporary variables tempXPos and tempYPos
					int tempXPos = destroyerXCoordinates.get(i) - 1;
					int tempYPos = destroyerYCoordinates.get(j) - 1;
					// If place not free, then there's an overlap--> notify the user with a command line message.
					if (boardPlacement[tempXPos][tempYPos] != 0) {
						tempOverlap = true; // there is an overlap
						// Notify the user
						throw new Exception("Your Destroyer overlaps. Please re-place your ships and ensure this does not happen");
						// If the place is free (i.e. boardPlacement at that position is 0),
						//then place the ship
					} else {
						// Set value to the points per hit of the destroyer
						boardPlacement[tempXPos][tempYPos] = 2; // A destroyer is placed==> The tile's value is set to 2

					}
				}

			}
		}




		// Return false if there is no overlap
		if (!tempOverlap) {
			System.out.println("No overlaps");
			return false;
		}
		return true;


	}

	/**
	 * @return the boardSize
	 */
	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * @param boardSize the boardSize to set
	 */
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
	}

	/**
	 * @return the boardPlacement
	 */
	public int[][] getBoardPlacement() {
		return boardPlacement;
	}

	/**
	 * @param boardPlacement the boardPlacement to set
	 */
	public void setBoardPlacement(int[][] boardPlacement) {
		this.boardPlacement = boardPlacement;
	}

	/**
	 * @return the boardPlacement
	 */


}