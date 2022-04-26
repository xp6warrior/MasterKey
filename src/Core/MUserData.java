package Core;

import Objects.Password;
import Objects.Term;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class MUserData {
    private static final ArrayList<Password> passwords = new ArrayList<>();
    private static final ArrayList<Term> terms = new ArrayList<>();

    protected static void addPassword(Password password) {
        passwords.add(password);
    }

    protected static void removePassword(String name) {
        for (Password pass: passwords) {
            if (pass.getPass().equals(name)) {
                passwords.remove(pass);
                break;
            }
        }
    }

    protected static void saveToPasswords() {
        // Saves title in one line, password in the next in Passwords.txt (first line is ignored to save code in algorithm)
        try {
            FileWriter writer = new FileWriter("Passwords.txt");

            for (Password password: passwords) {
                String title = password.getTitle();
                String pass = password.getPass();
                writer.write("\n"+title+"\n"+pass);
            }

            writer.close();
            passwords.clear();
        } catch (IOException ignore) {}
    }

    protected static ArrayList<Password> loadFromPasswords() {
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
                passwordsToLoad.add(new Password(tempTitle, name.toString()));
            }

        } catch (IOException ignore) {}
        return passwordsToLoad;
    }


    protected static void addTerm(Term term) {
        terms.add(term);
    }

    protected static void removeTerm(String name) {
        for (Term term: terms) {
            if (term.getName().equals(name)) {
                terms.remove(term);
                break;
            }
        }
    }

    protected static void saveToTerms() {
        // Saves term to Terms.txt (first line is ignored to save code in algorithm)
        try {
            FileWriter writer = new FileWriter("Terms.txt");

            for (Term term: terms) {
                writer.write("\n"+term.getName());
            }

            writer.close();
            terms.clear();
        } catch (IOException ignore) {}
    }

    protected static ArrayList<Term> loadFromTerms() {
        ArrayList<Term> termsToLoad = new ArrayList<>();

        try {
            FileReader reader = new FileReader("Terms.txt");
            StringBuilder name = new StringBuilder();
            boolean firstLineSkipped = false;

            int data = reader.read();

            while (data!=-1) {
                if (data!=10) { // If character is not ENTER -> add char to name
                    name.append((char)data);
                } else if (firstLineSkipped) { // If char is ENTER + first line was skipped -> finish name create term
                    termsToLoad.add(new Term(name.toString()));

                    System.out.println(name);

                    name = new StringBuilder();
                } else { // If char is ENTER + is first line -> skip
                    firstLineSkipped = true;
                }
                data = reader.read();
            }

            // For adding last term
            if (firstLineSkipped) {
                termsToLoad.add(new Term(name.toString()));
            }

        } catch (IOException ignore) {}
        return termsToLoad;
    }
}