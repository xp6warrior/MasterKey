package Core;

import UI_Pages.Frame;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public abstract class MasterKey {
    public static final String name = "MasterKey"; // Name of program referenced many times in the program

    public static void main(String[] args) {
        UserData.createDirectory(); // Creates a directory for the files (or detects directory if it exists)
        String password;

        // If there are no encrypted password-titles saved on computer
        // Then, the "Create Password" login prompt is shown
        if (UserData.getData(UserData.PASSWORD_MODE, true).isEmpty()) {
            password = JOptionPane.showInputDialog(null, "Create Password:", name, JOptionPane.INFORMATION_MESSAGE);

            // null if operation is cancelled
            if (password != null) {
                if (keyMeetsRequirements(password)) { // If the password meets password requirements
                    Cryptography.setKey(password); // Sets the password as the cryptography key
                    new Frame();

                } else {
                    JOptionPane.showMessageDialog(null, "Password must be between 1-16 ASCII characters!", name, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // Else, the "Enter Password" login prompt is shown
        else {
            password = JOptionPane.showInputDialog(null, "Enter Password:", name, JOptionPane.QUESTION_MESSAGE);

            // null if operation is cancelled
            if (password != null) {
                if (UserData.passwordTest(password)) { // If the password is correct
                    new Frame();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password!", name, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static boolean keyMeetsRequirements(String key) {
        return (!key.equals("") && key.length() <= 16 && StandardCharsets.US_ASCII.newEncoder().canEncode(key));
    }
}
