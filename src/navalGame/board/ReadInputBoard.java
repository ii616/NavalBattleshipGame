package navalGame.board;


import java.io.BufferedReader;
import java.io.File;
//import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.StringTokenizer;
//import java.util.regex.Pattern;
import java.util.*;



/**
 * This class reads the text file inputed by the user ("SampleBoardFile.txt"), which 
 * includes the position of the ships. 
 * This class checks for out of bound coordinates. It is used in the CheckBoard class inside this same package.
 * @author Ioannis Karatsivoulis
 * 
 */
public class ReadInputBoard {

	// Define the board size's variable
	final int boardSize=8; 

	// Initialise X Y coordinate arrays for the ships
	// Initialise ArrayList for the X-coordinate of the Carrier ship
	ArrayList<Integer> carrierX = new ArrayList<Integer>();
	ArrayList<Integer> carrierY = new ArrayList<Integer>();
	// Ship 2: Battleship
	ArrayList<Integer> battleshipX = new ArrayList<Integer>();
	ArrayList<Integer> battleshipY = new ArrayList<Integer>();

	// Ship 3: Submarine
	ArrayList<Integer> submarineX = new ArrayList<Integer>();
	ArrayList<Integer> submarineY = new ArrayList<Integer>();

	// Ship 4: Destroyer 

	ArrayList<Integer> destroyerX = new ArrayList<Integer>();
	ArrayList<Integer> destroyerY = new ArrayList<Integer>();



	public void scanFile(String filename) throws IOException {
		// Specify the location of the file. 
		File file = new File("src/navalGame/Board/"+filename);

		String[] lines = new String[5]; // Create a string array with 1 line per element
		try {
			// Read the file--> 
			FileReader reader = new FileReader(file);
			// Use a buffered reader to read line by line. 
			BufferedReader buffReader;
			buffReader = new BufferedReader(reader);
			int x = 0;
			String s;
			while((s = buffReader.readLine()) != null){
				lines[x] = s;  // Adding 1 line at a time 
				x++;
			}
			// Close the Buffered Reader
			buffReader.close();
		}
		catch(IOException e){ //handle IO exception
			System.out.println("Error has occured "+e);
			System.out.println("--------------------------------------");
			System.out.println("Please enter a valid filename");
			System.out.println("--------------------------------------");

			e.printStackTrace();

		}

		// Initialise the array with the ships' names
		ArrayList<String> shipNameArray= new ArrayList<String>();

		StringTokenizer tokenizerLineOne = new StringTokenizer(lines[1]);	
		String shipOneName = tokenizerLineOne.nextToken(";");
		// Add this ship name to the array of shipNameArray
		shipNameArray.add(shipOneName);

		while(tokenizerLineOne.hasMoreTokens()) {
			// Trim whitespace
			String coordinate = tokenizerLineOne.nextToken(";").trim();
			//System.out.println(coordinate);
			// Split each coordinate into the left and right of the *
			String[] arrOfCoord = coordinate.split("\\*", 2);
			// Convert strings to integers
			int partialCoordX = Integer.parseInt(arrOfCoord[0]);
			int partialCoordY = Integer.parseInt(arrOfCoord[1]);
			// Check for out of bounds
			checkOutOfBounds(partialCoordX, partialCoordY, boardSize);
			// Add coordinates to the Carrier's coordinate array
			carrierX.add(partialCoordX);
			carrierY.add(partialCoordY);

		}


		// Deal with the second ship--> Battleship
		StringTokenizer tokenizerLineTwo = new StringTokenizer(lines[2]);	
		String shipTwoName = tokenizerLineTwo.nextToken(";");
		// Add this ship name to the array of shipNameArray
		shipNameArray.add(shipTwoName);
		while(tokenizerLineTwo.hasMoreTokens()) {
			// Trim whitespace
			String coordinate = tokenizerLineTwo.nextToken(";").trim();
			// Split each coordinate into the left and right of the *
			String[] arrOfCoord = coordinate.split("\\*", 2);
			int partialCoordX = Integer.parseInt(arrOfCoord[0]);
			int partialCoordY = Integer.parseInt(arrOfCoord[1]);
			// Check for out of bounds coordinates
			checkOutOfBounds(partialCoordX, partialCoordY, boardSize);
			// Add coordinates to the battleship's array
			battleshipX.add(partialCoordX);
			battleshipY.add(partialCoordY);
		}

		// Deal with ship 3: Submarine
		StringTokenizer tokenizerLineThree = new StringTokenizer(lines[3]);	
		String shipThreeName = tokenizerLineThree.nextToken(";");
		// Add this ship name to the array of shipNameArray
		shipNameArray.add(shipThreeName);
		while(tokenizerLineThree.hasMoreTokens()) {
			// Trim whitespace
			String coordinate = tokenizerLineThree.nextToken(";").trim();
			// Split each coordinate into the left and right of the *
			String[] arrOfCoord = coordinate.split("\\*", 2);
			// Convert string coordinate to an integer
			int partialCoordX = Integer.parseInt(arrOfCoord[0]);
			int partialCoordY = Integer.parseInt(arrOfCoord[1]);
			// Check for out of bounds
			checkOutOfBounds(partialCoordX, partialCoordY, boardSize);
			// Add coordinates to the submarine's array
			submarineX.add(partialCoordX);
			submarineY.add(partialCoordY);
		}
		// Deal with ship 4: Destroyer
		StringTokenizer tokenizerLineFour = new StringTokenizer(lines[4]);	
		String shipFourName = tokenizerLineFour.nextToken(";");
		// Add this ship name to the array of shipNameArray
		shipNameArray.add(shipFourName);

		while(tokenizerLineFour.hasMoreTokens()) {
			// Trim whitespace
			String coordinate = tokenizerLineFour.nextToken(";").trim();

			// Split each coordinate into the left and right of the *
			String[] arrOfCoord = coordinate.split("\\*", 2);
			// Convert string coordinate to an integer
			int partialCoordX = Integer.parseInt(arrOfCoord[0]);
			int partialCoordY = Integer.parseInt(arrOfCoord[1]);
			// Check for out of bounds
			checkOutOfBounds(partialCoordX, partialCoordY, boardSize);
			// Add coordinates to the submarine's array
			destroyerX.add(partialCoordX);
			destroyerY.add(partialCoordY);
		}

	}
	/*
	 * This method checks if the coordinates of each ship are larger
	 * than the board's size.
	 */
	public void checkOutOfBounds(int x, int y, int bound) {
		if (x>bound || y>bound) {
			System.out.println("-----OUT OF BOUNDS ERROR-----");
			System.out.println("One of your coordinates is greater than "+bound+". Please re-enter "
					+ "your coordinates inside the SampleBoardFile.txt, ensuring all of them are less "
					+ "than "+bound+", and re-start the game.");
			System.out.println("-----OUT OF BOUNDS ERROR-----");
		}
	}


	// Add Getters and Setters to get the ships' coordinates & board size
	// Get the board's size

	public int getBoardSize() {

		return this.boardSize;
	}

	//1 : Carrier ship
	//X- coordinate
	public ArrayList<Integer> getCarrierX() {
		return this.carrierX;
	}
	// Y- coordinate
	public ArrayList<Integer> getCarrierY() {
		//System.out.println(this.carrierY);
		return carrierY;
	}

	// 2: Battleship

	public ArrayList<Integer> getBattleshipX() {
		return this.battleshipX;
	}
	// Add Getters and Setters to get the ships' coordinates
	public ArrayList<Integer> getBattleshipY() {
		return this.battleshipY;
	}

	// 3: Submarine
	// X- coordinate array
	public ArrayList<Integer> getSubmarineX() {
		return this.submarineX;
	}
	// Y-coordinate array
	public ArrayList<Integer> getSubmarineY() {
		return this.submarineY;
	}

	// 4: Destroyer
	// X- coordinate array
	public ArrayList<Integer> getDestroyerX() {
		return this.destroyerX;
	}
	// Y-coordinate array for destroyer
	public ArrayList<Integer> getDestroyerY() {
		return this.destroyerY;
	}

}