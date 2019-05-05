// this class breaks the game down into individual frames and takes in each roll and its result

import java.util.Scanner; // imports Scanner utility
import java.util.ArrayList; // imports ArrayList utility

public class Frame { // names public Frame class
	
	// CONSTANT VARIABLES
	static final String STRIKE = "X"; // initializes STRIKE string as "X"
	static final String SPARE = "/"; // initializes SPARE string as "/"
	static final int LAST_FRAME = 10; // initializes LAST_FRAME as the 10th frame
	
	// declared variables
	int rollsAvailable = 2; // initializes number of rolls available in each frame
	ArrayList<String> results = new ArrayList<String>(); // initializes new ArrayList item "results"
	int frameNumber; // initializes variable to track each frame

	Scanner input = new Scanner(System.in); // declaration of new Scanner "input"

	Frame(int frameNumber) { // constructor Frame
		this.frameNumber = frameNumber; // identifies this instance of frameNumber
		// if statement re-defines number of rolls available to be 3 for the 10th frame
		if (frameNumber == LAST_FRAME) {
			rollsAvailable = 3;
		}
	}

	// function to determine results of each roll
	public void roll() {
		boolean error = false;
		
		// for loop to receive input from user for rolls
		for (int x = 0; x < rollsAvailable; x++) {
			error = false;
			String prompt = String.format("What did you score on Roll %d of Frame %d? ", x + 1, frameNumber);
			System.out.printf(prompt);
			
			String value = input.next(); // receives the user's next input as variable "value"
			
			// switch to indicate acceptable input
			// if input is not "X", "/", or 0-9, an error message will prompt the user to re-enter valid input
			switch(value) {
			case STRIKE:
				results.add(value);
				break;
			case SPARE:
				results.add(value);
				break;
			case "0":
				results.add(value);
				break;
			case "1":
				results.add(value);
				break;
			case "2":
				results.add(value);
				break;
			case "3":
				results.add(value);
				break;
			case "4":
				results.add(value);
				break;
			case "5":
				results.add(value);
				break;
			case "6":
				results.add(value);
				break;
			case "7":
				results.add(value);
				break;
			case "8":
				results.add(value);
				break;
			case "9":
				results.add(value);
				break;
			default:
				System.out.println("Please enter a number 0-9, X, or /"); // error message to prompt valid input
				error = true;
				break;
			}
			
			// if statement to break out of loop and skip to next frame if a STRIKE or SPARE is rolled (not in LAST_FRAME)
			if(value.equals(STRIKE) || value.equals(SPARE)) {
				if(frameNumber < LAST_FRAME) {
					break;
				}		
			}
			
			if (error) x--; // if invalid input is entered, program does not progress to the next frame

		}

	}

}