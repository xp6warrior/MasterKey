package Core;

import Objects.Password;
import Objects.Term;

import javax.crypto.Cipher;
import java.io.*;
import java.util.ArrayList;

public abstract class UserData {
    private static final ArrayList<Password> passwords = new ArrayList<>();
    private static final ArrayList<Term> terms = new ArrayList<>();

    private static String path;
    public static void createDirectory() {
        String homeDir = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();
        File directory;

        if (os.contains("win")) {
            directory = new File(homeDir + "\\Documents\\Password");
        }
        else if (os.contains("mac")) {
            directory = new File(homeDir + "/Documents/Password");
        }
        else {
            directory = new File("./Password");
        }
        if (!directory.exists()) {
            directory.mkdir();
        }
        path = directory.getPath();
    }
    public static String checkForData() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/Passwords.txt"));
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void addPassword(Password password) {
        passwords.add(password);
    }
    public static void removePassword(String passName) {
        for (Password pass: passwords) {
            if (pass.getPass().equals(passName) || passName == null) {
                passwords.remove(pass);
                break;
            }
        }
    }

    public static void addTerm(Term term) {
        terms.add(term);
    }
    public static void removeTerm(String name) {
        for (Term term: terms) {
            if (term.getName().equals(name)) {
                terms.remove(term);
                break;
            }
        }
    }


    public static void saveToPasswords() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/Passwords.txt"));

            for (Password password: passwords) {
                String title = password.getTitle();
                String pass = password.getPass();

                String encryptedT = Cryptography.doCryptography(title, Cipher.ENCRYPT_MODE);
                String encryptedP = Cryptography.doCryptography(pass, Cipher.ENCRYPT_MODE);

                bw.write(encryptedT + " " + encryptedP + "\n");
            }

            bw.close();
            passwords.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveToTerms() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(path + "/Terms.txt"));

            for (Term term: terms) {
                bw.write(term.getName()+"\n");
            }

            bw.close();
            terms.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Password> loadFromPasswords() {
        ArrayList<Password> passwordsToLoad = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/Passwords.txt"));

            String line = br.readLine();
            while (line != null) {
                String[] titlePass = line.split(" ");

                String decryptedT = Cryptography.doCryptography(titlePass[0], Cipher.DECRYPT_MODE);
                String decryptedP = Cryptography.doCryptography(titlePass[1], Cipher.DECRYPT_MODE);

                passwordsToLoad.add(new Password(decryptedT, decryptedP));

                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return passwordsToLoad;
    }

    public static ArrayList<Term> loadFromTerms() {
        ArrayList<Term> termsToLoad = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + "/Terms.txt"));

            String line = br.readLine();
            while (line != null) {
                termsToLoad.add(new Term(line));
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return termsToLoad;
    }
}