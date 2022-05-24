package navalGame.board;

import navalGame.gameExecution.GameLogicChecks;
import navalGame.ships.Battleship;
import navalGame.ships.Carrier;
import navalGame.ships.Destroyer;
import navalGame.ships.Submarine;

/**
 * This class's purpose is to generate the placement of the ships completely randomly.
 * It does so by assigning a random number between 1 and 2 for each ship, which determines whether it will be 
 * placed vertically/horizontally. 
 * It uses the getRandomNumber of the {@link}GameLogicChecks class. Each ship is defined as an object, through the respective classes
 * in the navalGame.ships package.
 * 
 * 
 * @author Ioannis Karatsivoulis
 *  
 */
public class GenerateShipsRandomly {

	private int[][] shipsArray; // 2D array in which the ships will be placed. 

	// Method to generate the ships completely randomly

	public void generateRandomShips(){
		final int boardSize = 8; // BoardSize is constant and is set to 8; 


		boolean freeTileCheck; // checks if the tiles that will be occupied by the ship are free--> used with the freeTile method. 
		shipsArray = new int[boardSize][boardSize];
		// Create localCheck object to generate a random number.
		GameLogicChecks localChecks = new GameLogicChecks();

		//--------------------------------------
		//1. Generate the Carrier
		//--------------------------------------
		// Create a Carrier ship object. 
		Carrier carrierShip = new Carrier();
		// Get a random number; either 1 or 2 for Vertical/Horizontally accordingly. 
		int horizontalOrVertical = localChecks.getRandomNumber(1, 3);


		// Generate a random number in which the carrier will start 
		int xStartingPositionCarrier = localChecks.getRandomNumber(0, boardSize-carrierShip.getSize()); // X-starting coordinate
		int yStartingPositionCarrier = localChecks.getRandomNumber(0, boardSize-carrierShip.getSize()); // Y-starting coordinate

		// Carrier is placed vertically 
		if (horizontalOrVertical ==1) {
			// No need to check if the carrier overlaps, because it is placed first 
			// Place the ship
			for (int j=xStartingPositionCarrier; j<xStartingPositionCarrier+carrierShip.getSize(); j++) {
				for (int i=0; i<carrierShip.getSize();i++) {
					// When a carrier is placed, shipsArray is set to 5 (the ship's points per hit). 
					shipsArray[i][yStartingPositionCarrier] = carrierShip.getPointsPerHit(); // Carrier's points per hit are 5
				}
			}
		}

		// Carrier is placed horizontally
		if (horizontalOrVertical ==2) {
			// No need to check is the Carrier overlaps, as it is placed first. 
			for (int i=0; i<carrierShip.getSize();i++) {
				// Y-coordinate changes. 
				for (int j=yStartingPositionCarrier; j<yStartingPositionCarrier+carrierShip.getSize(); j++) {
					// Place the ship
					shipsArray[xStartingPositionCarrier][j] = carrierShip.getPointsPerHit();
				}
			}	
		}


		//--------------------------------------
		//2. Generate the BATTLESHIP
		//--------------------------------------
		Battleship battleShip = new Battleship(); // Create a battleship object
		// Vertical/Horizontal if we get 1/2 accordingly. 
		horizontalOrVertical = localChecks.getRandomNumber(1, 3);


		// Generate a random number in which the battleship will start 
		int xStartingPositionBattleship = localChecks.getRandomNumber(0, boardSize-battleShip.getSize()); // X-starting coordinate
		int yStartingPositionBattleship = localChecks.getRandomNumber(0, boardSize-battleShip.getSize()); // Y-starting coordinate

		freeTileCheck = freeTile(xStartingPositionBattleship,yStartingPositionBattleship,
				battleShip.getSize(), shipsArray, horizontalOrVertical);

		// Ship is placed horizontally
		if (horizontalOrVertical ==1 ) {
			// Ensure that the ship will not go our of bounds and that it won't overlap
			while ( (yStartingPositionBattleship >= (boardSize - battleShip.getSize()) || 
					freeTileCheck == false) ) {
				// Get random number between 1 and 7
				yStartingPositionBattleship = localChecks.getRandomNumber(0, boardSize-battleShip.getSize());
				xStartingPositionBattleship = localChecks.getRandomNumber(0, boardSize-battleShip.getSize());
				freeTileCheck = freeTile(xStartingPositionBattleship,yStartingPositionBattleship,
						battleShip.getSize(), shipsArray, horizontalOrVertical);	
			}

			// X-coordinate changes. 
			for (int i=xStartingPositionBattleship; i<xStartingPositionBattleship+battleShip.getSize(); i++) {
				// That coordinate will have the battleship's points per hit (=4).
				shipsArray[i][yStartingPositionBattleship] = battleShip.getPointsPerHit();
			}	
		}


		// Initialise the check of whether the tiles the ship will take are free or not. 
		freeTileCheck = freeTile(xStartingPositionBattleship,yStartingPositionBattleship,
				battleShip.getSize(), shipsArray, horizontalOrVertical);

		// Ship is placed horizontally
		if (horizontalOrVertical ==2 ) {
			// Ensure that the ship will not go our of bounds and that it won't overlap
			while ( (xStartingPositionBattleship >= (boardSize - battleShip.getSize()) || 
					freeTileCheck == false) ) {
				// Get random number between 1 and 7
				yStartingPositionBattleship = localChecks.getRandomNumber(0, boardSize-battleShip.getSize());
				xStartingPositionBattleship = localChecks.getRandomNumber(0, boardSize-battleShip.getSize());
				freeTileCheck = freeTile(xStartingPositionBattleship,yStartingPositionBattleship,
						battleShip.getSize(), shipsArray, horizontalOrVertical);	
			}

			// X-coordinate changes. 
			for (int j=yStartingPositionBattleship; j<yStartingPositionBattleship+battleShip.getSize(); j++) {
				// Set corresponding coordinate to the ship's points per hit. 
				shipsArray[xStartingPositionBattleship][j] = battleShip.getPointsPerHit();
			}	
		}


		//--------------------------------------
		//3. Generate the Submarine 
		//--------------------------------------
		Submarine submarine = new Submarine(); // Create a submarine object using the Submarine() class.
		// Vertical/Horizontal if we get 1/2 accordingly. 
		horizontalOrVertical = localChecks.getRandomNumber(1, 3);

		// Generate a random number in which the submarine will start 
		int xStartingPositionSubmarine = localChecks.getRandomNumber(0, boardSize-submarine.getSize()); // X-starting coordinate
		int yStartingPositionSubmarine = localChecks.getRandomNumber(0, boardSize-submarine.getSize()); // Y-starting coordinate

		// Initialise the free tile check
		freeTileCheck = freeTile(xStartingPositionSubmarine,yStartingPositionSubmarine,
				submarine.getSize(), shipsArray, horizontalOrVertical);

		// Ship is placed vertically
		if (horizontalOrVertical ==1 ) {
			// Ensure that the ship will not go our of bounds and that it won't overlap
			while ( (yStartingPositionSubmarine >= (boardSize - submarine.getSize()) || 
					freeTileCheck == false) ) {
				// Get random number between 1 and 7
				yStartingPositionSubmarine = localChecks.getRandomNumber(0, boardSize-submarine.getSize());
				xStartingPositionSubmarine = localChecks.getRandomNumber(0, boardSize-submarine.getSize());
				freeTileCheck = freeTile(xStartingPositionSubmarine,yStartingPositionSubmarine,
						submarine.getSize(), shipsArray, horizontalOrVertical);	
			}

			// X-coordinate changes. 
			for (int i=xStartingPositionSubmarine; i<xStartingPositionSubmarine+submarine.getSize(); i++) {

				shipsArray[i][yStartingPositionSubmarine] = submarine.getPointsPerHit();
			}	
		}


		// Initialise the check of whether the tiles the ship will take are free or not. 
		freeTileCheck = freeTile(xStartingPositionSubmarine,yStartingPositionSubmarine,
				submarine.getSize(), shipsArray, horizontalOrVertical);

		// Submarine is placed horizontally
		if (horizontalOrVertical ==2 ) {
			// Ensure that the submarine will not go our of bounds and won�t overlap with another ship
			while ( (xStartingPositionSubmarine >= (boardSize - submarine.getSize()) || 
					freeTileCheck == false) ) {
				// Get random number between 1 and 7
				yStartingPositionSubmarine = localChecks.getRandomNumber(0, boardSize-submarine.getSize());
				xStartingPositionSubmarine = localChecks.getRandomNumber(0, boardSize-submarine.getSize());
				freeTileCheck = freeTile(xStartingPositionSubmarine,yStartingPositionSubmarine,
						submarine.getSize(), shipsArray, horizontalOrVertical);	
			}

			// Y-coordinate changes. 
			for (int j=yStartingPositionSubmarine; j<yStartingPositionSubmarine+submarine.getSize(); j++) {
				// Place the ship
				shipsArray[xStartingPositionSubmarine][j] = submarine.getPointsPerHit();

			}	
		}



		//--------------------------------------
		//4. Generate the Destroyer. 
		//--------------------------------------

		Destroyer destroyer = new Destroyer(); // create a destroyer object
		// Vertical/Horizontal if we get 1/2 accordingly. 
		horizontalOrVertical = localChecks.getRandomNumber(1, 3);


		// Generate a random coordinate in which the destroyer will start 
		int xStartingPositionDestroyer = localChecks.getRandomNumber(0, boardSize-destroyer.getSize()); // X-starting coordinate
		int yStartingPositionDestroyer = localChecks.getRandomNumber(0, boardSize-destroyer.getSize()); // Y-starting coordinate

		// Initialise the free tile check
		freeTileCheck = freeTile(xStartingPositionDestroyer,yStartingPositionDestroyer,
				destroyer.getSize(), shipsArray, horizontalOrVertical);

		// Ship is placed vertically
		if (horizontalOrVertical ==1 ) {
			// Ensure that the ship will not go our of bounds and that it won't overlap
			while ( (yStartingPositionDestroyer >= (boardSize - destroyer.getSize()) || 
					freeTileCheck == false) ) {
				// Get random number between 1 and 7
				yStartingPositionDestroyer = localChecks.getRandomNumber(0, boardSize-destroyer.getSize());
				xStartingPositionDestroyer = localChecks.getRandomNumber(0, boardSize-destroyer.getSize());
				freeTileCheck = freeTile(xStartingPositionDestroyer,yStartingPositionDestroyer,
						destroyer.getSize(), shipsArray, horizontalOrVertical);	
			}

			// X-coordinate changes. 
			for (int i=xStartingPositionDestroyer; i<xStartingPositionDestroyer+destroyer.getSize(); i++) {

				shipsArray[i][yStartingPositionDestroyer] = destroyer.getPointsPerHit(); // Set equal to the destroyer's points per hit. 

			}	
		}

		// Initialise the check of whether the tiles the ship will take are free or not. 
		freeTileCheck = freeTile(xStartingPositionDestroyer,yStartingPositionDestroyer,
				destroyer.getSize(), shipsArray, horizontalOrVertical);

		// Destroyer is placed horizontally
		if (horizontalOrVertical ==2 ) {
			// Ensure that the destroyer will not go our of bounds and won�t overlap with another ship
			while ( (xStartingPositionDestroyer >= (boardSize - destroyer.getSize()) || 
					freeTileCheck == false) ) {
				// Get random number between 1 and 7
				yStartingPositionDestroyer = localChecks.getRandomNumber(0, boardSize-destroyer.getSize());
				xStartingPositionDestroyer = localChecks.getRandomNumber(0, boardSize-destroyer.getSize());
				freeTileCheck = freeTile(xStartingPositionDestroyer,yStartingPositionDestroyer,
						destroyer.getSize(), shipsArray, horizontalOrVertical);	
			}

			// Y-coordinate changes. 
			for (int j=yStartingPositionDestroyer; j<yStartingPositionDestroyer+destroyer.getSize(); j++) {
				// Place the ship
				shipsArray[xStartingPositionDestroyer][j] = destroyer.getPointsPerHit();

			}	
		}
	}



	public boolean freeTile(int xStartPos,int yStartPos, int sizeOfShip, int[][] arrayToCheck, int horizOrVert) {
		/**
		 * // This method checks whether the starting tile, until the tile at the end of the ship,
		 * are free or not. If all of the tiles are free, the method returns the boolean noOverlap = true. If they are not free,
		 * you return noOverlap = false.
		 * 
		 * 
		 * Inputs:
		 * 1. xStartPos: The ship's starting x position
		 * 2. yStartPos: The ship's starting y position
		 * 3. sizeOfShip: eg. 5 for carrier, 4 for battleship, 3 and 2 for submarine and destroyer accordingly. 
		 * 4. arrayToCheck: The array which has got all of the ships in it, at the point we are checking.
		 * 5. horizOrVert 1 if the ship is vertical, 2 if the ship is horizontal.
		 * Outputs:
		 * 1. The boolean noOverlap--> true if there is no overlap, false otherwise. 
		 */

		// Initialise the boolean. 
		boolean noOverlap = false;
		// Case 1: the ship is horizontal
		if (horizOrVert == 2) { // Ship is horizontal
			outer:
				for (int i=0; i<=sizeOfShip; i++) {
					if (arrayToCheck[xStartPos][yStartPos+sizeOfShip-i] == 0) {

						noOverlap = true; // there is no overlap


					} else {
						noOverlap = false; // there is overlap
						break outer;
					}
				}

		// Case 2: Ship is vertical 
		} else { 
			outer: 
				for (int i=0; i<=sizeOfShip; i++) {
					if (arrayToCheck[xStartPos+sizeOfShip-i][yStartPos] == 0) {
						// There no overlap
						noOverlap = true;
					} else {
						// there is overlap
						noOverlap = false;
						break outer; 
					}
				}
		}


		return noOverlap; // return boolean
	}



	public int[][] getShipsArray() {
		return shipsArray;
	}



	public void setShipsArray(int[][] shipsArray) {
		this.shipsArray = shipsArray;
	}




}