package navalGame.ships;

import java.util.ArrayList;

/**
 * Class Battleship: Inherits all of the properties of the {@link}Ship class. 
 * The colour, size and points per hit are set as final variables
 * Used in the {@link}TileClickedOn , {@link}GenerateShipsRandomly classes.
 * 
 * @author Ioannis Karatsivoulis
 * 
 */
public class Battleship extends Ship{
	// Colour, size and points per hit are constants, thus they're set as 
	// final variables
	final String colour = "Orange"; // colour of the ship that's been hit
	final int size = 4; // size of each ship
	final int pointsPerHit = 4; // Points per hit
	// Initialise the coordinate arrays
	public ArrayList<Integer> battleshipCoordX;
	public ArrayList<Integer> battleshipCoordY;

	public Battleship(
			ArrayList<Integer> battleshipCoordX, ArrayList<Integer> battleshipCoordY) {
		// Below: We have defined the Carrier as a function of the 
		// variables inherited from the Ships class. 
		super(battleshipCoordX, battleshipCoordY);
	}
	// Used when generating ships randomly
	public Battleship() {
		// TODO Auto-generated constructor stub
	}


	public ArrayList<Integer> getBattleshipCoordX() {
		return battleshipCoordX;
	}
	public void setBattleshipCoordX(ArrayList<Integer> battleshipCoordX) {
		this.battleshipCoordX = battleshipCoordX;
	}
	public ArrayList<Integer> getBattleshipCoordY() {
		return battleshipCoordY;
	}
	public void setBattleshipCoordY(ArrayList<Integer> battleshipCoordY) {
		this.battleshipCoordY = battleshipCoordY;
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
