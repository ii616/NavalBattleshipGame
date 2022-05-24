package navalGame.ships;

import java.util.ArrayList;

/**
 * Ships: Abstract class (no objects oh type "Ship" can be
 * instantiated), to create different ship types:
 * {@link}Carrier
 * {@link}Battleship
 * {@link}Submarine
 * {@link}Destroyer
 * 
 * @author Ioannis Karatsivoulis
 * 
 * 
 */
public abstract class Ship {

	// Make variables private (can only be accessed from 
	// within the Ships class. 

	// Define Array for the X and Y coordinates of the ship. 
	private ArrayList<Integer> xCoordinates; 
	private ArrayList<Integer> yCoordinates; 

	// Constructor method
	public Ship(ArrayList<Integer> xCoordinates,
			ArrayList<Integer> yCoordinates) {

		this.xCoordinates = xCoordinates;
		this.yCoordinates = yCoordinates;
	}

	// This constructor is used when generating ships randomly (GenerateShipsRandomly class)
	public Ship() {

	}

	public ArrayList<Integer> getxCoordinates() {
		return xCoordinates;
	}
	public void setxCoordinates(ArrayList<Integer> xCoordinates) {
		this.xCoordinates = xCoordinates;
	}
	public ArrayList<Integer> getyCoordinates() {
		return yCoordinates;
	}
	public void setyCoordinates(ArrayList<Integer> yCoordinates) {
		this.yCoordinates = yCoordinates;
	}

}