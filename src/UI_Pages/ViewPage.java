package UI_Pages;

import Core.UserData;
import Objects.ComponentArray;
import UI_Elements.Button;
import UI_Elements.Title;
import UI_Elements.PassField;
import UI_Elements.ScrollField;
import Objects.Password;

import javax.swing.*;
import java.awt.*;

public class ViewPage {
    public static final Dimension buttonSize = new Dimension(265, 60);

    void create(Frame frame) {
        // Components
        Title title = new Title("View Passwords");
        Button remove = new Button("Remove", buttonSize);
        Button back = new Button("Back", buttonSize);
        ScrollField scrollField = new ScrollField();

        ComponentArray components = new ComponentArray(new Component[]{title, scrollField}, new Component[]{remove, back});

        // Buttons
        remove.addActionListener(e -> scrollField.delete(true));
        back.addActionListener(e -> {
            frame.setPage(Frame.MENU);
            UserData.saveToPasswords();
        });

        // Adding
        frame.add(components);

        // Load passwords
        for (Password password: UserData.loadFromPasswords()) {
            PassField field = new PassField(password);
            scrollField.add(field);
            UserData.addPassword(password);
        }
    }
}