import java.util.ArrayList;

/**
 * A class which, when passed a phrase and a dictionary of words, will generate the "lost consonants" versions of the
 * sentence. The definition of Lost Consonants can be seen on Wikipedia: https://en.wikipedia.org/wiki/Lost_Consonants
 *
 * @author CS5001 Student (acm35@st-andrews.ac.uk)
 *
 * @version 1
 * @since 1
 */

public class LostConsonants {

    /**
     * Prints the lost consonants of a word or phrase compared against a passed dictionary.
     * @param args A file location of the dictionary, followed by a word or "phrase"
     *
     */
    public static void main(String[] args) {

        // Argument check
        if (args.length != 2) {
            System.out.println("Expected 2 command line arguments, but got " + args.length + ".");
            System.out.println("Please provide the path to the dictionary file as the first argument and a sentence "
                    + "as the second argument.");
            System.exit(0);
        }

        // Creation of dictionary Arraylist from source file
        ArrayList<String> lines = FileUtil.readLines(args[0]);

        //Make the whole of the dictionary lowercase so that words can be tested lowercase
        ArrayList<String> dictionary = parseDictionary(lines);

        // Check if the dictionary is empty, if so, warn and exit
        if (lines.size() == 0) {
            System.out.println("Invalid dictionary, aborting.");
            System.exit(0);
        }

        // Save the inputted string
        String input = args[1];

        int numOfAlternativesFound = 0;

        // Iterate through each character of the input string
        for (int i = 0; i < input.length(); i++) {

            StringBuilder sb = new StringBuilder(input);

            // Get the character at index i
            char letter = input.charAt(i);

            // Check if the character is a consonant
            if (isLetterConsonant(letter)) {

                //take letter out
                sb.deleteCharAt(i);

                // Write the new string without the letter
                String newInput = sb.toString();

                // Split the words
                String[] words = newInput.split(" ");

                // If all the words were validated in the dictionary
                if (areWordsInDic(words, dictionary)) {
                    System.out.println(newInput); //Print out the line
                    numOfAlternativesFound++; // Add one to the alternatives found counter
                }
            }
        }

        //If no alternatives found print phrase, else tell how many have been found
        if (numOfAlternativesFound == 0) {
            System.out.println("Could not find any alternatives.");
        } else {
            System.out.println("Found " + numOfAlternativesFound + " alternatives.");
        }
    }

    /**
     * Takes the dictionary array lines and makes it all lowercase.
     * @param lines Arraylist containing words of a dictionary
     * @return Returns the dictionary with all words lowercase
     */
    public static ArrayList<String> parseDictionary(ArrayList<String> lines) {

        for (int j = 0; j < lines.size(); j++) {
            String oldItem = lines.get(j);
            lines.set(j, oldItem.toLowerCase());
        }

        return lines;
    }

    /**
     * Checks whether a letter is a consonant.
     * @param letter Char containing a letter to be checked
     * @return Returns True (Letter is a consonant) or False (Letter is not)
     */
    public static boolean isLetterConsonant(char letter) {
        switch (Character.toLowerCase(letter)) {
            case 'a':
                return false;
            case 'e':
                return false;
            case 'i':
                return false;
            case 'o':
                return false;
            case 'u':
                return false;
            case ' ':
                return false;
            case ',':
                return false;
            case '.':
                return false;
            case '/':
                return false;
            case '?':
                return false;
            default:
                return true;
        }
    }

    /**
     * Given a list of words to test against a dictionary, the method splits the words, removes irrelevant punctuation
     * and tests whether the word exists within the dictionary.
     * @param words String array of words to check against the dictionary
     * @param dictionary Arraylist of strings forming a dictionary to test against
     * @return Returns True if ALL of the words are found in the dictionary, otherwise returns false.
     */
    public static boolean areWordsInDic(String[] words, ArrayList<String> dictionary) {
        // word is in the dictionary counter
        int counter = 0;

        // Iterate through all the words in the array
        for (int x = 0; x < words.length; x++) {

            //Get the last character of word
            char lastCharacter = words[x].charAt(words[x].length() - 1);

            //Check if the last character is a '.' ',' '/' '?' '!', if so remove punctuation
            if (".,/?!".indexOf(lastCharacter) != -1) {
                words[x] = words[x].replaceAll("[^\\w]", ""); // remove punctuation
            }

            //If the word is in the dictionary, increment the helper counter
            if (dictionary.contains(words[x].toLowerCase())) {
                counter++;
            }
        }

        // If all the words are in the dictionary return true, else false!
        return counter == words.length;
    }
}
