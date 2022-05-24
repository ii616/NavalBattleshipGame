package navalGame.gameExecution;
/**
 * This class implements the {@link}GameChecksInterface 
 * 
 * It is used in the {@link}TileClickedOn and {@link}GenerateShipsRandomly  class.
 * @author Ioannis Karatsivoulis
 * 
 */
public class GameLogicChecks implements GameChecksInterface {

	@Override
	public boolean winCheck(int[][] boardToCheck) {
		// gameWon--> boolean which is true iff the game has been won by someone
		boolean gameWon = false;

		// Outer "for" loop: Use "outer" in order to be able to break out of the nested loops
		outer:
			for (int[] ints : boardToCheck) {
				for (int j = 0; j < boardToCheck.length; j++) {
					// Check if any of these numbers is present inside the board (the numbers 2/3/4/5 represent a destroyer/submarine/battleship/carrier
					//respectively)
					if (ints[j] == 2 || ints[j] == 3 || ints[j] == 4 || ints[j] == 5) {
						// If any of these numbers are present in the board, then the game has not
						// been won by anyone (as there are still ships on the board), so return false
						gameWon = false;
						// As soon as one of the conditions boardToCheck[i][j]==2,3,4,5 is met, break out of the nested loops
						// by exiting the outer loop.
						break outer;
					} else {
						// This will only take place iff none of the conditions boardToCheck[i][j]==2,3,4,5 have 
						// been met, which means all of the ships have been sunk.  
						gameWon = true;

					}

				}

			}
		return gameWon; // return boolean 

	}

	/*
	 * This method checks whether or not the ship that has just been hit 
	 * has been sunk, in order to return double the points to whichever player 
	 * hit it last.
	 * Input 1: pointsPerHit--> the value of the tile that has just been clicked
	 * Input 2: boardWithTheShips--> The 2D matrix where all four ships are placed
	 * Output: shipSank (boolean). True/false if the ship has/has not been sank respectively.
	 */
	@Override
	public boolean shipSankCheck(int pointsPerHit, int [][] boardWithTheShips) {
		// Initialise a boolean value, which describes whether a ship has been sunk or not. 
		boolean shipSank = false; // initialise as false

		// Check whether in the current state of the board, there are anymore similar ships 
		// left. 

		// Outer "for" loop: Use "outer" in order to be able to break out of the nested loops
		outer:
			for (int[] boardWithTheShip : boardWithTheShips) {
				for (int j = 0; j < boardWithTheShips.length; j++) {
					// Check if any of these numbers is present inside the board
					if (boardWithTheShip[j] == pointsPerHit) {
						shipSank = false;
						// Break out of the outer loop if there is an instance of that
						// particular ship in the game. 
						break outer;
					} else {
						// If there is no more such ship left in the board, then the ship has been sunk
						shipSank = true;

					}

				}

			}

		return shipSank; // return boolean
	}

	@Override
	// Generates a random number between min and max, excluding max.
	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
