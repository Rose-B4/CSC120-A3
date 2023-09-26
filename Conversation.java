import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

class Conversation {
    static String[] openingStatements = {"What would you like to talk about today?", "What is on your mind?", "What's up?"};
    static String[] setResponses = {"That's great", "Very cool", "Neato", "How interesting"}; // create a list of strings that will be used to respond to unknown statements
    static String[][] mirrorWords = {{"I", "you"}, {"me", "you"}, {"you", "I"}, {"am", "are"}, {"my", "your"}, {"your", "my"}};
    static Random random = new Random(); // create a module to use for generating random numbers
    static Scanner input = new Scanner(System.in); // create a module to use for getting user input
    static String transcript[] = {};


    // You will start the conversation here.
    public static void main(String[] arguments) {
        int totalTurns = getNumTurns(); // get the total number of turns the user wants to take

        int openingStatementIndex = random.nextInt(openingStatements.length);

        System.out.println(openingStatements[openingStatementIndex]);
        addStringToTranscript(openingStatements[openingStatementIndex]);

        String userInput; // create a variable to hold the user's input
        String outputString;
        for (int i=0; i < totalTurns+1; i++){ // run the following code the number of times the user asked for
            userInput = input.nextLine(); // get user input
            addStringToTranscript(userInput); // add the user's input to the transcript
            outputString = getOutput(userInput); // check the user's input and generate an output
            System.out.println(outputString); // print the output
            addStringToTranscript(outputString); // add the output to the transcript
        }
    }
    /**
     * Checks the user input for key words. If there are keywords found, mirror them, if not, return a canned response
     * @param input takes the user's input to be checked
     */
    public static String getOutput(String input){
        String[] splicedString = input.split(" ");
        boolean useSetResponse = true;
        String stringToReturn;

        for(int i=0; i < splicedString.length; i++){ // iterate over the whole list of words the user input
            for(int j=0; j < mirrorWords.length; j++){ // iterate over all of the mirrored words
                if(splicedString[i].equalsIgnoreCase(mirrorWords[j][0])){ // if the current word is the same as one of the words we can mirror
                    splicedString[i] = mirrorWords[j][1]; // set the word in the spliced string to be the mirrored word
                    useSetResponse = false; // don't use a preset response since we changed something with the original string
                    break; // end the for j loop so the mirrored word doesn't get further modified
                }
            }
        }

        if(useSetResponse == true){
            int responseIndex = random.nextInt(setResponses.length);
            stringToReturn = setResponses[responseIndex];
        }
        else{
            stringToReturn = String.join(" ", splicedString);
            // TODO: Make first letter uppercase
        }

        return stringToReturn;
    }
    
    /**
     * Gets a user input for the number of turns the user wants to take
     * @return an int that is greater than 0s
     */
    public static int getNumTurns(){
        System.out.println("How many turns would you like there to be?");
        int intToReturn = -1;
        while (intToReturn == -1){
            try { // run the following code and allow errors to happen
                System.out.println("Please input a whole number greater than 0");
                intToReturn = input.nextInt(); // get an input from the user
                if (intToReturn <= 0){ // if the input is less than 0
                    throw new Exception("A number less than 0 was input"); // throw an error to force this block of code to run again
                }
            }
            catch (Exception e) { // if an error occurred
                input.nextLine(); // clear the input buffer
                intToReturn = -1; // make sure the value to return stays at -1
                continue;
            }
        }
        input.nextLine(); // clear the input buffer
        return intToReturn;
    }

    private static void addStringToTranscript(String stringToAdd){
        transcript = Arrays.copyOf(transcript, transcript.length +1);
        transcript[transcript.length - 1] = stringToAdd;
    }
}
