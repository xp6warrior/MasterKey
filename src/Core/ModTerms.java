package Core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ModTerms {
    public void saveToTerms(ArrayList<String> terms) {
        try {
            FileWriter writer = new FileWriter("Terms.txt");

            for (String term: terms) {
                writer.append(term);

                if (terms.size()!=terms.indexOf(term)+1) {
                    writer.append("\n");
                }
            }
            writer.close();
        } catch (IOException ignore) {}
    }

    public ArrayList<String> loadToTerms() {
        ArrayList<String> terms = new ArrayList<>();

        try {
            FileReader reader = new FileReader("Terms.txt");
            StringBuilder term = new StringBuilder();

            int data = reader.read();
            while (data!=-1) {
                if (data!=10) {
                    term.append((char)data);
                } else {
                    terms.add(term.toString());
                    term = new StringBuilder();
                }

                data = reader.read();
            }
            terms.add(term.toString());
            reader.close();

        } catch (IOException ignore) {}
        return terms;
    }
}