package navalGame.gameExecution;
/**
 * This interface introduces the methods:
 * 1. winCheck()--> Checks whether a player has won
 * 2. shipSankCheck()--> Every time a player selects a value, 
 * it checks if the ship that's been hit has been sank,
 * in order to double the player's score. 
 * 3. getRandomNumber--> generate a random number between min and max
 * 
 * --------
 * This interface is meant to be used by the {@link}GameExecution class, and can only be used 
 * from classes within the same package.
 * 
 * @author Ioannis Karatsivoulis
 * 
 */
interface GameChecksInterface {

	public boolean winCheck(int[][] boardToCheck); // Checks if all ships have been sank

	// Checks if a certain ship has been sunk.
	public boolean shipSankCheck(int pointsPerHit, int [][] boardWithTheShips);

	// Generates a random number. 
	public int getRandomNumber(int min, int max);
	
}
