package navalGame.board;


import navalGame.gameExecution.GameLogicChecks;
import navalGame.gameExecution.Player;
import navalGame.highScores.HighScores;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Map;

/**
 * This class puts together the grid in which we will play the game. Each tile is initialised as a button (using the GameGridButton class),
 * and each button has is own action listener (through the TileClickedOn class).
 * 
 * This class uses the GenerateShipsRandomly, Player, CheckBoard, NavalBattleshipInfoPanel, GameGridButton classes. 
 * @author Ioannis Karatsivoulis
 * 
 * 
 */
@SuppressWarnings("serial")
public class NavalBattleshipGameGrid extends JFrame {

	String fileToInputCoordinatesFrom;

	private GameLogicChecks checks = new GameLogicChecks();

	private final NavalBattleshipInfoPanel infoPanel;

	/**
	 * Player one
	 */
	private final Player player1 = new Player("Player 1");

	private final Player player2 = new Player("Player 2");
	/**
	 * A counter to hold the total number of turns (button clicks).
	 * useful to find who is the player playing (assuming that the players respect the rules and take turns)
	 */
	private int turnCounter = 0;


	//This panel holds the butons
	private final JPanel gamePanel;

	/**
	 * This is an array that holds the initialised ship positions
	 */
	private int[][] shipPositionsGrid;

	/**
	 * Below is the constructor that initialises the grid
	 *
	 * @param initParameters initial parameters from the menu
	 */
	public NavalBattleshipGameGrid(Map<String, Object> initParameters) {



		// Set default close operation. Click "x" to close the game.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Set the frame's title.
		this.setTitle("Welcome to the Naval Battleship game!");
		// Create a panel for info


		// create a panel for the game
		gamePanel = new JPanel();
		// Set preferred dimensions
		this.setPreferredSize(new Dimension(1200, 1000));

		// Set the boardSize initially to 8--> Only then may we extend it for higher board sizes
		int boardSize = 8;

		// This object will check if the board is fine
		CheckBoard checkBoard = new CheckBoard();


		// Add try block in case the user does not input anything on the "Place Your Ships" button

		try {
			// Obtain the "Place your ships" button's input
			Object shipsPlacement = initParameters.get("Place your ships"); // get from the hashmap
			String shipsPlacementString = shipsPlacement.toString();
			int shipsPlacementInt = Integer.parseInt(shipsPlacementString); // convert "Place your ships" button's input to an integer


			if(shipsPlacementInt == 1) {
				String nameOfFile = "SampleBoardFile.txt";
				setFileToInputCoordinatesFrom(nameOfFile);
				String filename = getFileToInputCoordinatesFrom();


				// Check for overlapping ships
				checkBoard.checkOverlappingShips(boardSize, filename);
				this.shipPositionsGrid = checkBoard.getBoardPlacement();
				System.out.println("The ships have been placed according to the file SampleBoardFile.txt.");
			} else {
				System.out.println("The ships have been placed randomly on the board.");
				// Generate ships randomly
				GenerateShipsRandomly randomShips = new GenerateShipsRandomly(); // create an object
				randomShips.generateRandomShips();
				int[][] shipsArray = randomShips.getShipsArray(); // gets the array with the random ship positions. 
				this.shipPositionsGrid = shipsArray; // set the ship positioning array

			}
			// Catch NullPointerException in case the user does not input anything on the "place your ships" button
		} catch (NullPointerException npe) {
			System.out.println("You have not entered an integer in the Place You Ships button, thus the ships have "
					+ "been placed randomly on the board.");
			// Generate ships randomly
			GenerateShipsRandomly randomShips = new GenerateShipsRandomly();
			randomShips.generateRandomShips();
			int[][] shipsArray = randomShips.getShipsArray();
			this.shipPositionsGrid = shipsArray; // set the ship positioning array

			// Catch NumberFormatException in the "place your ships" button. 
		} catch (NumberFormatException nfe) {
			System.out.println("You have not entered an integer in the Place You Ships button, thus the ships have "
					+ "been placed randomly on the board.");
			// Generate ships randomly
			GenerateShipsRandomly randomShips = new GenerateShipsRandomly();
			randomShips.generateRandomShips();
			int[][] shipsArray = randomShips.getShipsArray();
			this.shipPositionsGrid = shipsArray; // set the ship positioning array
		} catch (Exception e) {
			// Catch overlapping ships exception, in the case "SampleBoardFile.txt" contains overlapping ships. 
			e.printStackTrace();
		}


		// 8x8 game grid to play the game in. 
		gamePanel.setLayout(new GridLayout(boardSize, boardSize));

		// create a panel for the game
		gamePanel.setLayout(new GridLayout(boardSize, boardSize));
		infoPanel = new NavalBattleshipInfoPanel(turnCounter, player1, player2);

		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				infoPanel, gamePanel);

		// shipNumber--> indicates the number which corresponds to each ship
		//5 for Carrier, 4 for battleship, 3 for submarine, 2 for Destroyer.
		int shipNumber;

		// Place the buttons on the board using a for loop.
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				// shipNumber: determines the position of the current button
				shipNumber = shipPositionsGrid[i][j];
				// Create JButton
				JButton guiButton = new GameGridButton(shipNumber, i, j, this);
				// Add the buttons to the GUI.
				gamePanel.add(guiButton);
			}
		}


		// Set the layout of the game's frame.
		this.setLayout(new GridLayout(1, 1));

		// Show the score in the left hand side of the frame 
		this.add(splitPane);


		// Ensure everything is displayed neatly
		this.pack();
		// Ensure frame is visible to the users
		this.setVisible(true);

	}

	/**
	 * every time a button is pressed this counter is used
	 *
	 * @return the int number representing the turn
	 */
	public int getTurnCounter() {
		return turnCounter;
	}

	/**
	 * increase the turn counter
	 *
	 * @return the int number counter increased by one
	 */
	public int increaseTurnCounter() {
		return ++turnCounter;
	}

	/**
	 * get the class logic to be used by other components
	 * @return the {@link GameLogicChecks} logic
	 */
	public GameLogicChecks getChecks() {
		return checks;
	}

	/**
	 * get the positions array
	 * @return
	 */
	public int[][] getShipPositionsGrid() {
		return shipPositionsGrid;
	}

	/**
	 * THis method uses the modulo operator to find the current player that is playing.
	 *
	 * @return Player1 if the number is even, player 2 if the number is odd.
	 *
	 */
	public Player getCurrentPlayer() {
		boolean isOddNumber = turnCounter % 2 == 0;
		return isOddNumber ? player2 : player1;
	}

	public void declareWinner() throws IOException {
		// Player 1 has won
		if (player1.getScore() > player2.getScore())  {
			JOptionPane.showMessageDialog(this, player1.getName() + " has won the game!");
			// Use the High Scores class to append the score of player 1 to the high scores.

			HighScores.saveToFile("Player 1",player1.getScore(),true);
			HighScores.readScoresFromFile("Player 1");
			HighScores.MergeFiles();


			// Display a message if we have got a tie. 
		} else if (player2.getScore() == player1.getScore()){
			// If there is a tie, this does NOT qualify to be considered
			// for a high score. 
			JOptionPane.showMessageDialog(this, "We have a tie!");
			// If there is not tie AND player 1 has NOT won, then player 2 has won.	
		} else {
			// Message pops up in a separate window. 
			JOptionPane.showMessageDialog(this, player2.getName() + " has won the game!");
			// Append to the file
			HighScores.saveToFile("Player 2",player2.getScore(),true);
			HighScores.readScoresFromFile("Player 2");
			// Merge the high scores of player 1 and player 2
			HighScores.MergeFiles();
		}

	}

	/**
	 * @return the fileToInputCoordinatesFrom
	 */
	private String getFileToInputCoordinatesFrom() {
		return fileToInputCoordinatesFrom;
	}

	/**
	 * @param fileToInputCoordinatesFrom the fileToInputCoordinatesFrom to set
	 */
	private void setFileToInputCoordinatesFrom(String fileToInputCoordinatesFrom) {
		this.fileToInputCoordinatesFrom = fileToInputCoordinatesFrom;
	}

	public NavalBattleshipInfoPanel getInfoPanel() {
		return infoPanel;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}



}
