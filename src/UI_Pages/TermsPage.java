package UI_Pages;

import Core.CommonAssets;
import Core.UserData;
import Objects.JComponentArray;
import UI_Elements.*;
import Objects.Term;
import UI_Elements.Button;

import javax.swing.*;
import java.awt.*;

public class TermsPage {
    private static final Dimension buttonSize = new Dimension(170, 60);

    public void create(Frame frame) {
        // Components
        Title title = new Title("Add / Remove Terms", CommonAssets.titleSize);
        Button add = new Button("Add", buttonSize);
        Button remove = new Button("Remove", buttonSize);
        Button back = new Button("Back", buttonSize);
        ScrollField scrollField = new ScrollField();

        JComponentArray components = new JComponentArray(new JComponent[]{title, scrollField}, new JComponent[]{add, remove, back});

        // Button functionality
        add.addActionListener(e -> scrollField.add(new TermField())); // Adds new TermField to the ScrollField
        remove.addActionListener(e -> scrollField.delete(false)); // Deletes selected TermFields from ScrollField
        back.addActionListener(e -> { // Switches back to main menu and saves changes to terms
            frame.setPage(Frame.MENU);
            UserData.saveToTerms();
        });

        // Renders all components onto the Frame
        frame.add(components);

        // Loads list of existing terms for changing (displays them as Fields)
        for (Term term: UserData.loadFromTerms()) {
            Field field = new Field(CommonAssets.fieldSize, true);
            field.setText(term.getName());
            scrollField.add(field);
            UserData.addTerm(term);
        }
    }
}