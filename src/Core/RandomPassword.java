package Core;

import Objects.Term;

import java.util.ArrayList;
import java.util.Random;

public abstract class RandomPassword {
    private static final char[] symbols = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '~', '!', '@', '#', '$', '%', '&', '-', '_', '<', '>', '?'};
    private static final Random random = new Random();

    private static final int minChar = 2;
    private static final int maxChar = 4;

    public static String createRandomPassword() {
        ArrayList<Term> terms = UserData.loadFromTerms();

        if (!terms.isEmpty()) {
            String term = terms.get(random.nextInt(terms.size())).getName();

            int max = term.length() + maxChar;
            int min = term.length() + minChar;

            //-- random.nextInt((max - min) + 1) + min
            int passwordLength = random.nextInt((max - min) + 1) + min;
            int termPlacementIndex = random.nextInt(passwordLength - term.length());

            char[] password = new char[passwordLength];

            // Create random symbols
            for (int i = 0; i < passwordLength; i++) {
                char symbol = symbols[random.nextInt(symbols.length)];
                password[i] = symbol;
            }

            // Add the term (randomizing upper and lower case)
            int tCharIndex = 0;
            for (int i = termPlacementIndex; i < term.length() + termPlacementIndex; i++) {
                char termCharacter = term.charAt(tCharIndex);
                tCharIndex++;

                // 1/3 chance of upper case
                if (random.nextInt(3) == 2) {
                    termCharacter = Character.toUpperCase(termCharacter);
                }

                password[i] = termCharacter;
            }

            // Converting char[] to String
            String pass = "";
            for (char c : password) {
                pass = pass.concat(String.valueOf(c));
            }
            return pass;
        }

        return null;
    }
}
