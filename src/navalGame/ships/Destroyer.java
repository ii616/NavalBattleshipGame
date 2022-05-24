package navalGame.ships;

import java.util.ArrayList;

/**
 * Inherits all of the properties of @Ship class to create the Destroyer().
 * The colour, size and points per hit are set as final variables
 * Used in the {@link}TileClickedOn , {@link}GenerateShipsRandomly classes.
 * 
 * 
 * @author Ioannis Karatsivoulis
 * 
 */
public class Destroyer extends Ship{
	// Colour, size and points per hit are constants, thus they're set as 
	// final variables
	final String colour = "Black"; // colour of the ship that's been hit
	final int size = 2; // size of each ship
	final int pointsPerHit = 2; // Points per hit
	// Initialise the coordinate arrays
	public ArrayList<Integer> destroyerCoordX;
	public ArrayList<Integer> destroyerCoordY;
	// Constructor method
	public Destroyer(ArrayList<Integer> destroyerCoordX, ArrayList<Integer> destroyerCoordY) {
		// Below: We have defined the Carrier as a function of the 
		// variables inherited from the Ships class. 
		super(destroyerCoordX, destroyerCoordY);

	}

	// This constructor is used when generating ships randomly ({@link} GenerateShipsRandomly class)

	public Destroyer() {
		// TODO Auto-generated constructor stub
	}
	public ArrayList<Integer> getDestroyerCoordX() {
		return destroyerCoordX;
	}
	public void setDestroyerCoordX(ArrayList<Integer> destroyerCoordX) {
		this.destroyerCoordX = destroyerCoordX;
	}
	public ArrayList<Integer> getDestroyerCoordY() {
		return destroyerCoordY;
	}
	public void setDestroyerCoordY(ArrayList<Integer> destroyerCoordY) {
		this.destroyerCoordY = destroyerCoordY;
	}
	public String getColour() {
		return colour;
	}
	public int getSize() {
		return size;
	}
	public int getPointsPerHit() {
		return pointsPerHit;
	}


}
