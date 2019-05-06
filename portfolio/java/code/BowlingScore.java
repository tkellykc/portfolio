// Timothy Kelly
// 
// This program keeps score for a bowling game. The program prompts the user to input the value for each roll of each frame, storing the values into corresponding arrays. After all of the rolls are made, the program outputs the final scores as a score card.

import java.util.ArrayList; // imports ArrayList utility

public class BowlingScore { // names public class

	// CONSTANT VARIABLES
	static final String STRIKE = "X"; // initializes STRIKE string as "X"
	static final String SPARE = "/"; // initializes SPARE string as "/"
	static final int TOTAL_FRAMES = 10; // initializes TOTAL_FRAMES as "10"

	// declared arrays
	static ArrayList<ArrayList<String>> frameResults = new ArrayList<ArrayList<String>>(); // declares new ArrayList "frameResuts" which displays user input for each roll in each frame
	static int[] frameScores = new int[TOTAL_FRAMES]; // declares new "frameScores" array to display the score values of each frame

	public static void main(String[] args) { // opens main program function
		
		// welcome message and explanation of input
		System.out.println("\nWelcome to Kingpin Bowling!");
		System.out.println("\nPlease enter your result for each roll as you play...\n");
		
		for (int x = 0; x < TOTAL_FRAMES; x++) { // for loop to count number of frames
			Frame frame = new Frame(x + 1);
			frame.roll();

			frameResults.add(frame.results); // adds results of each frame
		}

		calculateFrameScores(); // calls function to calculate scores of each frame
		
		System.out.println("\nThank you for playing! Here is your final score card:");
			
		printScoreCard(); // calls function to print final game score card
		
		System.out.println("\n\n----------------------\n*** END OF PROGRAM ***");

	}

	// opens function to calculate frame scores
	private static void calculateFrameScores() {
		
		ArrayList<String> rolls = new ArrayList<String>(); // stores each roll in ArrayList
		ArrayList<int[]> strikeIndeces = new ArrayList<int[]>(); // stores each strike in ArrayList
		ArrayList<int[]> spareIndeces = new ArrayList<int[]>(); // stores each spare in ArrayList
		ArrayList<Integer> openFrameIndeces = new ArrayList<Integer>(); // stores each open frame in ArrayList

		// loop to find and index each strike and spare rolled
		int rollIndex = 0;
		for (int frameIndex = 0; frameIndex < frameResults.size(); frameIndex++) {
			boolean isOpen = true;
			
			// loop to find which frames included strikes or spares
			for (String roll : frameResults.get(frameIndex)) {
				if (roll.equals(STRIKE)) {
					int[] frameStrike = { frameIndex, rollIndex };
					strikeIndeces.add(frameStrike);
					isOpen = false;
				}
				if (roll.equals(SPARE)) {
					int[] frameSpare = { frameIndex, rollIndex };
					spareIndeces.add(frameSpare);
					isOpen = false;
				}

				rolls.add(roll);
				rollIndex++;
			}
			
			// adds any open frame to index
			if(isOpen) {
				openFrameIndeces.add(frameIndex);
			}
			
		}

		calculateStrikes(rolls, strikeIndeces); // calls function to calculate strike scores
		calculateSpares(rolls, spareIndeces); // calls function to calculate spare scores
		calculateOpenFrames(openFrameIndeces); // calls function to calculate open frame scores
		
		ArrayList<String> lastFrameRolls = frameResults.get(frameResults.size() - 1); // initializes rolls specifically for final frame
		calculateLastFrame(lastFrameRolls); // calls function to calculate the final frame scores
	}

	// function to calculate strike scores
	private static void calculateStrikes(ArrayList<String> rolls, ArrayList<int[]> strikeIndeces) {
		for (int[] strikeIndex : strikeIndeces) {
			int currentFrame = strikeIndex[0];
			int startRoll = strikeIndex[1];
			int endRoll = startRoll + 2;
			
			int frameTotalScore = 0;

			if (currentFrame < TOTAL_FRAMES - 1) {
				for (int currrentIndex = startRoll; currrentIndex <= endRoll; currrentIndex++) {
					frameTotalScore += getRollValue(rolls, currrentIndex);
				}
				
				frameScores[currentFrame] = frameTotalScore;
			}
		}
	}

	// function to calculate spare scores
	private static void calculateSpares(ArrayList<String> rolls, ArrayList<int[]> spareIndeces) {
		for (int[] spareIndex : spareIndeces) {
			int currentFrame = spareIndex[0];
			int startRoll = spareIndex[1] - 1;
			int endRoll = startRoll + 2;
			
			int frameTotalScore = 0;
			
			if (currentFrame < TOTAL_FRAMES - 1) {
				for (int currentIndex = startRoll; currentIndex <= endRoll; currentIndex++) {
					frameTotalScore += getRollValue(rolls, currentIndex);
				}
				
				frameScores[currentFrame] = frameTotalScore;
			}
		}
	}
	
	// function to calculate open frame scores (any frame that does not include strikes or spares
	private static void calculateOpenFrames(ArrayList<Integer> openFrameIndeces) {
		for (int openFrameIndex : openFrameIndeces) {
			int frameTotal = 0;
			for (String roll : frameResults.get(openFrameIndex)) {
				frameTotal += Integer.parseInt(roll);
			}
			
			frameScores[openFrameIndex] = frameTotal;
		}
	}

	// function to calculate the rolls in the final frame
	private static void calculateLastFrame(ArrayList<String> rolls) {
		int lastFrameTotal = 0;
		for(int x = 0; x < rolls.size(); x++) {
			lastFrameTotal += getRollValue(rolls, x);
		}
		
		frameScores[frameScores.length - 1] = lastFrameTotal;
	}
	
	// function to get value for each toll (strike, spare, or numeric value)
	private static int getRollValue(ArrayList<String> rolls, int currentIndex) {
		String value = rolls.get(currentIndex);
		switch (value) {
		case STRIKE:
			return 10;
		case SPARE:
			int previous = Integer.valueOf(rolls.get(currentIndex - 1));
			return 10 - previous;
		default:
			return Integer.valueOf(value);
		}
	}
	
	// function to print final game score card
	private static void printScoreCard() {
		
		// outputs frame number list
		System.out.print("\nFrame:\t\t");
		for (int x = 0; x < TOTAL_FRAMES; x++) {
			System.out.printf("\t%d", x + 1);
		}
		
		// outputs the user's input for each roll in each frame
		System.out.print("\n\nResult:\t\t");
		for (int x = 0; x < frameResults.size(); x++) {
			ArrayList<String> result = frameResults.get(x);
			System.out.print("\t");
			for (String roll : result) {
				System.out.printf("%s ", roll);
			}
			
		}
		
		// outputs the scores for each frame
		System.out.print("\n\nFrame Score:\t");
		for (int x = 0; x < frameScores.length; x++) {
			System.out.printf("\t%d", frameScores[x]);	
		}
		
		// outputs the running total frame by frame including the final game score
		System.out.print("\n\nRunning Total:\t");
		int runningTotal = 0;
		for (int x = 0; x < frameScores.length; x++) {
			runningTotal += frameScores[x];
			System.out.printf("\t%d", runningTotal);	
		}
	}

}
