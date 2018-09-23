import java.util.ArrayList;

public class LostConsonants {


    public static void main(String[] args) {

        if(args.length != 2) {
            System.out.println("Expected 2 command line arguments, but got "+args.length+".");
            System.out.println("Please provide the path to the dictionary file as the first argument and a sentence " +
                    "as the second argument.");
            System.exit(0);
        }

        ArrayList<String> lines = FileUtil.readLines(args[0]);

        // If lines is empty, then display "Invalid dictionary, aborting." and exit!
        if (lines.size() == 0) {
            System.out.println("Invalid dictionary, aborting.");
            System.exit(0);
        }
        String input = args[1];

        ////System.out.println("DEBUG: Input = "+input);

        int numOfAlternativesFound = 0;


        for (int i = 0; i < input.length() - 1; i++){

            StringBuilder sb = new StringBuilder(input);

            ///System.out.println("##########################################"); // blank line

            // Get the character at index
            char letter = input.charAt(i);

            // Check if the character is a consonant
            if (isLetterVowel(letter) == false){ // if it is a consonant

                //take letter out
                ////System.out.println("DEBUG: Character Chosen = "+letter);
                sb.deleteCharAt(i);

                // Write the new string without the letter
                String newInput = sb.toString();
                ////System.out.println("DEBUG: New String = " +newInput);

                // Split the words
                String[] words = newInput.split(" ");

                // Check that each word is in the dictionary
                int counter = 0;
                for (int x = 0; x < words.length; x++){ // Look at each word and check if its in the dictionary
                    ////System.out.println("DEBUG: Searched word = "+ words[x]);
                    if(lines.contains(words[x])){
                        ////System.out.println("DEBUG: Found word location = "+ lines.indexOf(words[x]));
                        counter++; // if the word is in the dictionary add one to the counter
                    } else {
                        ////System.out.println("DEBUG: Word not found!");
                    }
                }

                // If all the words were validated
                ////System.out.println("DEBUG: Counter = " + counter);
                ////System.out.println("DEBUG: Length = "+words.length);
                if (counter == words.length){
                    System.out.println(newInput);
                    numOfAlternativesFound++; // Add one to the counter
                }


            } else {
                //letter is a vowel
                ////System.out.println("DEBUG: Letter is a vowel");
            }

        }

        System.out.println("Found "+numOfAlternativesFound+" alternatives.");

    }

    public static boolean isLetterVowel(char letter){
        switch (Character.toLowerCase(letter)){
            case 'a':
                return true;
            case 'e':
                return true;
            case 'i':
                return true;
            case 'o':
                return true;
            case 'u':
                return true;
            default:
                return false;
        }
    }

    public static int isInDictionary(String word, ArrayList dictionary){

        boolean exists = dictionary.contains(word);

        if (exists){
            return dictionary.indexOf(word);
        } else {
            return -1;
        }
    }
}
