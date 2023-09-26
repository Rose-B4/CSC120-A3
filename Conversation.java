import java.util.Scanner;
import java.util.Random;
class Conversation {

    static String[] setResponses = {"That's great", "Very cool", "Neato", "How interesting"}; // create a list of strings that will be used to respond to unknown statements
    static Random random = new Random(); // create a module to use for generating random numbers
    static Scanner scanner = new Scanner(System.in); // create a module to use for getting user input

	// You will start the conversation here.
	public static void main(String[] arguments) {
		int totalTurns = getNumTurns(scanner); // get the total number of turns the user wants to take

		System.out.println("What would you like to talk about today?");
        String userInput = scanner.nextLine(); // get user input
		for (int i=0; i < totalTurns+1; i++){
			userInput = scanner.nextLine(); // get user input
			checkInput(userInput); // check the user's input
		}
	}


	// later use this method to scan for keywords or topics
	public static void checkInput(String input){
        input = input.toLowerCase();
		if (input.contains("hello")){
			System.out.println("HELLO DETECTED");
		}
		else{
			int responseIndex = random.nextInt(setResponses.length);
            System.out.println(setResponses[responseIndex]);
		}
	}
	

	public static int getNumTurns(Scanner scanner){
		System.out.println("How many turns would you like there to be?");
		int intToReturn = scanner.nextInt();
		// later add input checking here
		return intToReturn;
	}
}
