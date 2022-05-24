package navalGame.gameExecution;

/**
 * This class will allow to create 2 objects, player1 and player2 (in the {@link}NavalBattleshipGameGrid class),
 * to be used when playing the game.
 * 
 * 
 * @author Ioannis Karatsivoulis
 * 
 */
public class Player {
	// Initialise the player's score. It is made private to be able to be accessed
	// with getters and setters outside of this class.
	private int score = 0;

	private final String name; // name of the player. 

	// Constructor
	public Player(String name) {
		this.name = name;
	}

	/**
	 * @return the player's score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Set the player's score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	
	public int increaseScore() {
		++score;
		return score;
	}

	public int addScore(int scoreToAdd) {
		score += scoreToAdd;
		return score;
	}

	// print this in the command line
	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", score=" + score +
				'}';
	}
	// Gets the player's name
	public String getName() {
		return name;
	}
}
