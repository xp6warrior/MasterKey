package Core;

import Objects.Term;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public abstract class ModTerms {
    private static final ArrayList<Term> terms = new ArrayList<>();

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

    public static void saveToTerms() {
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

    public static ArrayList<Term> loadFromTerms() {
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