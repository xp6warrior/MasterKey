package UI_Pages;

import Core.UserData;
import Objects.ComponentArray;
import UI_Elements.Button;
import UI_Elements.Title;

import java.awt.*;

public class MenuPage extends UserData {
    private static final Dimension buttonSize = new Dimension(550, 110);

    void create(Frame frame) {
        // Components
        Title title = new Title("Password Generator");
        Button newButton = new Button("Create New Password", buttonSize);
        Button termsButton = new Button("Add / Remove Terms", buttonSize);
        Button viewButton = new Button("View Passwords", buttonSize);

        ComponentArray components = new ComponentArray(new Component[]{title, newButton, termsButton, viewButton}, null);

        // Buttons
        newButton.addActionListener(e -> frame.setPage(Frame.PASS));
        termsButton.addActionListener(e -> frame.setPage(Frame.TERM));
        viewButton.addActionListener(e -> frame.setPage(Frame.VIEW));

        // Adding
        frame.add(components);
    }
}