import Core.Cryptography;
import Core.UserData;
import UI_Pages.Frame;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        UserData.createDirectory();
        String key;

        if (UserData.checkForData() != null) {
            key = showMessage("Enter Password: ", JOptionPane.QUESTION_MESSAGE);
            if (key != null) { // null if operation is cancelled
                if (Cryptography.keyTest(key)) {
                    new Frame();
                } else {
                    showMessage("Incorrect Password!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            key = showMessage("Create Password: ", JOptionPane.INFORMATION_MESSAGE);
            if (key != null) {
                if (keyMeetsRequirements(key)) {
                    Cryptography.setKey(key);
                    new Frame();
                } else {
                    showMessage("Password must be between 1-16 ASCII characters!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static String showMessage(String msg, int type) {
        if (type != JOptionPane.ERROR_MESSAGE) {
            return JOptionPane.showInputDialog(null, msg, "Password Generator", type);
        }
        JOptionPane.showMessageDialog(null, msg, "Password Generator", JOptionPane.ERROR_MESSAGE);
        return null;
    }
    private static boolean keyMeetsRequirements(String key) {
        return (!key.equals("") && key.length() <= 16 && StandardCharsets.US_ASCII.newEncoder().canEncode(key));
    }
}
