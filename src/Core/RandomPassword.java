package Core;

import Objects.Term;

import java.util.Random;

public abstract class RandomPassword {
    private static final char[] lettersLower = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'
            , 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] lettersUpper = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P'
            , 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] symbols = {'`', '~', '!', '@', '#', '$', '%', '&', '-', '_'
            ,';', ':', '"', '<', '>', '?'};
    private static final char[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private static final char[][] allCharacters = {lettersLower, lettersUpper, numbers, symbols};
    private static final Random random = new Random();

    public static String createRandomPassword(Term inputTerm) {
        String term = inputTerm.getName();

        int passLength = random.nextInt((16 - term.length() + 4) + 1) + term.length() + 4; // r.nextInt((max - min) + 1) + min
        int termPlace = random.nextInt(passLength - 1 - term.length());

        char[] password = new char[passLength];

        for (int i = 0; i < passLength; i++) {
            char[] symbolType = allCharacters[random.nextInt(3)];
            char character = symbolType[random.nextInt(symbolType.length)];
            password[i] = character;
        }


        int place = 0;
        for (int i = termPlace; i < term.length() + termPlace; i++) {
            password[i] = term.charAt(place);
            place++;
        }

        System.out.println(password);

        return null;
    }
}
