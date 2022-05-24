package navalGame.highScores;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to read the contents of the "CombinedHighScores.txt" file
 * @author Ioannis Karatsivoulis
 * 
 */
public class ReadFile {



	public String readFile(String path) throws FileNotFoundException {

		File file = new File(path);

		try (Scanner scan = new Scanner(file)) {
			String fileContent ="";
			while(scan.hasNextLine()) {
				fileContent = fileContent.concat(scan.nextLine()+ "\n");
			}
			return fileContent;
		}

	}

}