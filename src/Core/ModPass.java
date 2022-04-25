package Core;

import Objects.Password;
import Objects.Term;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ModPass {
    private static final ArrayList<Password> passwords = new ArrayList<>();

    public static void addPass(Password password) {
        passwords.add(password);
    }

    public static void removePass(String name) {
        for (Password pass: passwords) {
            if (pass.getPass().equals(name)) {
                passwords.remove(pass);
                break;
            }
        }
    }

    public static void saveToPasswords() {
        // Saves title in one line, password in the next in Passwords.txt (first line is ignored to save code in algorithm)
        try {
            FileWriter writer = new FileWriter("Passwords.txt");

            for (Password password: passwords) {
                System.out.println(password.getPass());
                String title = password.getTitle();
                String pass = password.getPass();
                writer.write("\n"+title+"\n"+pass);
            }

            writer.close();
            passwords.clear();
        } catch (IOException ignore) {}
    }

    public static ArrayList<Password> loadFromPasswords() {
        ArrayList<Password> passwordsToLoad = new ArrayList<>();
        String tempTitle = "";

        try {
            FileReader reader = new FileReader("Passwords.txt");
            StringBuilder name = new StringBuilder();
            boolean firstLineSkipped = false;

            int data = reader.read();

            while (data!=-1) {
                if (data!=10) { // If character is not ENTER -> add char to name
                    name.append((char)data);
                } else if (firstLineSkipped) { // If char is ENTER + first line was skipped -> finish name create pass
                    if (tempTitle.equals("")) {
                        tempTitle = name.toString();
                    } else {
                        passwordsToLoad.add(new Password(name.toString(), tempTitle));
                        tempTitle = "";
                    }
                    name = new StringBuilder();
                } else { // If char is ENTER + is first line -> skip
                    firstLineSkipped = true;
                }
                data = reader.read();
            }

            // For adding last pass
            if (firstLineSkipped) {
                passwordsToLoad.add(new Password(name.toString(), tempTitle));
            }

        } catch (IOException ignore) {}
        return passwordsToLoad;
    }
}