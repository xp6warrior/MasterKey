package Core;

import Objects.Password;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModPass {
    private static final ArrayList<Password> passwords = new ArrayList<>();

    public static void addPass(Password password) {
        passwords.add(password);
    }

    public static void saveToPasswords() {
        // Saves title in one line, password in the next in Passwords.txt (first line is ignored to save code in algorithm)
        try {
            FileWriter writer = new FileWriter("Passwords.txt", true);

            for (Password password: passwords) {
                String[] info = password.getInfo();
                writer.write("\n"+info[0]+"\n"+info[1]);
            }

            writer.close();
            passwords.clear();
        } catch (IOException ignore) {}
    }
}