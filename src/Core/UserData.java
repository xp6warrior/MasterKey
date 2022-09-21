package Core;

import Objects.Password;
import Objects.Term;

import javax.crypto.Cipher;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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

        if (os.contains("win")) {
            directory = new File(homeDir + "\\Documents\\Core.MasterKey");
        } else if (os.contains("mac")) {
            directory = new File(homeDir + "/Documents/Core.MasterKey");
        } else {
            directory = new File("./Core.MasterKey");
        }
        if (!directory.exists()) {
            directory.mkdir();
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
                        System.out.println("a");
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
    public static ArrayList<Term> loadFromTerms() {
        ArrayList<String> data = getData(TERM_MODE, false);
        ArrayList<Term> terms = new ArrayList<>();

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

    public static ArrayList<String> getData(int mode, boolean firstLine) {
        String p = getPath(mode);
        ArrayList<String> data = new ArrayList<>();

        // Reads either first line or all data
        try {
            BufferedReader br = new BufferedReader(new FileReader(p));
            String line = br.readLine();

            if (firstLine && line != null) {
                data.add(line);
            }
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

    private static void saveData(ArrayList<String> mode, int type) {
        String p = getPath(type);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(p));
            for (String line: mode) {
                bw.write(line+"\n");
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}