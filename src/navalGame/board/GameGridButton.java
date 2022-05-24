package navalGame.board;

import javax.swing.*;
import java.awt.*;
/**
 * This class generates the buttons the user will click on. 
 * Bear in mind that if a carrier is placed in a certain tile, shipNumber=5 (carrier's points per hit)
 * If a battleship if placed there, shipNumber = 4 (carrier's points per hit)
 * If a submarine if placed there, shipNumber = 3 (submarine's points per hit)
 * If a destroyer is placed there, shipNumber = 2 (destroyer's points per hit). 
 * @author Ioannis Karatsivoulis
 * 
 */
@SuppressWarnings("serial")
public class GameGridButton extends JButton{

	// Below: Coordinates of the ship that has been hit
	private final int xCoord;

	private final int yCoord;

	// pressed: Boolean; indicates whether a button has been already pressed. If this is the case, the user is notified that the 
	// button has already been clicked.
	private boolean pressed = false; 

	private final NavalBattleshipGameGrid parent;

	private final int shipNumber; // indicates which ship we have hit. Each ship is identified according to its points per hit. 


	// Create a constructor for this class
	public GameGridButton(int shipNumber, int xCoord, int yCoord, NavalBattleshipGameGrid parentGridInstance) {
		this.xCoord = xCoord; // x coordinate
		this.yCoord = yCoord; // y coordinate
		this.shipNumber = shipNumber;

		this.setBackground(Color.GRAY); // unflipped tiles are gray
		// Add action listener to each tile.
		this.addActionListener(new TileClickedOn());

		parent = parentGridInstance; // parent grid
	}

	public boolean isPressed() {
		return pressed;
	}

	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	public int getxCoord() {
		return xCoord;
	}

	public int getyCoord() {
		return yCoord;
	}

	public int getShipNumber() {
		return shipNumber;
	}


	public NavalBattleshipGameGrid getParentGrid() {
		return parent;
	}
}