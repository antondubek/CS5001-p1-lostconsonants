package com.antondubek;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Main {



    public static void main(String[] args) {

        ArrayList<String> lines = FileUtil.readLines(args[0]);
        String input = args[1];

        System.out.println(input);

        String[] words = input.split(" ");


        StringBuilder sb = new StringBuilder(input);


        for (int i = 0; i < input.length() - 1; i++){

            System.out.println("##########################################"); // blank line

            // Get the character at index
            char letter = input.charAt(i);

            // Check if the character is a consonant
            if (isLetterVowel(letter) == false){ // if it is a consonant

                //take letter out
                System.out.println("DEBUG: Character Chosen = "+letter);
                sb.deleteCharAt(i);

                // Write the new string without the letter
                String newInput = sb.toString();
                System.out.println("DEBUG: New String = " +newInput);

                //check the word against dictionary
                int dictionaryCheck = isInDictionary(newInput, lines);
                if ( dictionaryCheck == -1){
                    System.out.println("The word is not in dictionary");
                } else {
                    System.out.println("Word is found in location: " +dictionaryCheck);
                }

            } else {
                //letter is a vowel
                System.out.println("Letter is a vowel");
            }

        }

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
