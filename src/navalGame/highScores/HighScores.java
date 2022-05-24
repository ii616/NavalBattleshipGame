package navalGame.highScores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This class does 3 things:
 * 1. With the saveToFile method, it saves the score of the each player to a file (either "playerOneScoreStorage.txt" or "playerTwoScoreStorage.txt")
 * 2. With the readScoresFromFile method, it reads "playerOneScoreStorage.txt" or "playerTwoScoreStorage.txt" for player 1/ player 2 accordingly, 
 * and prints their high scores in a new file, "playerOneHighScores.txt" and "playerTwoHighScores.txt" accordingly. The highest scores are shown at the 
 * top. 
 * 3. Combines playerOneHighScores.txt" and "playerTwoHighScores.txt" into a 3rd file called
 * "CombinedHighScores.txt"
 * 
 * Used in the {@link}NavalBattleshipGameGrid class' {@link}declareWinner method
 * Also used to display High scores at the {@link}NavalBattleshipGameStartingMenu
 * 
 * This is a static class, as no object will be created with it. 
 * 
 * @author Ioannis Karatsivoulis
 * 
 */

public class HighScores {
	public static void saveToFile(String playerNumber, int score, boolean append) {
		// Only scores above 25 are considered as high scores. If the score is less than that, 
		// nothing will happen. This is arbitrary.
		if (score >25) {
			// Identify which player we are attributing the score to. 
			if (playerNumber == "Player 1") {
				try {
					// Create a new file for storing player 1's high scores; 
					File f1 = new File("src/navalGame/highScores/playerOneScoreStorage.txt");
					FileWriter fw1 = new FileWriter(f1, append);
					PrintWriter pw1 = new PrintWriter(fw1);

					// Print the player's score
					pw1.println(score);

					pw1.close();
					// Catch exception
				} catch(IOException io) {
					// Helps you know which method is throwing the exception.
					System.out.println("Error: save to file--> playerOneScoreStorage.txt"); 
				}
			} else if(playerNumber == "Player 2") {
				try {
					// Create a new file for player 2 
					File f2 = new File("src/navalGame/highScores/playerTwoScoreStorage.txt");
					FileWriter fw2 = new FileWriter(f2, append);
					PrintWriter pw2 = new PrintWriter(fw2);

					// Print the score
					pw2.println(score);
					// Close the print writer object
					pw2.close();
				} catch(IOException io) {
					// Helps you know which method is throwing the exception.
					System.out.println("Error: save to file--> playerTwoScoreStorage.txt"); 

				}
			}
		}



	}

	// New method to read the files "playerOneScoreStorage.txt" and "playerTwoScoreStorage.txt" , and store each 
	// Element into an array list. 
	public static void readScoresFromFile(String playerNumber) {

		if (playerNumber == "Player 1") {
			try {
				File file = new File("src/navalGame/highScores/playerOneScoreStorage.txt");

				// Create a file reader object
				FileReader fr = new FileReader(file);
				// Create a buffered reader object to read the file line by line.
				BufferedReader br = new BufferedReader(fr);
				// Each line is initialised as a string
				String line;
				// Initialise an integer ArrayList to store the high scores of previous players.
				ArrayList<Integer> playerOneScoreList = new ArrayList<>();

				while( (line = br.readLine()) != null) {
					int eachScorePlayerOne = Integer.parseInt(line);
					// Append the score to ther ArrayList
					playerOneScoreList.add(eachScorePlayerOne);
				}

				Collections.sort(playerOneScoreList); // sort the scores of player 1


				// After having sorted the Array, Append to a file called playerOneHighScores, which stores the
				// high scores of player one. The highest high score is at the top. 

				// Create a file called playerOneHighScores.txt
				File playerOneHighScoresFile = new File("src/navalGame/highScores/playerOneHighScores.txt");
				// the playerOneHighScores.txt is created every time after we have appended a score 
				// to the playerOneScoreStorage.txt array.
				FileWriter fwPlayerOne = new FileWriter(playerOneHighScoresFile, false);
				PrintWriter pwPlayerOne = new PrintWriter(fwPlayerOne);

				// print the list of high scores
				for (int i=playerOneScoreList.size()-1; i>=0; i--) {
					pwPlayerOne.println(playerOneScoreList.get(i));
				}

				pwPlayerOne.close(); // close print writer (no resource leak)
				br.close(); // close buffered reader
			} catch (FileNotFoundException e) {
				// Catch exception
				System.out.println("File ''playerOneScoreStorage.txt'' not found (readScoresFromFile method )");
				e.printStackTrace();
				// Handle IO exception 
			} catch (IOException e) {
				// print IO Exception message to the user. 
				System.out.println("IO Exception at the readScoresFromFile, when reading the file ''playerTwoScoreStorage.txt''");
				e.printStackTrace();
			}
		} else if (playerNumber == "Player 2") {
			try {
				File file = new File("src/navalGame/highScores/playerTwoScoreStorage.txt");

				// Create a file reader object
				FileReader fr = new FileReader(file);
				// Create a buffered reader object to read the file line by line.
				BufferedReader br = new BufferedReader(fr);
				// Each line is initialised as a string
				String line;
				// Initialise an integer ArrayList to store the high scores of previous players.
				ArrayList<Integer> playerTwoScoreList = new ArrayList<>();

				while( (line = br.readLine()) != null) {
					int eachScorePlayerTwo = Integer.parseInt(line);
					playerTwoScoreList.add(eachScorePlayerTwo);
				}

				Collections.sort(playerTwoScoreList); // sort the high scores of player 2 

				// Create a file called playerTwoHighScores.txt
				File playerTwoHighScoresFile = new File("src/navalGame/highScores/playerTwoHighScores.txt");
				// the playerOneHighScores.txt is created every time after we have appended a score 
				// to the playerOneScoreStorage.txt array.
				FileWriter fwPlayerTwo = new FileWriter(playerTwoHighScoresFile, false);
				PrintWriter pwPlayerTwo = new PrintWriter(fwPlayerTwo);

				// Iterate and print the high scores in the new file. 
				for (int i=playerTwoScoreList.size()-1; i>=0; i--) {
					pwPlayerTwo.println(playerTwoScoreList.get(i));
				}

				// Close print writer
				pwPlayerTwo.close();

				// Close buffered reader. 
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File ''playerTwoScoreStorage.txt'' not found (readScoresFromFile method )");
				e.printStackTrace();
				// Handle IO exception 
			} catch (IOException e) {
				// print a message to the user
				System.out.println("IO Exception at the readScoresFromFile, when reading the file ''playerTwoScoreStorage.txt''");
				e.printStackTrace();
			}
		}





	}
	public static void MergeFiles() throws IOException { 
		// The file which has got the scores of both players is called "CombinedHighScores.txt "
		// PrintWriter object for CombinedHighScores.txt 
		PrintWriter pw = new PrintWriter("src/navalGame/highScores/CombinedHighScores.txt"); 

		// BufferedReader object for playerOneHighScores.txt and playerTwoHighScores.txt 
		BufferedReader br1 = new BufferedReader(new FileReader("src/navalGame/highScores/playerOneHighScores.txt")); 
		BufferedReader br2 = new BufferedReader(new FileReader("src/navalGame/highScores/playerTwoHighScores.txt")); 


		String line1 = br1.readLine(); 
		String line2 = br2.readLine(); 

		// This loops copies each line inside
		// playerOneHighScores.txt and playerTwoHighScores.txt  
		// to  CombinedHighScores.txt. This will be displayed when we click
		// on the High scores button at the starting menu
		pw.println("Pl1  Pl2"); // header of the file Pl1= Player 1 and Pl2=Player 2. 
		while (line1 != null || line2 !=null) { 
			if(line1 != null) 
			{ 
				pw.print(line1); 
				pw.print("  ");
				line1 = br1.readLine(); 
			} 

			if(line2 != null) 
			{ 
				pw.println(line2); 
				pw.print(" ");
				line2 = br2.readLine(); 
			}

		} 
		// Flush the stream- does not accept any parameters; does not return an element.
		pw.flush(); 

		// Close buffered readers
		br1.close(); 
		br2.close(); 
		// Close the print writer
		pw.close(); 


	}

}
