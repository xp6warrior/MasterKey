import Core.Cryptography;
import Core.UserData;
import UI_Pages.Frame;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        UserData.createDirectory();
        String password;
        String msg = "KeyWizard";

        if (!UserData.getData(UserData.PASSWORD_MODE, true).isEmpty()) {
            password = JOptionPane.showInputDialog(null, "Enter Password:", msg, JOptionPane.QUESTION_MESSAGE);

            if (password != null) { // null if operation is cancelled
                if (UserData.passwordTest(password)) {
                    new Frame();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password!", msg, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            password = JOptionPane.showInputDialog(null, "Create Password:", msg, JOptionPane.INFORMATION_MESSAGE);

            if (password != null) {
                if (keyMeetsRequirements(password)) {
                    Cryptography.setKey(password);
                    new Frame();

                } else {
                    JOptionPane.showMessageDialog(null, "Password must be between 1-16 ASCII characters!", msg, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static boolean keyMeetsRequirements(String key) {
        return (!key.equals("") && key.length() <= 16 && StandardCharsets.US_ASCII.newEncoder().canEncode(key));
    }
}
