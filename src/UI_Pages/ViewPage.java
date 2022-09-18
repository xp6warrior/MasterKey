package UI_Pages;

import Core.UserData;
import UI_Elements.Assets;
import UI_Elements.Button;
import UI_Elements.Label;
import UI_Elements.PassField;
import UI_Elements.ScrollField;
import Objects.Password;

import javax.swing.*;
import java.awt.*;

public class ViewPage {
    void create(Frame frame) {
        // Components
        Label title = new Label("View Passwords");
        Button remove = new Button("Remove", Assets.mediumButtonSize);
        Button back = new Button("Back", Assets.mediumButtonSize);
        ScrollField scrollField = new ScrollField(550, 260, 10);
        JPanel bottomPanel = new JPanel();

        // Buttons
        remove.addActionListener(e -> scrollField.remove(ScrollField.PASSWORD));
        back.addActionListener(e -> {
            frame.setPage(Frame.MENU);
            UserData.saveToPasswords();
        });

        // BottomPanel components
        bottomPanel.setLayout(Assets.bottomPanelLayout);
        bottomPanel.add(remove);
        bottomPanel.add(back);

        // Frame components
        frame.getContentPane().setLayout(Assets.frameLayout);
        frame.getContentPane().add(title);
        frame.getContentPane().add(scrollField);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        // Load passwords
        for (Password password: UserData.loadFromPasswords()) {
            PassField field = new PassField(500, 50, password);
            scrollField.add(field);
            UserData.addPassword(password);
        }
    }
}