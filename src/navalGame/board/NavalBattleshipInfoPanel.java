package navalGame.board;


import navalGame.gameExecution.Player;

import javax.swing.*;
import java.awt.*;

/**
 * This class creates the side panel on the left hand side of the game's grid. It is used
 * in the 
 * @author Ioannis Karatsivoulis
 *
 */
@SuppressWarnings("serial")
public class NavalBattleshipInfoPanel extends JPanel {

	private final Label score1; // player 1 score
	private final Label score2; // player 2 score
	private final Label counter; // count how many times the players have played

	public NavalBattleshipInfoPanel(int turnCounter, Player player1, Player player2) {
		this.setLayout(new GridLayout(8, 1)); // board size is 8.
		score1 = new Label();
		score2 = new Label();
		counter = new Label();

		this.add(score1); // add score
		this.add(score2); // add score
		this.add(counter); // increase count
		refresh(turnCounter, player1, player2); // display the score of each player and the turns taken so far. 

	}

	/**
	 * render the labels
	 *
	 * @param turnCounter the counter int
	 * @param player1     {@link Player} 1
	 * @param player2     {@link Player} 2
	 */
	protected void refresh(int turnCounter, Player player1, Player player2) {

		// Display the score in the side panel. 
		score1.setText(player1.getName() + " score is:" + player1.getScore());
		score2.setText(player2.getName() + " score is:" + player2.getScore());
		counter.setText("total turns played:" + turnCounter); // display how many turns we have played
	}


	// get the counter
	public Label getCounter() {
		return counter;
	}
}
