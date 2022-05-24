package navalGame.ships;

import java.util.ArrayList;

/**
 * Class Carrier: Defines the Carrier ship and inherits the properties of {@link}Ship.
 * The colour, size and points per hit are set as final variables
 * 
 * Used in the {@link}TileClickedOn , {@link}GenerateShipsRandomly classes.
 * @author Ioannis Karatsivoulis
 * 
 */
public class Carrier extends Ship{

	// Colour, size and points per hit are constants, thus they're set as 
	// final variables
	final String colour = "Pink"; // colour of the ship that's been hit
	final int size = 5; // size of each ship
	final int pointsPerHit = 5; // Points per hit
	// Initialise the coordinate arrays
	public ArrayList<Integer> carrierCoordX;
	public ArrayList<Integer> carrierCoordY;


	public Carrier(ArrayList<Integer> carrierCoordX, ArrayList<Integer> carrierCoordY) {
		// Below: We have defined the Carrier as a function of the 
		// variables inherited from the Ships class. 
		super(carrierCoordX, carrierCoordY);
	}

	// This constructor is used when generating ships randomly (GenerateShipsRandomly class)
	public Carrier() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return the carrierCoordX
	 */
	public ArrayList<Integer> getCarrierCoordX() {
		return carrierCoordX;
	}


	/**
	 * @param carrierCoordX the carrierCoordX to set
	 */
	public void setCarrierCoordX(ArrayList<Integer> carrierCoordX) {
		this.carrierCoordX = carrierCoordX;
	}


	/**
	 * @return the carrierCoordY
	 */
	public ArrayList<Integer> getCarrierCoordY() {
		return carrierCoordY;
	}


	/**
	 * @param carrierCoordY the carrierCoordY to set
	 */
	public void setCarrierCoordY(ArrayList<Integer> carrierCoordY) {
		this.carrierCoordY = carrierCoordY;
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
