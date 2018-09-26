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

        if (args.length != 2) {
            System.out.println("Expected 2 command line arguments, but got " + args.length + ".");
            System.out.println("Please provide the path to the dictionary file as the first argument and a sentence "
                    + "as the second argument.");
            System.exit(0);
        }

        ArrayList<String> lines = FileUtil.readLines(args[0]);

        // If lines is empty, then display "Invalid dictionary, aborting." and exit!
        if (lines.size() == 0) {
            System.out.println("Invalid dictionary, aborting.");
            System.exit(0);
        }
        String input = args[1];

        //Make the whole of the dictionary lowercase so that only lowercase words need to be tested
        for (int j = 0; j < lines.size(); j++) {
            String oldItem = lines.get(j);
            lines.set(j, oldItem.toLowerCase());
        }

        ////System.out.println("DEBUG: Input = "+input);

        int numOfAlternativesFound = 0;


        for (int i = 0; i < input.length(); i++) {

            StringBuilder sb = new StringBuilder(input);

            ////System.out.println("##########################################"); // blank line

            // Get the character at index
            char letter = input.charAt(i);

            // Check if the character is a consonant
            if (isLetterConsonant(letter)) { // if it is a consonant

                //take letter out
                ////System.out.println("DEBUG: Character Chosen = "+letter);
                sb.deleteCharAt(i);

                // Write the new string without the letter
                String newInput = sb.toString();
                ////System.out.println("DEBUG: New String = " +newInput);

                // Split the words
                String[] words = newInput.split(" ");
                //String[] words = newInput.split("\\\\s+|(?=[,.])");

                /*for(String something : words){
                   System.out.println("DEBUG: "+something);
                }*/

                // Check that each word is in the dictionary
                int counter = 0;
                for (int x = 0; x < words.length; x++) { // Look at each word and check if its in the dictionary

                    char lastCharacter = words[x].charAt(words[x].length() - 1);
                    if (lastCharacter == '.' || lastCharacter == ',') {
                        words[x] = words[x].replaceAll("[^\\w]", ""); // remove punctuation
                    }

                    ////words[x] = words[x].replaceAll("[^\\w]", ""); // remove punctuation

                    ////System.out.println("DEBUG: Searched word = "+ words[x].toLowerCase());
                    if (lines.contains(words[x].toLowerCase())) {

                        ////System.out.println("DEBUG: Found word location = "+ lines.indexOf(words[x]));
                        counter++; // if the word is in the dictionary add one to the counter
                    }


                    /*
                    else {
                        ////System.out.println("DEBUG: Word not found!");
                    }
                    */
                }

                // If all the words were validated
                ////System.out.println("DEBUG: Counter = " + counter);
                ////System.out.println("DEBUG: Length = "+words.length);
                if (counter == words.length) {
                    System.out.println(newInput);
                    numOfAlternativesFound++; // Add one to the counter
                }

            }


            /*else {
                //letter is a vowel
                ////System.out.println("DEBUG: Letter is a vowel");
            }*/

        }

        if (numOfAlternativesFound == 0) {
            System.out.println("Could not find any alternatives.");
        } else {
            System.out.println("Found " + numOfAlternativesFound + " alternatives.");
        }

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
            case ' ': // fixes merged words problem
                return false;
            case ',': // fixes commas being lost
                return false;
            case '.':
                return false;
            case '/':
                return false;
            default:
                return true;
        }
    }


    /*
    public static int isInDictionary(String word, ArrayList dictionary) {

        boolean exists = dictionary.contains(word);

        if (exists) {
            return dictionary.indexOf(word);
        } else {
            return -1;
        }
    }
    */
}
