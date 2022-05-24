package navalGame.ships;

import java.util.ArrayList;

/**
 * Inherits all of the properties of the {@link}Ship class to define the submarine. 
 * Used in the {@link}TileClickedOn , {@link}GenerateShipsRandomly classes.
 * @author Ioannis Karatsivoulis
 * 
 */
public class Submarine extends Ship{

	// Colour, size and points per hit are constants, thus they're set as 
	// final variables
	final String colour = "Yellow"; // colour of the ship that's been hit
	final int size = 3; // size of each ship
	final int pointsPerHit = 3; // Points per hit
	// Initialise the coordinate arrays
	public ArrayList<Integer> submarineCoordX;
	public ArrayList<Integer> submarineCoordY;

	public Submarine(ArrayList<Integer> submarineCoordX, 
			ArrayList<Integer> submarineCoordY) {
		// Below: We have defined the Carrier as a function of the 
		// variables inherited from the Ships class. 
		super(submarineCoordX, submarineCoordY);
	}

	// Used when generating ships randomly (GenerateShipsRandomly class)
	public Submarine() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the submarineCoordX
	 */
	public ArrayList<Integer> getSubmarineCoordX() {
		return submarineCoordX;
	}
	/**
	 * @param submarineCoordX the submarineCoordX to set
	 */
	public void setSubmarineCoordX(ArrayList<Integer> submarineCoordX) {
		this.submarineCoordX = submarineCoordX;
	}
	/**
	 * @return the submarineCoordY
	 */
	public ArrayList<Integer> getSubmarineCoordY() {
		return submarineCoordY;
	}
	/**
	 * @param submarineCoordY the submarineCoordY to set
	 */
	public void setSubmarineCoordY(ArrayList<Integer> submarineCoordY) {
		this.submarineCoordY = submarineCoordY;
	}
	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}
	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	/**
	 * @return the pointsPerHit
	 */
	public int getPointsPerHit() {
		return pointsPerHit;
	}

}
