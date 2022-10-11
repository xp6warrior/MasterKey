package Core;

import Objects.Password;
import UI_Pages.Frame;

import javax.swing.*;
import java.nio.charset.StandardCharsets;

public abstract class MasterKey {
    public static final String name = "MasterKey";

    public static void main(String[] args) {
        UserData.createDirectory();
        String password;

        if (!UserData.getData(UserData.PASSWORD_MODE, true).isEmpty()) {
            password = JOptionPane.showInputDialog(null, "Enter Password:", name, JOptionPane.QUESTION_MESSAGE);

            if (password != null) { // null if operation is cancelled
                if (UserData.passwordTest(password)) {
                    new Frame();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Password!", name, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else {
            password = JOptionPane.showInputDialog(null, "Create Password:", name, JOptionPane.INFORMATION_MESSAGE);

            if (password != null) {
                if (keyMeetsRequirements(password)) {
                    Cryptography.setKey(password);
                    new Frame();

                } else {
                    JOptionPane.showMessageDialog(null, "Password must be between 1-16 ASCII characters!", name, JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private static boolean keyMeetsRequirements(String key) {
        return (!key.equals("") && key.length() <= 16 && StandardCharsets.US_ASCII.newEncoder().canEncode(key));
    }
}
