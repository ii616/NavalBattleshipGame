package navalGame.board;

import javax.swing.*;

import navalGame.highScores.ReadFile;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Generates the game's starting menu, where the users can configure their choices
 * The user needs to run this class to display the starting menu.
 * 
 * It uses the readFile() method from the {@link}ReadFile class. 
 * 
 * 
 * @author Ioannis Karatsivoulis
 */
public class NavalBattleshipGameStartingMenu {
	// Define what is written inside the buttons
	public static final String PLACE_YOUR_SHIPS = "Place your ships";
	public static final String SCORING_SYSTEM = "Select scoring system";
	public static final String RULES = "Display the rules";
	public static final String PLAY_GAME = "PLAY GAME";
	public static final String HIGH_SCORES = "High scores";
	public static final String EXIT = "Exit";

	/**
	 * game's buttons
	 */
	private final Map<String, JButton> gameButtons;

	/**
	 * Initialise game's parameters
	 */
	private final Map<String, Object> initParams = new HashMap<>();


	private JButton showTheRulesButton;
	private JButton selectScoringSystemButton;
	private JButton placeTheShipsButton;
	private JButton playTheGameButton;
	private JButton highScoresButton;
	private JButton exitTheGameButton;

	// This is the only main method in the entire project. 
	public static void main(String[] args) throws FileNotFoundException {
		// Set look and feel so that tiles change colour on Mac
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			// Handle exception
		} catch (Exception e) {
			System.out.println("An exception has occured in the NavalBattleshipGameStartingMenuClass: "+e);
		}
		// Starts the game's starting menu from here. 
		new NavalBattleshipGameStartingMenu();
	}

	public NavalBattleshipGameStartingMenu() throws FileNotFoundException {
		JFrame frame = new JFrame(); // Create a frame 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // click no the "x" button to exit the frame. 
		frame.setTitle("Welcome to the Naval Battleship game!"); // set title

		// Use a grid bag layout manager
		frame.getContentPane().setLayout(new GridBagLayout());

		// Below is a hash map to hold the buttons
		gameButtons = createButtons(
				PLACE_YOUR_SHIPS,
				SCORING_SYSTEM,
				RULES,
				PLAY_GAME,
				HIGH_SCORES,
				EXIT);

		// Define the buttons displayed on the GUI
		placeTheShipsButton = gameButtons.get(PLACE_YOUR_SHIPS);
		selectScoringSystemButton = gameButtons.get(SCORING_SYSTEM);
		showTheRulesButton = gameButtons.get(RULES);
		playTheGameButton = gameButtons.get(PLAY_GAME);
		highScoresButton = gameButtons.get(HIGH_SCORES);
		exitTheGameButton = gameButtons.get(EXIT);



		// String which displays the rules of the game
		String rulesOfTheGame = "-Players take turns clicking (initially gray) tiles. \n-If a player hits a ship, they get 5/4/3/2 points for a Carrier/Battleship/Submarine/Destroyer"
				+ " respectively. "
				+ "\n-If a player has sank a ship, they get double the usual amount of points. The tile changes colour according to the ship that has been hit. "
				+ "\n (pink/orange/yellow/black for the Carrier/Battleship/Submarine/Destroyer respectively."
				+ "\n-If a player hits the sea, the tile turns blue and they get 0 points."
				+ "\n-The game stops when all ships have been sank."
				+ "\n-The winner is the player with the most points once the game is terminated.";

		/* Each button can have its own listener */
		showTheRulesButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(showTheRulesButton, rulesOfTheGame);
		});

		// Use the ReadFile class of the navalGame.highScores package.
		ReadFile fileReader = new ReadFile();

		// Read the high scores so that they are later displayed when clicking the high scores JButton
		String highScoresText= fileReader.readFile("src/navalGame/highScores/CombinedHighScores.txt");




		highScoresButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(highScoresButton, highScoresText);


		});
		// Listener for the scoring system
		selectScoringSystemButton.addActionListener(e -> {
			String scoringSystem = JOptionPane.showInputDialog("Please select a scoring system for the game");
			if (Objects.nonNull(scoringSystem)){
				initParams.put(SCORING_SYSTEM, scoringSystem);

			}
		});

		// Press this button to exit the game.
		exitTheGameButton.addActionListener(e -> System.exit(0));

		// this button action listener redirects to a new frame to play.
		//using the selected
		playTheGameButton.addActionListener(e -> {
			NavalBattleshipGameGrid gameGrid = new NavalBattleshipGameGrid(this.initParams); //create a new instance for a game board
			// Make our frame visible
			gameGrid.setVisible(true); 
		});

		// Select whether you want to input a text file or chose a text file randomly
		placeTheShipsButton.addActionListener(e -> {
			String placeShips = JOptionPane.showInputDialog("- Enter 1 to place the ships according to the user-inputted file SampleBoardFile.txt."
					+ "\n- Enter 2 to place the ships randomly on the board.");
			// If the user inputs something that is not null, store it 
			// inside the hash map. 
			if (Objects.nonNull(placeShips)){
				initParams.put(PLACE_YOUR_SHIPS, placeShips);
			}
		});


		// Add action listener to this button
		GridBagConstraints menuConstraints = new GridBagConstraints();
		// Specify the constraints of the starting menu.
		menuConstraints.fill = GridBagConstraints.BOTH;

		menuConstraints.weightx = 1;
		menuConstraints.weighty = 1;


		// First button--> Chooses ship placement--> (x,y) = (0,0)
		menuConstraints.gridx = 0;
		menuConstraints.gridy = 0;
		frame.add(gameButtons.get(PLACE_YOUR_SHIPS), menuConstraints);

		// Second button--> Choose scoring system--> (x,y) = (0,1)
		menuConstraints.gridy = 1;
		frame.add(gameButtons.get(SCORING_SYSTEM), menuConstraints);

		// Third button --> Display the rules  -->  (x,y) = (0,2)
		menuConstraints.gridy = 2;
		frame.add(showTheRulesButton, menuConstraints);

		// Fourth button --> Playing the game => (x,Y) = (0,3)
		menuConstraints.gridy = 3;
		frame.add(gameButtons.get(PLAY_GAME), menuConstraints);

		// Fifth button --> Shows high scores of previous games => (x,y) = (0,4)
		menuConstraints.gridy = 4;
		frame.add(gameButtons.get(HIGH_SCORES), menuConstraints);

		// Sixth button -- >Exits the game
		menuConstraints.gridy = 5;
		frame.add(gameButtons.get(EXIT), menuConstraints);

		// Ensure everything is displayed neatly
		frame.pack();
		// Ensure frame is visible to the users
		frame.setVisible(true);

	}


	/**
	 * initialise the buttons
	 * @param strings the strings
	 * @return a map with the string label as key and the button as a value
	 */
	private static Map<String, JButton> createButtons(String... strings) {
		Map<String, JButton> gameButtons = new HashMap<>();
		for (String label : strings)
			gameButtons.put(label, new JButton(label));
		return gameButtons;

	}
}

