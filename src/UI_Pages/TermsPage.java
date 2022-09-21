package UI_Pages;

import Core.UserData;
import Objects.ComponentArray;
import UI_Elements.Button;
import UI_Elements.Field;
import UI_Elements.Title;
import UI_Elements.ScrollField;
import UI_Elements.TermField;
import Objects.Term;

import javax.swing.*;
import java.awt.*;

public class TermsPage {
    private static final Dimension buttonSize = new Dimension(170, 60);

    void create(Frame frame) {
        // Components
        Title title = new Title("Add / Remove Terms");
        Button add = new Button("Add", buttonSize);
        Button remove = new Button("Remove", buttonSize);
        Button back = new Button("Back", buttonSize);
        ScrollField scrollField = new ScrollField();

        ComponentArray components = new ComponentArray(new Component[]{title, scrollField}, new Component[]{add, remove, back});

        // Buttons
        add.addActionListener(e -> scrollField.add(new TermField()));
        remove.addActionListener(e -> scrollField.delete(false));
        back.addActionListener(e -> {
            frame.setPage(Frame.MENU);
            UserData.saveToTerms();
        });

        // Adding
        frame.add(components);

        // Load terms
        for (Term term: UserData.loadFromTerms()) {
            Field field = new Field(500, 50, true);
            field.setText(term.getName());
            scrollField.add(field);
            UserData.addTerm(term);
        }
    }
}