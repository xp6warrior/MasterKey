package Core;

import Objects.Term;

import java.util.ArrayList;
import java.util.Random;

public abstract class RandomPassword {
    private static final char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '~', '!', '@', '#', '$', '%', '&', '-', '_', '<', '>', '?'};
    private static final Random random = new Random();
    private static final int minChar = 2; // The minimum and maximum amount of characters added to a term to create a password
    private static final int maxChar = 4;

    public static String createRandomPassword() {
        ArrayList<Term> terms = UserData.loadFromTerms(); // Gets terms

        if (!terms.isEmpty()) { // Only works if there are existing terms
            String term = terms.get(random.nextInt(terms.size())).getName(); // Gets random term
            int max = term.length() + maxChar;
            int min = term.length() + minChar;

            // random.nextInt((max - min) + 1) + min -> Gets a random password length and index at which term is inserted into password
            int passwordLength = random.nextInt((max - min) + 1) + min;
            int termPlacementIndex = random.nextInt(passwordLength - term.length());
            char[] password = new char[passwordLength]; // Creates character array for password

            // Inserts random symbols into password
            for (int i = 0; i < passwordLength; i++) {
                char symbol = symbols[random.nextInt(symbols.length)];
                password[i] = symbol;
            }

            // Adds the term into the password at the termPlacementIndex
            int tCharIndex = 0;
            for (int i = termPlacementIndex; i < term.length() + termPlacementIndex; i++) {
                char termCharacter = term.charAt(tCharIndex);
                tCharIndex++;

                // 1/3 chance that a character will be upper cased
                if (random.nextInt(3) == 2) {
                    termCharacter = Character.toUpperCase(termCharacter);
                }

                password[i] = termCharacter;
            }

            // Converts the character array into a to String
            String pass = "";
            for (char c : password) {
                pass = pass.concat(String.valueOf(c));
            }
            return pass;
        }

        return null; // Returns null if there are no saved terms
    }
}
