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
            Field field = new Field(CommonAssets.fieldSize, true);
            field.setText(term.getName());
            scrollField.add(field);
            UserData.addTerm(term);
        }
    }
}