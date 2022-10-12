package Core;

import Objects.Password;
import Objects.Term;

import javax.crypto.Cipher;
import java.io.*;
import java.util.ArrayList;

public abstract class UserData {
    private static final ArrayList<Password> passwords = new ArrayList<>();
    private static final ArrayList<Term> terms = new ArrayList<>();

    public static final int PASSWORD_MODE = 1;
    public static final int TERM_MODE = 2;

    private static String path;

    @SuppressWarnings("all")
    public static void createDirectory() {
        String homeDir = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();
        File directory;
        File pass;
        File terms;

        if (os.contains("win")) {
            directory = new File(homeDir + "\\Documents\\" + MasterKey.name);
            pass = new File(directory.getPath() + "\\Passwords.txt");
            terms = new File(directory.getPath() + "\\Terms.txt");
        } else if (os.contains("mac")) {
            directory = new File(homeDir + "/Documents/" + MasterKey.name);
            pass = new File(directory.getPath() + "/Passwords.txt");
            terms = new File(directory.getPath() + "/Terms.txt");
        } else {
            directory = new File("./" + MasterKey.name);
            pass = new File("./" + MasterKey.name + "/Passwords.txt");
            terms = new File("./" + MasterKey.name + "/Terms.txt");
        }
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!pass.exists()) {
            try {pass.createNewFile();} catch (IOException e) {e.printStackTrace();}
        }
        if (!terms.exists()) {
            try {terms.createNewFile();} catch (IOException e) {e.printStackTrace();}
        }
        path = directory.getPath();
    }
    public static boolean passwordTest(String pass) {
        Cryptography.setKey(pass);
        ArrayList<Password> data = loadFromPasswords();

        for (Password p: data) {
            if (!p.getTitle().equals("_corrupt") || !p.getPass().equals("_corrupt")) {
                return true;
            }
        }
        return false;
    }


    public static void addPassword(Password password) {
        passwords.add(password);
    }
    public static void addTerm(Term term) {
        terms.add(term);
    }
    public static void removePassword(String passName) {
        for (Password pass: passwords) {
            if (pass.getPass().equals(passName) || passName == null) {
                passwords.remove(pass);
                break;
            }
        }
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
        ArrayList<String> data = new ArrayList<>();

        for (Password pass: passwords) {
            String encryptedT = Cryptography.doCryptography(pass.getTitle(), Cipher.ENCRYPT_MODE);
            String encryptedP = Cryptography.doCryptography(pass.getPass(), Cipher.ENCRYPT_MODE);

            data.add(encryptedT + " " + encryptedP);
        }

        saveData(data, PASSWORD_MODE);
        passwords.clear();
    }
    public static void saveToTerms() {
        ArrayList<String> data = new ArrayList<>();

        for (Term term: terms) {
            data.add(term.getName());
        }

        saveData(data, TERM_MODE);
        terms.clear();
    }
    public static ArrayList<Password> loadFromPasswords() {
        ArrayList<String> data = getData(PASSWORD_MODE, false);
        ArrayList<Password> passwords = new ArrayList<>();

        for (String line: data) {
            String[] split = line.split(" ");

            String decryptedT = "_corrupt";
            String decryptedP = "_corrupt";

            boolean Tsuccess = false;
            for (String s : split) {
                if (!Tsuccess) {
                    decryptedT = Cryptography.doCryptography(s, Cipher.DECRYPT_MODE);

                    if (!decryptedT.equals("_corrupt") && !decryptedT.equals("")) {
                        Tsuccess = true;
                    }
                } else {
                    decryptedP = Cryptography.doCryptography(s, Cipher.DECRYPT_MODE);

                    if (!decryptedP.equals("_corrupt") && !decryptedP.equals("")) {
                        break;
                    }
                }
            }

            passwords.add(new Password(decryptedT, decryptedP));
        }

        return passwords;
    }

    // This method is invoked when a list of Terms is needed
    // Return value: ArrayList<Term> terms - a list of Term objects
    public static ArrayList<Term> loadFromTerms() {

        ArrayList<Term> terms = new ArrayList<>(); // Return data
        ArrayList<String> data = getData(UserData.TERM_MODE, false); // Gets save data in the form of Strings

        // Transforms String values into Term Objects
        for (String line: data) {
            terms.add(new Term(line));
        }

        return terms;
    }


    private static String getPath(int mode) {
        switch (mode) {
            case PASSWORD_MODE: return path + "/Passwords.txt";
            case TERM_MODE: return path + "/Terms.txt";
        }
        return "";
    }

    // The method for reading general data from a file
    // Parameters: int mode - the type of data (Password or Term), boolean firstLine - if the method should return only the first line
    // Return value: ArrayList<String> data - general data read from the file
    public static ArrayList<String> getData(int mode, boolean firstLine) {

        ArrayList<String> data = new ArrayList<>(); // Return data
        String p = getPath(mode); // Gets the system path to the file

        // Try/Catch in case of IOException
        try {

            // The FileReader is placed in a BufferedReader to provide a buffer as well as functionality (readline() method)
            BufferedReader br = new BufferedReader(new FileReader(p));
            String line = br.readLine();

            // Reads only first line
            if (firstLine && line != null) {
                data.add(line);
            }
            // Reads all data
            else if (!firstLine) {
                while (line != null) {
                    data.add(line);
                    line = br.readLine();
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    // The method for writing general data onto a file
    // Parameters: ArrayList<String> data - The general data being written, int mode - the type of data (Password or Term)
    private static void saveData(ArrayList<String> data, int mode) {

        String p = getPath(mode); // Gets the system path to the file

        // Try/Catch in case of IOException
        try {
            // The FileWriter is placed in a BufferedWriter to provide a buffer
            BufferedWriter bw = new BufferedWriter(new FileWriter(p));

            for (String line: data) {
                bw.write(line+"\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}