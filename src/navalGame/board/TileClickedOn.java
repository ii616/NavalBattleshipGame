package navalGame.board;

import navalGame.gameExecution.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Action Listener to "listen" to click events in the game board.
 * There is one for each button in the Game grid
 * Connected with the GameGridButtons class.
 * IMPORTANT NOTE:
 * Remember that the ships are placed in a 2D matrix; where the carrier is placed, the corresponding tiles have a value of
 * 5 (equal to the ship's points per hit). For a battleship/submarine/destroyer, the tiles in which these ships are placed
 * have got values of 4/3/2 accordingly. 
 * 
 * It uses with the class {@link}GameGridButton. 
 * 
 * @author Ioannis Karatsivoulis
 * 
 */
public class TileClickedOn implements ActionListener {
	// Extract the coordinates the player has just clicked to the main code.

	// x coordinate
	public int xPlayer;

	// y coordinate
	public int yPlayer;

	// Create a constructor for this class
	public TileClickedOn() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameGridButton buttonClicked = (GameGridButton) e.getSource();
		boolean hit = false;
		if (!buttonClicked.isPressed()) {
			buttonClicked.setPressed(true);
			//this is the parent game grid that is a common instance for all buttons
			NavalBattleshipGameGrid gameGrid = buttonClicked.getParentGrid();



			gameGrid.increaseTurnCounter(); // increase the turns
			Player currentPlayer = gameGrid.getCurrentPlayer(); // identify who is the current player. 

			// Get the x and y coordinates of the button which has just been clicked, with getters
			int buttonCoordinateX = buttonClicked.getxCoord();
			int buttonCoordinateY = buttonClicked.getyCoord();

			// Tell the code what to do in case we have hit sea (symbolised with
			// the number 0.
			if (buttonClicked.getShipNumber() == 0) {
				// Tile turns blue to symbolise water
				buttonClicked.setBackground(Color.BLUE);
				buttonClicked.repaint();
				System.out.println("You have NOT shot any ship, just water!" );

				xPlayer = buttonCoordinateX;
				yPlayer = buttonCoordinateY;

				// If the coordinate we have clicked on has got an element equal 5
				// then we have hit a carrier ==> CHANGE COLOR TO PINK)
			} else if (buttonClicked.getShipNumber() == 5 && buttonClicked.getShipNumber() < 6) {
				// The tile turns pink
				buttonClicked.setBackground(Color.PINK);
				buttonClicked.repaint();
				System.out.println("You have shot the Carrier" );
				xPlayer = buttonCoordinateX;
				yPlayer = buttonCoordinateY;
				// set to true if a ship has been hit. 
				hit = true;

				// Set the coordinate that has been hit to 0 so we can have the updated grid in memory
				gameGrid.getShipPositionsGrid()[xPlayer][yPlayer] = 0;

				// If the coordinate we have clicked on has got number 4, then we have hit a battleship==> The tile
				// turns Orange
			} else if (buttonClicked.getShipNumber() == 4 && buttonClicked.getShipNumber() < 6) {
				// The tile turns orange
				buttonClicked.setBackground(Color.ORANGE);
				buttonClicked.repaint();
				System.out.println("You have shot the Battleship" );

				xPlayer = buttonCoordinateX;
				yPlayer = buttonCoordinateY;
				// set to true if a ship has been hit. 
				hit = true;

				// Set the coordinate that has been hit to 0 so we can have the updated grid in memory
				gameGrid.getShipPositionsGrid()[xPlayer][yPlayer] = 0;

				// If the coordinate clicked upon corresponds to 3, then we have hit a submarine ==> the tile turns yellow
			}   else if (buttonClicked.getShipNumber() == 3 && buttonClicked.getShipNumber() < 6) {
				// The tile turns yellow
				buttonClicked.setBackground(Color.YELLOW);
				buttonClicked.repaint();
				System.out.println("You have shot the Submarine" );

				xPlayer = buttonCoordinateX;
				yPlayer = buttonCoordinateY;
				// set to true if a ship has been hit. 
				hit = true;

				// Set the coordinate that has been hit to 0 so we can have the updated grid in memory
				gameGrid.getShipPositionsGrid()[xPlayer][yPlayer] = 0;


				// if the coordinate we have clicked on corresponds to 2, then we have hit a destroyer==>
				// set the corresponding tile's colour to black
			}  else if (buttonClicked.getShipNumber() == 2 && buttonClicked.getShipNumber() < 6) {
				// The tile turns black
				buttonClicked.setBackground(Color.BLACK);
				buttonClicked.repaint();
				System.out.println("You have shot the Destroyer" );

				xPlayer = buttonCoordinateX;
				yPlayer = buttonCoordinateY;
				// set to true if a ship has been hit. 
				hit = true;

				// Set the coordinate that has been hit to 0 so we can have the updated grid in memory
				gameGrid.getShipPositionsGrid()[xPlayer][yPlayer] = 0;
			}


			if (hit) {
				// Check if the ship has been sank
				boolean checkShipSank = gameGrid.getChecks().shipSankCheck(buttonClicked.getShipNumber(),
						gameGrid.getShipPositionsGrid());

				if (checkShipSank) {
					// Case 2: The ship has been sunk => Double the score the player would have
					// normally received if the ship had not been sunk
					int doubleScore = 2 * buttonClicked.getShipNumber();
					currentPlayer.addScore(doubleScore);
					System.out.println("Player " + currentPlayer.getName() + " has received a double score of " + doubleScore);
				} else {
					// Add the score to the current player, depending on the ship they have hit.
					currentPlayer.addScore(buttonClicked.getShipNumber());
				}
			}
			// Refresh and display in the info panel
			gameGrid.getInfoPanel().refresh(
					gameGrid.getTurnCounter(),
					gameGrid.getPlayer1(),
					gameGrid.getPlayer2()
					);

			// Print the current player. 
			System.out.println(currentPlayer);

			// See if all ships have been sank
			boolean gameWonCheck = gameGrid.getChecks().winCheck(gameGrid.getShipPositionsGrid());
			if (gameWonCheck) {
				// use the declareWinner method if the game should not be stopped (tie, win, loss)
				try {
					gameGrid.declareWinner();
				} catch (IOException e1) {
					// Handle IO Exception
					e1.printStackTrace();
				}
			}


		} else {
			// If the user has already clicked a tile, let them know with a pop-up message. They will retain their turn 
			// but will need to click on another tile.
			JOptionPane.showMessageDialog(buttonClicked.getParentGrid(), "This choice was already used");
		}


	}

}
