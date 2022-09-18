package UI_Pages;

import Core.UserData;
import UI_Elements.Assets;
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
        ScrollField scrollField = new ScrollField(550, 260, 10);
        JPanel bottomPanel = new JPanel();

        // Buttons
        add.addActionListener(e -> scrollField.add(new TermField(500, 50)));
        remove.addActionListener(e -> scrollField.remove(ScrollField.TERM));
        back.addActionListener(e -> {
            frame.setPage(Frame.MENU);
            UserData.saveToTerms();
        });

        // BottomPanel components
        bottomPanel.setLayout(Assets.bottomPanelLayout);
        bottomPanel.add(add);
        bottomPanel.add(remove);
        bottomPanel.add(back);

        // Frame components
        frame.getContentPane().setLayout(Assets.frameLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(scrollField);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Load terms
        for (Term term: UserData.loadFromTerms()) {
            Field field = new Field(500, 50, true);
            field.setText(term.getName());
            scrollField.add(field);
            UserData.addTerm(term);
        }
    }
}