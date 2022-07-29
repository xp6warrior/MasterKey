package Core;

import Objects.Password;
import Objects.Term;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public abstract class MUserData {
    private static final ArrayList<Password> passwords = new ArrayList<>();
    private static final ArrayList<Term> terms = new ArrayList<>();

    public static void addPassword(Password password) {
        passwords.add(password);
    }
    public static void removePassword(String term) {
        for (Password pass: passwords) {
            if (pass.getPass().equals(term) || term == null) {
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
            BufferedWriter bw = new BufferedWriter(new FileWriter("res/Passwords.txt"));

            for (Password password: passwords) {
                String title = password.getTitle();
                String pass = password.getPass();
                bw.write(title + "\n" + pass + "\n");
            }

            bw.close();
            passwords.clear();

        } catch (IOException ignore) {}
    }
    public static void saveToTerms() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("res/Terms.txt"));

            for (Term term: terms) {
                bw.write(term.getName()+"\n");
            }

            bw.close();
            terms.clear();

        } catch (IOException ignore) {}
    }

    public static ArrayList<Password> loadFromPasswords() {
        ArrayList<Password> passwordsToLoad = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("res/Passwords.txt"));

            String title = br.readLine();
            String pass = br.readLine();
            while (title != null) {
                passwordsToLoad.add(new Password(title, pass));
                title = br.readLine();
                pass = br.readLine();
            }
            br.close();

        } catch (IOException ignored) {}

        return passwordsToLoad;
    }
    public static ArrayList<Term> loadFromTerms() {
        ArrayList<Term> termsToLoad = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("res/Terms.txt"));

            String line = br.readLine();
            while (line != null) {
                termsToLoad.add(new Term(line));
                line = br.readLine();
            }
            br.close();

        } catch (IOException ignore) {}

        return termsToLoad;
    }
}