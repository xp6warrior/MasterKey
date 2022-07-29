package Core;

import Objects.Term;

import java.util.ArrayList;
import java.util.Random;

public abstract class RandomPassword {
    private static final char[] symbols = {'~', '!', '@', '#', '$', '%', '&', '-', '_'
            , '<', '>', '?'};
    private static final char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private static final char[][] allSymbols = {symbols, numbers};

    private static final Random random = new Random();

    private static final int minChar = 2;
    private static final int maxChar = 4;

    public static String createRandomPassword() {
        ArrayList<Term> terms = MUserData.loadFromTerms();
        String termName = terms.get(random.nextInt(terms.size())).getName();

        int max = termName.length() + maxChar;
        int min = termName.length() + minChar;

        //-- random.nextInt((max - min) + 1) + min
        int newPassLength = random.nextInt((max - min) + 1) + min;
        int termPlacementIndex = random.nextInt(newPassLength - termName.length());

        char[] password = new char[newPassLength];


        // Creating random symbols
        for (int i = 0; i < newPassLength; i++) {
            int symbolTypeIndex = random.nextInt(2);
            int symbolIndex = random.nextInt(allSymbols[symbolTypeIndex].length);

            char symbol = allSymbols[symbolTypeIndex][symbolIndex];
            password[i] = symbol;
        }


        // Adding the term (randomizing upper and lower case)
        int place = 0;
        for (int i = termPlacementIndex; i < termName.length() + termPlacementIndex; i++) {
            char termCharacter = termName.charAt(place);

            // 1/3 chance of upper case
            if (random.nextInt(3) == 2) {
                termCharacter = Character.toUpperCase(termCharacter);
            }

            password[i] = termCharacter;
            place++;
        }

        // Converting char[] to String
        StringBuilder pass = new StringBuilder();
        for (char c : password) {
            pass.append(c);
        }

        return pass.toString();
    }
}
