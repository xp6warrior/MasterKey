package UI_Pages;

import Core.CommonAssets;
import Core.UserData;
import Objects.JComponentArray;
import UI_Elements.*;
import Objects.Password;
import UI_Elements.Button;

import java.awt.*;

public class ViewPage {
    public static final Dimension buttonSize = new Dimension(265, 60);

    void create(Frame frame) {
        // Components
        Title title = new Title("View Passwords", CommonAssets.titleSize);
        Button remove = new Button("Remove", buttonSize);
        Button back = new Button("Back", buttonSize);
        ScrollField scrollField = new ScrollField();

        JComponentArray components = new JComponentArray(new Component[]{title, scrollField}, new Component[]{remove, back});

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