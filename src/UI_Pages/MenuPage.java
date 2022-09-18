package UI_Pages;

import Core.UserData;
import UI_Elements.Assets;
import UI_Elements.Button;
import UI_Elements.Label;

import java.awt.*;

public class MenuPage extends UserData {
    private static final Dimension buttonSize = new Dimension(550, 110);

    void create(Frame frame) {
        Label title = new Label("Password Generator");
        Button newButton = new Button("Create New Password", buttonSize);
        Button termsButton = new Button("Add / Remove Terms", buttonSize);
        Button viewButton = new Button("View Passwords", buttonSize);

        newButton.addActionListener(e -> frame.setPage(Frame.PASS));
        termsButton.addActionListener(e -> frame.setPage(Frame.TERM));
        viewButton.addActionListener(e -> frame.setPage(Frame.VIEW));

        frame.getContentPane().setLayout(Assets.menuPageLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(newButton);
        frame.getContentPane().add(termsButton);
        frame.getContentPane().add(viewButton);
    }
}